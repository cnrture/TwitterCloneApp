package com.canerture.twittercloneapp.adapters

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.canerture.twittercloneapp.R
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
            it.findNavController().navigate(R.id.action_homeFragment_to_tweetBottomSheetFragment)
        }

        holder.tweetCardBinding.commentButton.setOnClickListener {
            clickedTweetListener.crfButtonsListener("comment", tweet.docId, tweet.comment.toIntOrNull()!!)
        }

        holder.tweetCardBinding.rtButton.setOnClickListener {
            clickedTweetListener.crfButtonsListener("rt", tweet.docId, tweet.rt.toIntOrNull()!!)
        }

        holder.tweetCardBinding.favButton.setOnClickListener {
            clickedTweetListener.crfButtonsListener("fav", tweet.docId, tweet.fav.toIntOrNull()!!)
        }

        Picasso.get().load(tweet.profilepicture).into(holder.tweetCardBinding.profilePicture)
        if (tweet.tweetimage.isNotEmpty()) {
            Picasso.get().load(tweet.tweetimage).into(holder.tweetCardBinding.tweetImage)
        }

    }

    override fun getItemCount(): Int {
        return tweetsList.size
    }

    interface ClickedTweetListener {
        fun crfButtonsListener(commentrtfav: String, tweetDocId: String, currentlyCRFNumber: Int)
    }


}