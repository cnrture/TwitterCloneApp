package com.canerture.twittercloneapp.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.canerture.twittercloneapp.R
import com.canerture.twittercloneapp.adapters.ViewPagerAdapter
import com.canerture.twittercloneapp.databinding.FragmentNotificationsBinding
import com.google.android.material.tabs.TabLayoutMediator
import com.squareup.picasso.Picasso

class NotificationsFragment : Fragment() {

    private lateinit var binding : FragmentNotificationsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_notifications, container, false)
        return inflater.inflate(R.layout.fragment_notifications, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val prefences = requireActivity().getSharedPreferences("com.canerture.twittercloneapp", Context.MODE_PRIVATE)
        Picasso.get().load(prefences.getString("profilepic", "null")).into(binding.profilePicture)


    }

}