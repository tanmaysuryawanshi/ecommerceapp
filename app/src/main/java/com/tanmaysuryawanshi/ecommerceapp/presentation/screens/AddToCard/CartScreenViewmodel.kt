package com.tanmaysuryawanshi.ecommerceapp.presentation.screens.AddToCard

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tanmaysuryawanshi.ecommerceapp.domain.model.ProductItem
import com.tanmaysuryawanshi.ecommerceapp.domain.model.ProductResponse
import com.tanmaysuryawanshi.ecommerceapp.domain.model.Responseitem
import com.tanmaysuryawanshi.ecommerceapp.domain.usecases.AddToCardUsecase
import com.tanmaysuryawanshi.ecommerceapp.presentation.screens.productlist.ProductListInfoState
import com.tanmaysuryawanshi.ecommerceapp.presentation.screens.productlist.ProductListViewmodel
import com.tanmaysuryawanshi.ecommerceapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CartScreenViewmodel @Inject constructor(private val usecase: AddToCardUsecase) : ViewModel() {
    private var addToCartJob: Job?=null

    private val _state= mutableStateOf(CartInfoState())
    val state: State<CartInfoState> =_state


    private val _eventFlow= MutableSharedFlow<UIEvent>()
    val eventFlow =_eventFlow.asSharedFlow()

    fun onAddedToCart(
        price: Double,
        product_name: String,
        product_type: String,
        tax: Double
    ) {
        addToCartJob?.cancel()
        addToCartJob=viewModelScope.launch {

            usecase(product_name,product_type,price.toString(),tax.toString())
                .onEach {
                        result->
                    when(result){
                        is Resource.Success->{
                            Log.d("Success", "onAddedToCart: "+result.data.toString())
                            _state.value=state.value.copy(
                                cartResponse = result.data?: ProductResponse("",
                                    Responseitem("","","",""),0,success = false),
                                isLoading = false
                            )}
                        is Resource.Error ->{
                            Log.d("cart", "onAddedToCart: "+result.message)
                            _state.value=state.value.copy(
                                cartResponse = result.data?: ProductResponse("",Responseitem("","","",""),0,success = false),
                                isLoading = false)
                            _eventFlow.emit(
                                UIEvent.ShowSnackbar(
                                result.message ?:"Unknown error"
                            ))
                        }

                        is Resource.Loading->{
                            _state.value=state.value.copy(
                                cartResponse = result.data?: ProductResponse("",Responseitem("","","",""),0,success = false),
                                isLoading = true
                            )
                        }
                    }
                }.launchIn(this)
        }
    }


    sealed class UIEvent {


        data class ShowSnackbar(val message: String) : UIEvent()
    }

}