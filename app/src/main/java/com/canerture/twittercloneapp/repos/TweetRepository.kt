package com.canerture.twittercloneapp.repos

import android.net.Uri
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.canerture.twittercloneapp.models.Tweet
import com.canerture.twittercloneapp.models.User
import com.canerture.twittercloneapp.response.CRUDResponse
import com.canerture.twittercloneapp.response.TweetResponse
import com.canerture.twittercloneapp.response.UserResponse
import com.canerture.twittercloneapp.retrofit.ApiUtils
import com.canerture.twittercloneapp.retrofit.TweetsDAOInterface
import com.canerture.twittercloneapp.retrofit.UsersDAOInterface
import com.google.firebase.Timestamp
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class TweetRepository {

    private var tweetData = MutableLiveData<List<Tweet>>()

    private val db = Firebase.firestore

    init {
        tweetData = MutableLiveData()
    }

    fun getTweetData() : MutableLiveData<List<Tweet>> {
        return tweetData
    }

    fun allTweets() {
        db.collection("tweets").orderBy("time", Query.Direction.DESCENDING).addSnapshotListener { snapshot, exception ->
            if (exception != null) {
                Log.e("exception", exception.localizedMessage!!.toString())
            } else {

                if (snapshot != null) {
                    if (!snapshot.isEmpty) {

                        val documents = snapshot.documents
                        for (document in documents) {
                            val id = document.get("id") as String
                            val name = document.get("name") as String
                            val nickname = "@${document.get("nickname") as String}"
                            val profilepic = document.get("profilepic") as String
                            val time = document.get("time") as Timestamp
                            val date = time.toDate()
                            val tweetImage = document.get("tweetimage") as String
                            val tweetText = document.get("tweettext") as String

                            println(document.get("name") as String)
                            println("olduu")

                            tweetData.value = listOf(Tweet(id, profilepic, name, nickname, date, tweetText, tweetImage, 0,0,0))

                        }
                    }
                }
            }
        }
    }

    fun addTweet(id: String, profilepic: String, name: String, nickname: String, tweetText: String, tweetImageName: String, tweetImage: Uri?) {

        val storage = Firebase.storage
        val reference = storage.reference
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
                    "tweetimage" to tweetImageUrl
                )

                db.collection("tweets").document(id)
                    .set(tweet)
                    .addOnSuccessListener {
                        Log.w("success", "added tweet")

                    }
                    .addOnFailureListener { e ->
                        Log.w("error", "Error adding document", e)
                    }
            }
        }
    }
}