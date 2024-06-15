package com.example.mywishlistapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField

import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TextFieldDefaults.outlinedTextFieldColors
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditDetailView(
    id: Long,
    viewModel: WishViewModel,
    navController: NavController
) {

    val snackmessage = remember{
        mutableStateOf("")
    }
    val scope= rememberCoroutineScope()
    val scaffoldstate= rememberScaffoldState()
    if(id!=0L){
        val wish=viewModel.getWishById(id).collectAsState(initial = Wish(0L,"","") )
        viewModel.WishTitleState= wish.value?.title ?: ""
        viewModel.WishDescriptionState= wish.value?.description ?: ""
    }
    else{
        viewModel.WishTitleState=""
        viewModel.WishDescriptionState=""
    }

    Scaffold(
        scaffoldState = scaffoldstate,
        topBar = {
            AppBar(
                title = if (id != 0L) stringResource(id = R.string.update_wish) else stringResource(id = R.string.add_wish),
                onBackNavClicked = { navController.navigateUp() }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .wrapContentSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.height(10.dp))
            OutlinedTextField(
                value = viewModel.WishTitleState,
                onValueChange = { viewModel.onWishtitleChanged(it) },
                label = { Text(text = "Title") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp)
            )

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(
                value = viewModel.WishDescriptionState,
                onValueChange = { viewModel.onWishDescChanged(it) },
                label = { Text(text = "Description") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp)
            )

            Spacer(modifier = Modifier.height(10.dp))

            Button(onClick = {
                if (viewModel.WishTitleState.isNotEmpty() &&
                    viewModel.WishDescriptionState.isNotEmpty()) {
                 if(id!=0L) {
                 // UPDATE WISH
                    viewModel.Updatewish(
                        Wish(
                            id=id,
                            title = viewModel.WishTitleState.trim(),
                            description = viewModel.WishDescriptionState.trim()
                        )
                    )
                 }else {
                     // ADD WISH
                     viewModel.Addwish(Wish(
                         title = viewModel.WishTitleState.trim(),
                         description =viewModel.WishDescriptionState.trim()
                     ))
                     snackmessage.value="Wish has been Created"
                 }
                } else {
                    snackmessage.value="Enter the field to add or Update a Wish"
                }
                scope.launch {
                   // scaffoldstate.snackbarHostState.showSnackbar(snackmessage.value)
                    navController.navigateUp()
                }
            },colors=ButtonDefaults.buttonColors(containerColor =
            colorResource(id = R.color.app_bar_color))) {
                Text(
                    text = if (id != 0L) stringResource(id = R.string.update_wish)
                    else stringResource(id = R.string.add_wish),
                    style = TextStyle(fontSize = 18.sp),

                )
            }
        }
    }
}



