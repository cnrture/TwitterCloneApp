package com.canerture.twittercloneapp.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.canerture.twittercloneapp.models.Agendas
import com.canerture.twittercloneapp.models.SearchTopContent
import com.canerture.twittercloneapp.models.WhatsGoingOn
import com.canerture.twittercloneapp.models.WhoToFollow
import com.canerture.twittercloneapp.repos.SearchScreenRepository

class SearchFragmentViewModel: ViewModel() {

    private var searchrepo = SearchScreenRepository()

    var agendasData = MutableLiveData<List<Agendas>>()
    var whatsGoingOnData = MutableLiveData<WhatsGoingOn>()
    var whoToFollowData = MutableLiveData<List<WhoToFollow>>()
    var searchTopContentData = MutableLiveData<SearchTopContent>()

    init {
        getAgendasData()
        getWhatsGoingOnData()
        getWhoToFollowData()
        getSearchTopContentData()
    }

    private fun getAgendasData() {
        searchrepo.getAgendas()
        agendasData = searchrepo.getAgendasData()
    }

    private fun getWhatsGoingOnData() {
        searchrepo.getWhatsGoingOn()
        whatsGoingOnData = searchrepo.getWhatsGoingOnData()
    }

    private fun getWhoToFollowData() {
        searchrepo.getWhoToFollow()
        whoToFollowData = searchrepo.getWhoToFollowData()
    }

    private fun getSearchTopContentData() {
        searchrepo.getSearchTopContent()
        searchTopContentData = searchrepo.getSearchTopContentData()
    }

}