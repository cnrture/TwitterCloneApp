package com.canerture.twittercloneapp.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.canerture.twittercloneapp.CustomBottomSheetDialogFragment
import com.canerture.twittercloneapp.R
import com.canerture.twittercloneapp.adapters.TweetsAdapter
import com.canerture.twittercloneapp.databinding.FragmentHomeBinding
import com.canerture.twittercloneapp.databinding.TweetBottomSheetBinding
import com.canerture.twittercloneapp.models.Tweet
import com.canerture.twittercloneapp.viewmodels.HomeFragmentViewModel
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.squareup.picasso.Picasso

class HomeFragment : Fragment(), TweetsAdapter.ClickedTweetListener {

    private lateinit var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>
    private lateinit var binding: FragmentHomeBinding
    private val viewModel : HomeFragmentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.homeFragmentObject = this

        val prefences = requireActivity().getSharedPreferences("com.canerture.twittercloneapp", Context.MODE_PRIVATE)
        Picasso.get().load(prefences.getString("profilepic", "null")).into(binding.profilePicture)

        viewModel.allTweets()

        viewModel.tweetData.observe(viewLifecycleOwner) {
            val adapter = TweetsAdapter(it, this)
            println(it)
            binding.tweetsAdapter = adapter
        }

    }

    fun addTweetFAB() {
        Navigation.findNavController(requireView()).navigate(R.id.action_homeFragment_to_addTweetFragment)
    }

    override fun onClickedTweetListener(data: Tweet, pos: Int) {

    }
}