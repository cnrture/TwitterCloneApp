package com.canerture.twittercloneapp.retrofit

import com.canerture.twittercloneapp.response.CRUDResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface TweetsDAOInterface {

    @GET("/all_books.php")
    fun allBooks(): Call<CRUDResponse>

    @POST("/add_book.php")
    @FormUrlEncoded
    fun addBook(@Field("book_name") book_name: String,
                @Field("book_author") book_author: String,
                @Field("book_publisher") book_publisher: String,
                @Field("book_price") book_price: String,
                @Field("book_image_url") book_image_url: String): Call<CRUDResponse>

    @POST("/search_book.php")
    @FormUrlEncoded
    fun searchBook(@Field("book_name") book_name: String): Call<CRUDResponse>

    @POST("/cart_status_change.php")
    @FormUrlEncoded
    fun cartStatusChange(@Field("book_id") book_id: Int,
                         @Field("cart_status") cart_status: Int): Call<CRUDResponse>

}