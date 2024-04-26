package com.zekri_ahmed.pokemontypes.presentation.pokemons_list.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp

@Composable
fun ErrorMessageLoading(error:String?,retry:()->Unit){
    Text(
        text =
        buildAnnotatedString {
            append(error!! + " ")
            withStyle(
                style = SpanStyle(
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Red,
                )
            ) {
                append("Retry")
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .clickable { retry() })


}