package com.tanmaysuryawanshi.ecommerceapp.presentation.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.tanmaysuryawanshi.ecommerceapp.presentation.screens.AddToCard.CartScreen
import com.tanmaysuryawanshi.ecommerceapp.presentation.screens.AddToCard.CartScreenViewmodel
import com.tanmaysuryawanshi.ecommerceapp.presentation.screens.productlist.ProductListScreen
import com.tanmaysuryawanshi.ecommerceapp.presentation.screens.productlist.ProductListViewmodel
import com.tanmaysuryawanshi.ecommerceapp.presentation.screens.splash.SplashScreen


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppNavigation() {
    val navController = rememberNavController()




    NavHost(
        navController = navController,
        startDestination = ProductsAppScreens.SplashScreen.route
    ) {
        composable(ProductsAppScreens.ProductListScreen.route) {

            val productListViewModel: ProductListViewmodel = hiltViewModel()

            ProductListScreen(navController, productListViewModel)

        }
        composable(ProductsAppScreens.SplashScreen.route) {
            SplashScreen(navController)
        }

        composable(ProductsAppScreens.AddProductScreen.route+"/{price}/{product_name}/{product_type}/{tax}") {
                backStackEntry ->

         //   val image = backStackEntry.arguments?.getString("image") ?: ""
          //  val price = backStackEntry.arguments?.getDouble("price") ?: 0.0
            val priceString = backStackEntry.arguments?.getString("price")
            val price = priceString?.toDoubleOrNull() ?: 0.0
            val product_name = backStackEntry.arguments?.getString("product_name") ?: ""
            val product_type = backStackEntry.arguments?.getString("product_type") ?: ""
            //val tax = backStackEntry.arguments?.getDouble("tax") ?: 0.0
            val taxString = backStackEntry.arguments?.getString("tax")
            val tax = taxString?.toDoubleOrNull() ?: 0.0
            val cartViewModel: CartScreenViewmodel = hiltViewModel()

            CartScreen( price = price , product_name = product_name, product_type = product_type , tax =tax,cartViewModel )

        }


    }

}

