package com.example.crud

//data class TraerProduct (
    data class TraerProduct(
val sku: String,
val name: String,
val description: String,
val price: String,
val quantity: String,
val status: String,
val createdAt: String = "", // Establecer un valor por defecto o dejarlo como cadena vacía
val updatedAt: String = "" // Establecer un valor por defecto o dejarlo como cadena vacía
)

