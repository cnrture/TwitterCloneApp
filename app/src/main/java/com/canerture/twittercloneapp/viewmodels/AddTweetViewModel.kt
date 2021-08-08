package com.canerture.twittercloneapp.viewmodels

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.canerture.twittercloneapp.models.Tweet
import com.canerture.twittercloneapp.repos.TweetRepository

class AddTweetViewModel: ViewModel() {

    private var tweetrepo = TweetRepository()
    var tweetData = MutableLiveData<List<Tweet>>()

    init {
        tweetData = tweetrepo.getTweetData()
    }

    fun addTweet(id: String, profilepic: String, name: String, nickname: String, tweetText: String, tweetImageName: String, tweetImage: Uri?) {
        tweetrepo.addTweet(id, profilepic, name, nickname, tweetText, tweetImageName, tweetImage)
    }

}