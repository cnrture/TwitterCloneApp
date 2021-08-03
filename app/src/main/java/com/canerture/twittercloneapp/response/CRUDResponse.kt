package com.canerture.twittercloneapp.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CRUDResponse(@SerializedName("success") @Expose var success: Int, @SerializedName("signincheck") @Expose var signincheck: Int, @SerializedName("message") @Expose var message: String)