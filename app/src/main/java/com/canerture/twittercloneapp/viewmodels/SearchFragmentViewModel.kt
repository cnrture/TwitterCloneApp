package com.canerture.twittercloneapp.viewmodels

import androidx.lifecycle.MutableLiveData
import com.canerture.twittercloneapp.models.Agendas
import com.canerture.twittercloneapp.models.WhatsGoingOn
import com.canerture.twittercloneapp.models.WhoToFollow
import com.canerture.twittercloneapp.repos.SearchScreenRepository

class SearchFragmentViewModel {

    private var searchrepo = SearchScreenRepository()

    private var agendasData = MutableLiveData<List<Agendas>>()
    private var whatsGoingOnData = MutableLiveData<List<WhatsGoingOn>>()
    private var whoToFollowData = MutableLiveData<List<WhoToFollow>>()

    init {
        agendasData = searchrepo.getAgendasData()
        whatsGoingOnData = searchrepo.getWhatsGoingOnData()
        whoToFollowData = searchrepo.getWhoToFollowData()
    }

    fun getAgendasData() {
        searchrepo.getAgendas()
    }

    fun getWhatsGoingOnData() {
        searchrepo.getWhatsGoingOn()
    }

    fun getWhoToFollowData() {
        searchrepo.getWhoToFollow()
    }

}