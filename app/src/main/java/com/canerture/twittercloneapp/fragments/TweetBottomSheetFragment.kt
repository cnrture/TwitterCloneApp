package com.canerture.twittercloneapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.canerture.twittercloneapp.databinding.FragmentTweetBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class TweetBottomSheetFragment: BottomSheetDialogFragment() {

    private lateinit var binding: FragmentTweetBottomSheetBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTweetBottomSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



    }

}