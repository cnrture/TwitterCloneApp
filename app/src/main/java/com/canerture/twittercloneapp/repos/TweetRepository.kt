package com.canerture.twittercloneapp.repos

import android.content.ContentValues.TAG
import android.net.Uri
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.canerture.twittercloneapp.models.Tweet
import com.google.firebase.Timestamp
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import java.sql.Time

class TweetRepository {

    private var tweetData = MutableLiveData<List<Tweet>>()

    private val db = Firebase.firestore
    private var storage = Firebase.storage
    private var reference = storage.reference
    private var comment = MutableLiveData<Int>()

    init {
        tweetData = MutableLiveData()
        comment = MutableLiveData()
        storage = Firebase.storage
        reference = storage.reference
    }

    fun getTweetData() : MutableLiveData<List<Tweet>> {
        return tweetData
    }

    fun getCommentNumber() : MutableLiveData<Int> {
        return comment
    }

    fun allTweets() {
        db.collection("tweets").orderBy("time", Query.Direction.DESCENDING).addSnapshotListener { snapshot, exception ->
            if (exception != null) {
                Log.e("exception", exception.localizedMessage!!.toString())
            } else {

                if (snapshot != null) {
                    if (!snapshot.isEmpty) {

                        val tweetTempList = arrayListOf<Tweet>()

                        val documents = snapshot.documents
                        for (document in documents) {
                            val docId = document.id
                            val id = document.get("id") as String
                            val name = document.get("name") as String
                            val nickname = "@${document.get("nickname") as String}"
                            val profilepic = document.get("profilepic") as String
                            val tweetImage = document.get("tweetimage") as String
                            val tweetText = document.get("tweettext") as String
                            val comment = document.get("comment").toString()
                            val rt = document.get("rt").toString()
                            val fav = document.get("fav").toString()

                            val time = document.get("time") as Timestamp
                            val hoursDate = Timestamp.now().toDate().hours - time.toDate().hours
                            var date = ""

                            if (hoursDate == 0) {
                                date = "${Timestamp.now().toDate().minutes - time.toDate().minutes}dk"
                            }   else if (hoursDate > 0) {
                                date = "${hoursDate}s"
                            }   else if (hoursDate < 0) {
                                date = "${Timestamp.now().toDate().day - time.toDate().day}g"
                            }

                            tweetTempList.add(Tweet(docId, id, profilepic, name, nickname, date, tweetText, tweetImage, comment,rt,fav))

                        }

                        tweetData.value = tweetTempList
                    }
                }
            }
        }
    }

    fun addTweet(id: String, profilepic: String, name: String, nickname: String, tweetText: String, tweetImageName: String, tweetImage: Uri?) {

        if (tweetImage != null) {
            val imagesReference = reference.child("tweetimages").child(tweetImageName)

            imagesReference.putFile(tweetImage!!).addOnSuccessListener { taskSnapshot ->

                val uploadedPictureReference = storage.reference.child("tweetimages").child(tweetImageName)
                uploadedPictureReference.downloadUrl.addOnSuccessListener { uri ->
                    val tweetImageUrl = uri.toString()

                    val tweet = hashMapOf(
                        "id" to id,
                        "profilepic" to profilepic,
                        "name" to name,
                        "nickname" to nickname,
                        "time" to Timestamp.now(),
                        "tweettext" to tweetText,
                        "tweetimage" to tweetImageUrl,
                        "comment" to 0,
                        "rt" to 0,
                        "fav" to 0
                    )

                    db.collection("tweets")
                        .add(tweet)
                        .addOnSuccessListener {
                            Log.w("success", "added tweet")

                        }
                        .addOnFailureListener { e ->
                            Log.w("error", "Error adding document", e)
                        }
                }
            }
        }   else {

            val tweet = hashMapOf(
                "id" to id,
                "profilepic" to profilepic,
                "name" to name,
                "nickname" to nickname,
                "time" to Timestamp.now(),
                "tweettext" to tweetText,
                "tweetimage" to "",
                "comment" to 0,
                "rt" to 0,
                "fav" to 0
            )

            db.collection("tweets")
                .add(tweet)
                .addOnSuccessListener {
                    Log.w("success", "added tweet")

                }
                .addOnFailureListener { e ->
                    Log.w("error", "Error adding document", e)
                }
        }


    }

    fun commentTweet(tweetDocId:String, comment: Int) {
        val commentRef = db.collection("tweets").document(tweetDocId)
        commentRef.update("comment", comment +1)
            .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully updated!") }
            .addOnFailureListener { e -> Log.w(TAG, "Error updating document", e) }
    }

    fun rtTweet(tweetDocId:String, rt: Int) {
        val commentRef = db.collection("tweets").document(tweetDocId)
        commentRef.update("rt", rt +1)
            .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully updated!") }
            .addOnFailureListener { e -> Log.w(TAG, "Error updating document", e) }
    }

    fun favTweet(tweetDocId:String, fav: Int) {
        val commentRef = db.collection("tweets").document(tweetDocId)
        commentRef.update("fav", fav +1)
            .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully updated!") }
            .addOnFailureListener { e -> Log.w(TAG, "Error updating document", e) }
    }
}