package com.canerture.twittercloneapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.canerture.twittercloneapp.databinding.TweetCardBinding
import com.canerture.twittercloneapp.models.Tweet
import com.squareup.picasso.Picasso

class TweetsAdapter(private val tweetsList: List<Tweet>, private val clickedTweetListener: ClickedTweetListener):
    RecyclerView.Adapter<TweetsAdapter.TweetCardDesign>() {

    inner class TweetCardDesign(var tweetCardBinding: TweetCardBinding): RecyclerView.ViewHolder(tweetCardBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TweetCardDesign {
        val layoutInflater = LayoutInflater.from(parent.context)
        val tweetCardBinding = TweetCardBinding.inflate(layoutInflater, parent, false)

        return TweetCardDesign(tweetCardBinding)
    }

    override fun onBindViewHolder(holder: TweetCardDesign, position: Int) {
        val tweet = tweetsList[position]
        holder.tweetCardBinding.tweetObject = tweet

        holder.tweetCardBinding.tweetMenuText.setOnClickListener {
            clickedTweetListener.onClickedTweetListener(tweet, position)
        }

        Picasso.get().load(tweet.profilepicture).into(holder.tweetCardBinding.profilePicture)
        Picasso.get().load(tweet.tweetimage).into(holder.tweetCardBinding.tweetImage)

        holder.tweetCardBinding.tweetImage
    }

    override fun getItemCount(): Int {
        return tweetsList.size
    }

    interface ClickedTweetListener {
        fun onClickedTweetListener(data: Tweet, pos: Int)
    }


}