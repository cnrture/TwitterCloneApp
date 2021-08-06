package com.canerture.twittercloneapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.canerture.twittercloneapp.databinding.StoryCardBinding
import com.canerture.twittercloneapp.models.Story

class StoryAdapter(private val storiesList: List<Story>, private val clickedStoryListener: ClickedStoryListener):
    RecyclerView.Adapter<StoryAdapter.BookCardDesign>() {

    inner class BookCardDesign(storyCardBinding: StoryCardBinding): RecyclerView.ViewHolder(storyCardBinding.root) {
        var bookCardBinding:StoryCardBinding
        init {
            this.bookCardBinding = storyCardBinding
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookCardDesign {
        val layoutInflater = LayoutInflater.from(parent.context)
        val bookCardBinding = StoryCardBinding.inflate(layoutInflater, parent, false)

        return BookCardDesign(bookCardBinding)
    }

    override fun onBindViewHolder(holder: BookCardDesign, position: Int) {
        val story = storiesList[position]

//        holder.storyCardBinding.bookImageView.setOnClickListener {
//            clickedStoryListener.onClickedStoryListener(story, position)
//        }
    }

    override fun getItemCount(): Int {
        return storiesList.size
    }

    interface ClickedStoryListener {
        fun onClickedStoryListener(data: Story, pos: Int)
    }


}