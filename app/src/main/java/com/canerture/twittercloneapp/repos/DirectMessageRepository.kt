package com.canerture.twittercloneapp.repos

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.canerture.twittercloneapp.models.*
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage

class DirectMessageRepository {

    private var dmData = MutableLiveData<List<DirectMessage>>()

    private val db = Firebase.firestore
    private var storage = Firebase.storage
    private var reference = storage.reference

    init {
        dmData = MutableLiveData()
        storage = Firebase.storage
        reference = storage.reference
    }

    fun getDmData() : MutableLiveData<List<DirectMessage>> {
        return dmData
    }

    fun getDirectMessages() {
        db.collection("directmessages").addSnapshotListener { snapshot, exception ->
            if (exception != null) {
                Log.e("exception", exception.localizedMessage!!.toString())
            } else {

                if (snapshot != null) {
                    if (!snapshot.isEmpty) {

                        val dmTempList = arrayListOf<DirectMessage>()

                        val documents = snapshot.documents
                        for (document in documents) {
                            val profilepicture = document.get("profilepicture") as String
                            val name = document.get("name") as String
                            val nickname = document.get("nickname") as String
                            val message = document.get("message") as String
                            val date = document.get("date") as String

                            dmTempList.add(DirectMessage(profilepicture, name, nickname, message, date))

                        }

                        dmData.value = dmTempList
                    }
                }
            }
        }
    }
}