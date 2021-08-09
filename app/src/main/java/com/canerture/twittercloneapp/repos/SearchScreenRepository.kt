package com.canerture.twittercloneapp.repos

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.canerture.twittercloneapp.models.Agendas
import com.canerture.twittercloneapp.models.WhatsGoingOn
import com.canerture.twittercloneapp.models.WhoToFollow
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage

class SearchScreenRepository {

    private var agendasData = MutableLiveData<List<Agendas>>()
    private var whatsGoingOnData = MutableLiveData<List<WhatsGoingOn>>()
    private var whoToFollowData = MutableLiveData<List<WhoToFollow>>()

    private val db = Firebase.firestore
    private var storage = Firebase.storage
    private var reference = storage.reference

    init {
        agendasData = MutableLiveData()
        whatsGoingOnData = MutableLiveData()
        whoToFollowData = MutableLiveData()
        storage = Firebase.storage
        reference = storage.reference
    }

    fun getAgendasData() : MutableLiveData<List<Agendas>> {
        return agendasData
    }

    fun getWhatsGoingOnData() : MutableLiveData<List<WhatsGoingOn>> {
        return whatsGoingOnData
    }

    fun getWhoToFollowData() : MutableLiveData<List<WhoToFollow>> {
        return whoToFollowData
    }

    fun getAgendas() {
        db.collection("agendas").orderBy("time", Query.Direction.DESCENDING).addSnapshotListener { snapshot, exception ->
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
        db.collection("whatsgoingon").orderBy("time", Query.Direction.DESCENDING).addSnapshotListener { snapshot, exception ->
            if (exception != null) {
                Log.e("exception", exception.localizedMessage!!.toString())
            } else {

                if (snapshot != null) {
                    if (!snapshot.isEmpty) {

                        val whatsGoingOnTempList = arrayListOf<WhatsGoingOn>()

                        val documents = snapshot.documents
                        for (document in documents) {
                            val subject = document.get("subject") as String
                            val content = document.get("content") as String
                            val image = document.get("image") as String

                            whatsGoingOnTempList.add(WhatsGoingOn(subject, content, image))

                        }

                        whatsGoingOnData.value = whatsGoingOnTempList
                    }
                }
            }
        }
    }

    fun getWhoToFollow() {
        db.collection("whotofollow").orderBy("time", Query.Direction.DESCENDING).addSnapshotListener { snapshot, exception ->
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
                            val profilpicture = document.get("profilpicture") as String

                            whoToFollowTempList.add(WhoToFollow(id, name, profilpicture))

                        }

                        whoToFollowData.value = whoToFollowTempList
                    }
                }
            }
        }
    }

}