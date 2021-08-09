package com.canerture.twittercloneapp.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class WhatsGoingOn(@SerializedName("subject") @Expose var subject: String,
                   @SerializedName("content") @Expose var content: String,
                   @SerializedName("image") @Expose var image: String) : Serializable