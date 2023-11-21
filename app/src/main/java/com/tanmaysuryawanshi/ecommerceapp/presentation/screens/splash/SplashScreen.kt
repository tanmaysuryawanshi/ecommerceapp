package com.tanmaysuryawanshi.ecommerceapp.presentation.screens.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.tanmaysuryawanshi.ecommerceapp.R
import com.tanmaysuryawanshi.ecommerceapp.presentation.components.Description
import com.tanmaysuryawanshi.ecommerceapp.presentation.components.SubTitle
import com.tanmaysuryawanshi.ecommerceapp.presentation.components.Title
import com.tanmaysuryawanshi.ecommerceapp.presentation.navigation.ProductsAppScreens
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavHostController) {
    LaunchedEffect(key1 = true ){
        delay(2000L)
        navController.navigate(ProductsAppScreens.ProductListScreen.route){
            popUpTo(ProductsAppScreens.SplashScreen.route) {
                inclusive = true
            }
        }
    }
Column(modifier = Modifier
    .fillMaxSize()
    .background(color = Color.White),
verticalArrangement = Arrangement.Center) {

    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.splash))
    LottieAnimation(
        modifier = Modifier.size(400.dp),
        composition = composition,
        iterations = LottieConstants.IterateForever,
    )
    Title(text = "CrazyBuy")
   Description(text = "One Stop to buy any thing")

}
}