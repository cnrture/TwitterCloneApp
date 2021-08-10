package com.canerture.twittercloneapp.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class DirectMessage(@SerializedName("profilepicture") @Expose var profilepicture: String,
                         @SerializedName("name") @Expose var name: String,
                         @SerializedName("nickname") @Expose var nickname: String,
                         @SerializedName("message") @Expose var message: String,
                         @SerializedName("date") @Expose var date: String,) : Serializable