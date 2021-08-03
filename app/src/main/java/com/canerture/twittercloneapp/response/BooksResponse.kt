package com.canerture.twittercloneapp.response

import com.canerture.twittercloneapp.models.User
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class BooksResponse(@SerializedName("books") @Expose var books: List<User>, @SerializedName("success") @Expose var success: Int)