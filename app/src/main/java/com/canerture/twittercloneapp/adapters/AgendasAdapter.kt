package com.canerture.twittercloneapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.canerture.twittercloneapp.databinding.AgendaCardBinding
import com.canerture.twittercloneapp.databinding.TweetCardBinding
import com.canerture.twittercloneapp.models.Agendas

class AgendasAdapter(private val agendasList: List<Agendas>):
    RecyclerView.Adapter<AgendasAdapter.AgendaCardDesign>() {

    inner class AgendaCardDesign(var agendaCardBinding: AgendaCardBinding): RecyclerView.ViewHolder(agendaCardBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AgendaCardDesign {
        val layoutInflater = LayoutInflater.from(parent.context)
        val agendaCardBinding = AgendaCardBinding.inflate(layoutInflater, parent, false)

        return AgendaCardDesign(agendaCardBinding)
    }

    override fun onBindViewHolder(holder: AgendaCardDesign, position: Int) {
        val agenda = agendasList[position]
        holder.agendaCardBinding.agendaObject = agenda
        println(agenda)
        println(2)
    }

    override fun getItemCount(): Int {
        return agendasList.size
    }


}