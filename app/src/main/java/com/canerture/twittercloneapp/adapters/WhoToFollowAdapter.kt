package com.canerture.twittercloneapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.canerture.twittercloneapp.databinding.AgendaCardBinding
import com.canerture.twittercloneapp.databinding.TweetCardBinding
import com.canerture.twittercloneapp.databinding.WhoToFollowCardBinding
import com.canerture.twittercloneapp.models.Agendas
import com.canerture.twittercloneapp.models.WhoToFollow
import com.squareup.picasso.Picasso

class WhoToFollowAdapter(private val whoToFollowList: List<WhoToFollow>):
    RecyclerView.Adapter<WhoToFollowAdapter.WhoToFollowCardDesign>() {

    inner class WhoToFollowCardDesign(var whoToFollowCardBinding: WhoToFollowCardBinding): RecyclerView.ViewHolder(whoToFollowCardBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WhoToFollowCardDesign {
        val layoutInflater = LayoutInflater.from(parent.context)
        val whoToFollowCardBinding = WhoToFollowCardBinding.inflate(layoutInflater, parent, false)

        return WhoToFollowCardDesign(whoToFollowCardBinding)
    }

    override fun onBindViewHolder(holder: WhoToFollowCardDesign, position: Int) {
        val whoToFollow = whoToFollowList[position]
        holder.whoToFollowCardBinding.whoToFollowObject = whoToFollow

        Picasso.get().load(whoToFollow.profilpicture).into(holder.whoToFollowCardBinding.profilePicture)

    }

    override fun getItemCount(): Int {
        return whoToFollowList.size
    }


}