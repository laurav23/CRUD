package com.example.crud

import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

object AppConstantes {
    const val BASE_URL = "http://10.185.208.126:8000"
}

interface Web_Service {

    @GET("/api/products")
    suspend fun obtenerProducts(): Response<ProductsResponse>

    @POST("/api/products")
    fun PostProduct(@Body loginRequest: TraerProduct):Call<Products>

//    @PUT("api/users/{userId}")
//    fun updateProduct(@Body userRequest: TraerProduct, @Path("productId") userId: String): Call<Products>

    @PUT("api/products/{productId}") // Aquí debería ser el mismo nombre de parámetro que en la función
    fun updateProduct(@Body productRequest: TraerProduct, @Path("productId") productId: String): Call<Products>


    @PUT("/api/products/{id}")
   fun actualizarProdu(@Path("id") id: Int, @Body products: Products): Response<String>


    @PUT("api/users/{userId}")
    fun actualizarProducts(@Body userRequest: TraerProduct, @Path("userId") userId: String): Call<Products>

    @GET("/api/products")
    fun getProduct(): Call<List<Products>>

    @GET("/api/products/{id}")
    suspend fun obtenerProductPorId(
        @Path("id") id: Int
    ): Response<ProductsResponse>

    @POST("/api/products")
    suspend fun agregarProducts(
        @Body products: Products
    ): Response<String>

    @PUT("/api/products/{id}")
    suspend fun actualizarProductskk(
        @Path("id") id: Int,
        @Body products: Products
    ): Response<String>

    @DELETE("/api/products/{id}")
    suspend fun borrarProducts(
        @Path("id") idUsuario: Int
    ): Response<String>
}


object RetrofitClient {
    val webService: Web_Service by lazy {
        Retrofit.Builder()
            .baseUrl(AppConstantes.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build().create(Web_Service::class.java)

    }

}
