package com.tanmaysuryawanshi.ecommerceapp.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

@Composable
fun SubTitle( text:String) {
    Text(text =text,
        fontFamily = FontFamily.SansSerif,
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold,
        color = Color(0xFF444652)
        ,
        textAlign = TextAlign.Center,
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun Title(text:String) {

    Text(text = text,
        textAlign = TextAlign.Center,
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Bold,
        fontSize = 28.sp,
        modifier = Modifier.fillMaxWidth(),
        color = Color(0xFFFF6500)
    )

}

@Composable
fun Description(text:String) {
    Text(text =text,
        fontFamily = FontFamily.SansSerif,
        fontSize = 16.sp,
        color = Color(0xFF74757f),
        textAlign = TextAlign.Center,
        modifier = Modifier.fillMaxWidth()


    )
}
