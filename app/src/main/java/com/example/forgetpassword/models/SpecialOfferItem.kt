package com.example.forgetpassword.models

data class SpecialOfferItem(
    val id: Int,
    val categoryTag: String,
    val title: String,
    val subtitle: String,
    val discountTag: String,
    val imageRes: Int,
    val buttonText: String
)
