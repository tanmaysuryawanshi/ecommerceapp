package com.tanmaysuryawanshi.ecommerceapp.presentation.screens.productlist

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.tanmaysuryawanshi.ecommerceapp.R
import com.tanmaysuryawanshi.ecommerceapp.domain.model.ProductItem
import com.tanmaysuryawanshi.ecommerceapp.presentation.components.Description
import com.tanmaysuryawanshi.ecommerceapp.presentation.components.SubTitle
import com.tanmaysuryawanshi.ecommerceapp.utils.formatPrice




@Composable
fun CardProduct(item: ProductItem, onClick: () -> Unit) {
    Card(elevation = CardDefaults.cardElevation(
        defaultElevation = 10.dp,

    ),   modifier= Modifier

        .padding(horizontal = 16.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier.

                padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(150.dp)

                    .clip(RoundedCornerShape(16.dp)),
                contentAlignment = Alignment.Center
            ) {
                if (!item.image.isNullOrBlank()) {
                    AsyncImage(
                        model = item.image,
                        contentDescription = null,
                        modifier = Modifier
                            .padding(2.dp),
                        contentScale = ContentScale.Crop

                    )
                } else {
                    Box(
                        modifier = Modifier
                            .padding(2.dp)
                            .fillMaxSize()
                            .background(color = Color.LightGray)
                    )

                }
            }
            SubTitle(text = item.product_name)
            Description(text = item.product_type)


            Row(
                modifier = Modifier
                    .width(150.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Text(
                    text = formatPrice(item.price),
                    textAlign = TextAlign.Center,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 24.sp,

                    color = Color(0xFFFF6500)
                )


                IconButton(onClick = onClick, modifier = Modifier
                    .size(40.dp)
                    .background(
                        Color(0x33FF6500),
                        shape = CircleShape
                    )
                    .clip(CircleShape)
                    .clickable {
                        onClick
                    }) {
                    Icon(
                        imageVector = Icons.Default.Add, contentDescription = "",
                        tint = Color(0xFFFF6500)
                    )
                }


            }

        }
    }
}