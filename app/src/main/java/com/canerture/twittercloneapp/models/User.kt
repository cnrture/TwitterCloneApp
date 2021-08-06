package com.canerture.twittercloneapp.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class User(@SerializedName("signincheck") @Expose var signincheck: Int,
                @SerializedName("searchcheck") @Expose var searchcheck: Int,
                @SerializedName("id") @Expose var id: Int,
                @SerializedName("name") @Expose var name: String,
                @SerializedName("nickname") @Expose var nickname: String,
                @SerializedName("phone") @Expose var phone: String,
                @SerializedName("birthday") @Expose var birthday: String,
                @SerializedName("email") @Expose var email: String,
                @SerializedName("password") @Expose var password: String) : Serializable