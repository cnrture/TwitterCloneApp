package com.canerture.twittercloneapp.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.canerture.twittercloneapp.models.Tweet
import com.canerture.twittercloneapp.repos.TweetRepository

class HomeFragmentViewModel: ViewModel() {

    private var tweetrepo = TweetRepository()
    var tweetData = MutableLiveData<List<Tweet>>()
    var comment = MutableLiveData<Int>()

    init {
        tweetData = tweetrepo.getTweetData()
        comment = tweetrepo.getCommentNumber()
    }

    fun allTweets() {
        tweetrepo.allTweets()
    }

    fun commentTweet(tweetDocId: String, comment: Int) {
        tweetrepo.commentTweet(tweetDocId, comment)
    }

    fun rtTweet(tweetDocId: String, rt: Int) {
        tweetrepo.rtTweet(tweetDocId, rt)
    }

    fun favTweet(tweetDocId: String, fav: Int) {
        tweetrepo.favTweet(tweetDocId, fav)
    }

}