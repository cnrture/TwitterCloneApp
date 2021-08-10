package com.canerture.twittercloneapp.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.canerture.twittercloneapp.R
import com.canerture.twittercloneapp.adapters.AgendasAdapter
import com.canerture.twittercloneapp.adapters.WhoToFollowAdapter
import com.canerture.twittercloneapp.databinding.FragmentSearchBinding
import com.canerture.twittercloneapp.viewmodels.SearchFragmentViewModel
import com.squareup.picasso.Picasso

class SearchFragment : Fragment() {

    private lateinit var binding : FragmentSearchBinding
    private val viewModel : SearchFragmentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.searchFragmentObject = this

        val prefences = requireActivity().getSharedPreferences("com.canerture.twittercloneapp", Context.MODE_PRIVATE)
        Picasso.get().load(prefences.getString("profilepic", "null")).into(binding.profilePicture)

        viewModel.agendasData.observe(viewLifecycleOwner) {
            val adapter = AgendasAdapter(it)
            binding.agendasAdapter = adapter
        }

        viewModel.whoToFollowData.observe(viewLifecycleOwner) {
            val adapter = WhoToFollowAdapter(it)
            binding.whoToFollowAdapter = adapter
        }

        viewModel.whatsGoingOnData.observe(viewLifecycleOwner) {
            binding.whatsGoingOnObject = it
            Picasso.get().load(it.image).into(binding.whatsGoingOnImage)
        }

        viewModel.searchTopContentData.observe(viewLifecycleOwner) {
            binding.searchTopContentObject = it
            Picasso.get().load(it.image).into(binding.topContentImage)
        }
    }

    fun addTweetFAB() {
        Navigation.findNavController(requireView()).navigate(R.id.action_searchFragment_to_addTweetFragment)
    }
}