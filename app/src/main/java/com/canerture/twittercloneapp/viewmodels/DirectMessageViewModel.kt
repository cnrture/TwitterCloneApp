package com.canerture.twittercloneapp.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.canerture.twittercloneapp.models.*
import com.canerture.twittercloneapp.repos.DirectMessageRepository
import com.canerture.twittercloneapp.repos.SearchScreenRepository

class DirectMessageViewModel: ViewModel() {

    private var dmrepo = DirectMessageRepository()

    var dmData = MutableLiveData<List<DirectMessage>>()

    init {
        getDmData()
    }

    private fun getDmData() {
        dmrepo.getDirectMessages()
        dmData = dmrepo.getDmData()
    }

}