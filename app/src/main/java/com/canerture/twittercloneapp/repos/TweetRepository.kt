package com.canerture.twittercloneapp.repos

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.canerture.twittercloneapp.models.Tweet
import com.canerture.twittercloneapp.models.User
import com.canerture.twittercloneapp.response.CRUDResponse
import com.canerture.twittercloneapp.response.TweetResponse
import com.canerture.twittercloneapp.response.UserResponse
import com.canerture.twittercloneapp.retrofit.ApiUtils
import com.canerture.twittercloneapp.retrofit.TweetsDAOInterface
import com.canerture.twittercloneapp.retrofit.UsersDAOInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TweetRepository {

    private var tweetData = MutableLiveData<List<Tweet>>()
    private var tweetsDIF : TweetsDAOInterface = ApiUtils.getTweetsDAOInterface()

    init {
        tweetData = MutableLiveData()
    }

    fun getTweetData() : MutableLiveData<List<Tweet>> {
        return tweetData
    }

    fun allTweets() {
        tweetsDIF.allTweets().enqueue(object : Callback<TweetResponse> {
            override fun onResponse(call: Call<TweetResponse>, response: Response<TweetResponse>) {
                val tempTweets = response.body()?.tweets
                tweetData.value = tempTweets!!
                println(tempTweets)
            }

            override fun onFailure(call: Call<TweetResponse>, t: Throwable) {
                Log.e("All Tweets Error", t.localizedMessage!!.toString())
            }

        })
    }
}