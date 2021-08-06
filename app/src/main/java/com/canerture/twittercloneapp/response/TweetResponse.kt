package com.canerture.twittercloneapp.response

import com.canerture.twittercloneapp.models.Tweet
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class TweetResponse(@SerializedName("tweets") @Expose var tweets: List<Tweet>, @SerializedName("success") @Expose var success: Int)