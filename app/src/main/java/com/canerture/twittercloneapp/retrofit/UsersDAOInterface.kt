package com.canerture.twittercloneapp.retrofit

import com.canerture.twittercloneapp.response.CRUDResponse
import com.canerture.twittercloneapp.response.UserResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface UsersDAOInterface {

    @POST("/sign_up.php")
    @FormUrlEncoded
    fun signUp(@Field("name") name: String,
               @Field("nickname") nickname: String,
               @Field("phone") phone: String,
               @Field("birthday") birthday: String,
               @Field("email") email: String,
               @Field("password") password: String): Call<CRUDResponse>

    @POST("/sign_in.php")
    @FormUrlEncoded
    fun signIn(@Field("emailphonenickname") emailphonenickname: String,
               @Field("password") password: String): Call<UserResponse>

}