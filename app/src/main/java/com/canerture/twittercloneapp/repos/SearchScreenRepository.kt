package com.canerture.twittercloneapp.repos

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.canerture.twittercloneapp.models.Agendas
import com.canerture.twittercloneapp.models.SearchTopContent
import com.canerture.twittercloneapp.models.WhatsGoingOn
import com.canerture.twittercloneapp.models.WhoToFollow
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage

class SearchScreenRepository {

    private var agendasData = MutableLiveData<List<Agendas>>()
    private var whatsGoingOnData = MutableLiveData<WhatsGoingOn>()
    private var whoToFollowData = MutableLiveData<List<WhoToFollow>>()
    private var searchTopContentData = MutableLiveData<SearchTopContent>()

    private val db = Firebase.firestore
    private var storage = Firebase.storage
    private var reference = storage.reference

    init {
        agendasData = MutableLiveData()
        whatsGoingOnData = MutableLiveData()
        whoToFollowData = MutableLiveData()
        searchTopContentData = MutableLiveData()
        storage = Firebase.storage
        reference = storage.reference
    }

    fun getAgendasData() : MutableLiveData<List<Agendas>> {
        return agendasData
    }

    fun getWhatsGoingOnData() : MutableLiveData<WhatsGoingOn> {
        return whatsGoingOnData
    }

    fun getWhoToFollowData() : MutableLiveData<List<WhoToFollow>> {
        return whoToFollowData
    }

    fun getSearchTopContentData() : MutableLiveData<SearchTopContent> {
        return searchTopContentData
    }

    fun getAgendas() {
        db.collection("agendas").addSnapshotListener { snapshot, exception ->
            if (exception != null) {
                Log.e("exception", exception.localizedMessage!!.toString())
            } else {

                if (snapshot != null) {
                    if (!snapshot.isEmpty) {

                        val agendasTempList = arrayListOf<Agendas>()

                        val documents = snapshot.documents
                        for (document in documents) {
                            val category = document.get("category") as String
                            val hashtag = document.get("hashtag") as String
                            val tweetnumber = document.get("tweetnumber") as String

                            agendasTempList.add(Agendas(category, hashtag, tweetnumber))

                        }

                        agendasData.value = agendasTempList
                    }
                }
            }
        }
    }

    fun getWhatsGoingOn() {
        db.collection("whatsgoingon").addSnapshotListener { snapshot, exception ->
            if (exception != null) {
                Log.e("exception", exception.localizedMessage!!.toString())
            } else {

                if (snapshot != null) {
                    if (!snapshot.isEmpty) {

                        val documents = snapshot.documents
                        for (document in documents) {
                            val subject = document.get("subject") as String
                            val content = document.get("content") as String
                            val image = document.get("image") as String

                            whatsGoingOnData.value = WhatsGoingOn(subject, content, image)

                        }
                    }
                }
            }
        }
    }

    fun getWhoToFollow() {
        db.collection("whotofollow").addSnapshotListener { snapshot, exception ->
            if (exception != null) {
                Log.e("exception", exception.localizedMessage!!.toString())
            } else {

                if (snapshot != null) {
                    if (!snapshot.isEmpty) {

                        val whoToFollowTempList = arrayListOf<WhoToFollow>()

                        val documents = snapshot.documents
                        for (document in documents) {
                            val id = document.get("id") as String
                            val name = document.get("name") as String
                            val profilepicture = document.get("profilepicture") as String

                            whoToFollowTempList.add(WhoToFollow(id, name, profilepicture))

                        }

                        whoToFollowData.value = whoToFollowTempList
                    }
                }
            }
        }
    }

    fun getSearchTopContent() {
        db.collection("searchtopcontent").addSnapshotListener { snapshot, exception ->
            if (exception != null) {
                Log.e("exception", exception.localizedMessage!!.toString())
            } else {

                if (snapshot != null) {
                    if (!snapshot.isEmpty) {

                        val documents = snapshot.documents
                        for (document in documents) {
                            val subject = document.get("subject") as String
                            val content = document.get("content") as String
                            val image = document.get("image") as String

                            searchTopContentData.value = SearchTopContent(subject, content, image)

                        }
                    }
                }
            }
        }
    }

}