package com.canerture.twittercloneapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.canerture.twittercloneapp.databinding.TweetBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class CustomBottomSheetDialogFragment : BottomSheetDialogFragment() {

    private lateinit var binding: TweetBottomSheetBinding

    companion object {

        const val TAG = "CustomBottomSheetDialogFragment"

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = TweetBottomSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)



    }
}