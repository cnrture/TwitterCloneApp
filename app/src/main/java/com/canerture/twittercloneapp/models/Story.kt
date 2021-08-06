package com.canerture.twittercloneapp.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.sql.Blob

data class Story(@SerializedName("id") @Expose var id: Int,
                 @SerializedName("profilepicture") @Expose var profilepicture: Blob) : Serializable