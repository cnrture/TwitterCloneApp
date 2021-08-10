package com.canerture.twittercloneapp.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.canerture.twittercloneapp.R
import com.canerture.twittercloneapp.adapters.AgendasAdapter
import com.canerture.twittercloneapp.adapters.DmAdapter
import com.canerture.twittercloneapp.databinding.FragmentDirectMessageBinding
import com.canerture.twittercloneapp.viewmodels.DirectMessageViewModel
import com.canerture.twittercloneapp.viewmodels.SearchFragmentViewModel
import com.squareup.picasso.Picasso

class DirectMessageFragment : Fragment() {

    private lateinit var binding: FragmentDirectMessageBinding
    private val viewModel : DirectMessageViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_direct_message, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.directMessageFragmentObject = this

        val prefences = requireActivity().getSharedPreferences("com.canerture.twittercloneapp", Context.MODE_PRIVATE)
        Picasso.get().load(prefences.getString("profilepic", "null")).into(binding.profilePicture)

        viewModel.dmData.observe(viewLifecycleOwner) {
            val adapter = DmAdapter(it)
            binding.dmAdapter = adapter
        }

    }

    fun sendDmFAB() {

    }
}