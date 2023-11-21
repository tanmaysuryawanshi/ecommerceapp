package com.tanmaysuryawanshi.ecommerceapp.presentation.screens.productlist

import android.net.Uri
import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.tanmaysuryawanshi.ecommerceapp.domain.model.ProductItem
import com.tanmaysuryawanshi.ecommerceapp.presentation.components.Description
import com.tanmaysuryawanshi.ecommerceapp.presentation.components.SubTitle
import com.tanmaysuryawanshi.ecommerceapp.presentation.components.Title
import com.tanmaysuryawanshi.ecommerceapp.presentation.navigation.ProductsAppScreens
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun ProductListScreen(
    navController: NavHostController,
    productListViewModel: ProductListViewmodel
) {

    val viewState = productListViewModel.state.value

    val snackbarHostState = remember { SnackbarHostState() }
    LaunchedEffect(key1 = viewState.productInfoItems) {
        productListViewModel.eventFlow.collectLatest { result ->
            when (result) {
                is ProductListViewmodel.UIEvent.ShowSnackbar -> {
                    snackbarHostState.showSnackbar(result.message)
                }

            }
        }
    }


    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        containerColor = Color.White
    ) { it ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(8.dp))

            Title(text = "CrazyBuy")

            Spacer(modifier = Modifier.height(8.dp))
            SubTitle(text = "Get your Favourite Products")
            Spacer(modifier = Modifier.height(4.dp))
            Description(text = "Choose best product for you from the variety of Products available in our app")
            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                value = productListViewModel.searchQuery.value,
                onValueChange = productListViewModel::onSearch,
                colors = TextFieldDefaults.textFieldColors(
                    textColor = Color(0xFFFF6500),
                    containerColor = Color.White,
                    cursorColor = Color(0xFFFF6500),
                    unfocusedIndicatorColor = Color.Gray,

                    focusedIndicatorColor = Color(0xFFFF6500)
                ),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search, contentDescription = "",
                        tint = Color.Gray
                    )
                },
                shape = RoundedCornerShape(16.dp),
            )



            Spacer(modifier = Modifier.height(16.dp))

            LazyVerticalStaggeredGrid(
                columns = StaggeredGridCells.Fixed(2),
                horizontalArrangement = Arrangement.Center,

                ) {
                items(viewState.productInfoItems.size) {

                    CardProduct(item = viewState.productInfoItems[it], {
                        Log.d("card clicked", "ProductListScreen: $it")

                       navController.navigate(ProductsAppScreens.AddProductScreen.route + "/${viewState.productInfoItems[it].price.toString()}/${viewState.productInfoItems[it].product_name}/${viewState.productInfoItems[it].product_type}/${viewState.productInfoItems[it].tax.toString()}")
                    })
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }


    }
}