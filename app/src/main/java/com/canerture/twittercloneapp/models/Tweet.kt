package com.canerture.twittercloneapp.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.sql.Blob
import java.util.*

data class Tweet(@SerializedName("docId") @Expose var docId: String,
                 @SerializedName("id") @Expose var id: String,
                 @SerializedName("profilepicture") @Expose var profilepicture: String,
                 @SerializedName("name") @Expose var name: String,
                 @SerializedName("nickname") @Expose var nickname: String,
                 @SerializedName("time") @Expose var time: String,
                 @SerializedName("tweettext") @Expose var tweettext: String,
                 @SerializedName("tweetimage") @Expose var tweetimage: String,
                 @SerializedName("comment") @Expose var comment: String,
                 @SerializedName("rt") @Expose var rt: String,
                 @SerializedName("fav") @Expose var fav: String) : Serializable