package com.tanmaysuryawanshi.ecommerceapp.presentation.navigation

sealed class ProductsAppScreens(val route:String){

    object ProductListScreen: ProductsAppScreens("product_list_screen")
    object AddProductScreen: ProductsAppScreens("add_product_screen")
    object SplashScreen: ProductsAppScreens("splash_screen")
}
