package com.canerture.twittercloneapp.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.sql.Blob
import java.util.*

data class Tweet(@SerializedName("id") @Expose var id: Int,
                 //@SerializedName("profilepicture") @Expose var profilepicture: Blob,
                 @SerializedName("name") @Expose var name: String,
                 @SerializedName("nickname") @Expose var nickname: String,
                 @SerializedName("time") @Expose var time: Date,
                 @SerializedName("tweettext") @Expose var tweettext: String,
                 //@SerializedName("tweetimage") @Expose var tweetimage: Blob,
                 @SerializedName("commentnumber") @Expose var commentnumber: Int,
                 @SerializedName("rtnumber") @Expose var rtnumber: Int,
                 @SerializedName("favnumber") @Expose var favnumber: Int) : Serializable