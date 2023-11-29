package com.example.crud

data class Products(
    var id: Int,
    var sku: String,
    var name: String,
    var description: String,
    var price: String,
    var quantity: String,
    var status: String,
    val createdAt: String,
    val updatedAt: String
)


