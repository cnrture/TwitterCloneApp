package com.canerture.twittercloneapp.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class WhoToFollow(@SerializedName("id") @Expose var id: String,
                   @SerializedName("name") @Expose var name: String,
                   @SerializedName("profilpicture") @Expose var profilpicture: String) : Serializable