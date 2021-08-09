package com.canerture.twittercloneapp.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Agendas(@SerializedName("category") @Expose var category: String,
                @SerializedName("hashtag") @Expose var hashtag: String,
                @SerializedName("tweetnumber") @Expose var tweetnumber: String) : Serializable