package com.tanmaysuryawanshi.ecommerceapp.presentation.screens.AddToCard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.tanmaysuryawanshi.ecommerceapp.R
import com.tanmaysuryawanshi.ecommerceapp.presentation.components.Description
import com.tanmaysuryawanshi.ecommerceapp.presentation.components.SubTitle
import com.tanmaysuryawanshi.ecommerceapp.presentation.components.Title
import kotlinx.coroutines.flow.collectLatest


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(
    price: Double,
    product_name: String,
    product_type: String,
    tax: Double,
    cartViewModel: CartScreenViewmodel
) {

    val viewState = cartViewModel.state.value
    val snackbarHostState = remember { SnackbarHostState() }
    LaunchedEffect(key1 = true) {
        cartViewModel.onAddedToCart(price, product_name, product_type, tax)
        cartViewModel.eventFlow.collectLatest { result ->
            when (result) {
                is CartScreenViewmodel.UIEvent.ShowSnackbar -> {
                    snackbarHostState.showSnackbar(result.message)
                }

            }
        }
    }

    Scaffold(snackbarHost = { SnackbarHost(hostState = snackbarHostState) }) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            if (viewState.cartResponse.success) {
                val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.lottie))
                LottieAnimation(
                    modifier = Modifier.size(400.dp),
                    composition = composition,
                    iterations = LottieConstants.IterateForever,
                )

                Title(text = viewState.cartResponse.product_details.product_name)
                SubTitle(text = "Sucessfully added")
                Description(text = viewState.cartResponse.product_details.product_type)
            }
        }
    }
}