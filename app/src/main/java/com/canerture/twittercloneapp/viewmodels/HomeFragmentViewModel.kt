package com.canerture.twittercloneapp.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.canerture.twittercloneapp.models.Tweet
import com.canerture.twittercloneapp.repos.TweetRepository

class HomeFragmentViewModel: ViewModel() {

    private var tweetrepo = TweetRepository()
    var tweetData = MutableLiveData<List<Tweet>>()

    init {
        tweetData = tweetrepo.getTweetData()
    }

    fun allTweets() {
        tweetrepo.allTweets()
    }

}