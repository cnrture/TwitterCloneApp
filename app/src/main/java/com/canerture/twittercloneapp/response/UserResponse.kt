package com.canerture.twittercloneapp.response

import com.canerture.twittercloneapp.models.User
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class UserResponse(@SerializedName("users") @Expose var users: List<User>, @SerializedName("success") @Expose var success: Int)