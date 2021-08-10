package com.canerture.twittercloneapp.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.canerture.twittercloneapp.fragments.AllNotificationsFragment
import com.canerture.twittercloneapp.fragments.MentionNotificationFragment

class ViewPagerAdapter(fragment: Fragment) :
    FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> return AllNotificationsFragment()
            1 -> return MentionNotificationFragment()
        }
        return AllNotificationsFragment()
    }
}