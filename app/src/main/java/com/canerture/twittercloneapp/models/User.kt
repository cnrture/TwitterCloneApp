package com.canerture.twittercloneapp.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class User(@SerializedName("id") @Expose var id: String,
                @SerializedName("name") @Expose var name: String,
                @SerializedName("nickname") @Expose var nickname: String,
                @SerializedName("phone") @Expose var phone: String,
                @SerializedName("birthday") @Expose var birthday: String,
                @SerializedName("profilepic") @Expose var profilpic: String) : Serializable