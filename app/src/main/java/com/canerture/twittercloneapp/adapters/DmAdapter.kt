package com.canerture.twittercloneapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.canerture.twittercloneapp.databinding.AgendaCardBinding
import com.canerture.twittercloneapp.databinding.DmCardBinding
import com.canerture.twittercloneapp.databinding.TweetCardBinding
import com.canerture.twittercloneapp.databinding.WhoToFollowCardBinding
import com.canerture.twittercloneapp.models.Agendas
import com.canerture.twittercloneapp.models.DirectMessage
import com.canerture.twittercloneapp.models.WhoToFollow
import com.squareup.picasso.Picasso

class DmAdapter(private val dmList: List<DirectMessage>):
    RecyclerView.Adapter<DmAdapter.DmCardDesign>() {

    inner class DmCardDesign(var dmCardBinding: DmCardBinding): RecyclerView.ViewHolder(dmCardBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DmCardDesign {
        val layoutInflater = LayoutInflater.from(parent.context)
        val dmCardBinding = DmCardBinding.inflate(layoutInflater, parent, false)

        return DmCardDesign(dmCardBinding)
    }

    override fun onBindViewHolder(holder: DmCardDesign, position: Int) {
        val dm = dmList[position]
        holder.dmCardBinding.dmObject = dm

        Picasso.get().load(dm.profilepicture).into(holder.dmCardBinding.profilePicture)

    }

    override fun getItemCount(): Int {
        return dmList.size
    }


}