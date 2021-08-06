package com.canerture.twittercloneapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.viewModels
import com.canerture.twittercloneapp.CustomBottomSheetDialogFragment
import com.canerture.twittercloneapp.R
import com.canerture.twittercloneapp.adapters.TweetsAdapter
import com.canerture.twittercloneapp.databinding.FragmentHomeBinding
import com.canerture.twittercloneapp.databinding.TweetBottomSheetBinding
import com.canerture.twittercloneapp.models.Tweet
import com.canerture.twittercloneapp.viewmodels.HomeFragmentViewModel
import com.google.android.material.bottomsheet.BottomSheetBehavior

class HomeFragment : Fragment(), TweetsAdapter.ClickedTweetListener {

    private lateinit var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>
    private lateinit var binding: FragmentHomeBinding
    private val viewModel : HomeFragmentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.allTweets()

        viewModel.tweetData.observe(viewLifecycleOwner) {
            println(it[0])
            val adapter = TweetsAdapter(it, this)
            binding.tweetsAdapter = adapter
        }

    }

    override fun onClickedTweetListener(data: Tweet, pos: Int) {

    }
}