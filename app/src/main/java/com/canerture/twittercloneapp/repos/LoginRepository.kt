package com.canerture.twittercloneapp.repos

import android.content.ContentValues.TAG
import android.net.Uri
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.canerture.twittercloneapp.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage

class LoginRepository {

    private var userData = MutableLiveData<User?>()
    private var passwordChangeCheck = MutableLiveData<Int>()

    private var auth: FirebaseAuth
    private val db = Firebase.firestore

    init {
        userData = MutableLiveData()
        passwordChangeCheck = MutableLiveData()

        auth = Firebase.auth
    }

    fun getUserData() : MutableLiveData<User?> {
        return userData
    }

    fun passwordChangeCheck() : MutableLiveData<Int>{
        return passwordChangeCheck
    }

    fun signUp(name: String, nickname: String, phone: String, birthday: String, email: String, password: String, imageName: String, selectedPicture: Uri?) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                if (task.isSuccessful) {

                    Log.d("Sign Up", "createUserWithEmail:success")

                    val currentUser = auth.currentUser
                    currentUser?.let { firebaseUser ->

                        val storage = Firebase.storage
                        val reference = storage.reference
                        val imagesReference = reference.child("profilpictures").child(imageName)

                        imagesReference.putFile(selectedPicture!!).addOnSuccessListener { taskSnapshot ->

                            val uploadedPictureReference = storage.reference.child("profilpictures").child(imageName)
                            uploadedPictureReference.downloadUrl.addOnSuccessListener { uri ->
                                val profilPicUrl = uri.toString()

                                val user = hashMapOf(
                                    "id" to firebaseUser.uid,
                                    "name" to name,
                                    "nickname" to nickname,
                                    "phone" to phone,
                                    "birthday" to birthday,
                                    "profilepic" to profilPicUrl
                                )

                                db.collection("users").document(firebaseUser.uid)
                                    .set(user)
                                    .addOnSuccessListener {
                                        Log.d("success", "DocumentSnapshot added with ID: ${firebaseUser.uid}")
                                    }
                                    .addOnFailureListener { e ->
                                        Log.w("error", "Error adding document", e)
                                    }
                            }
                        }



                    }
                } else {
                    Log.w("Sign Up", "createUserWithEmail:failure", task.exception)
                }
            }
    }

    fun signIn(email: String, password: String) {

        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                if (task.isSuccessful) {

                    val currentUser = auth.currentUser

                    val docRef = db.collection("users").document(currentUser!!.uid)
                    docRef.get().addOnSuccessListener { document ->
                            if (document != null) {
                                userData.value = User(document.data?.get("id").toString(), document.data?.get("name").toString(), document.data?.get("nickname").toString(), document.data?.get("phone").toString(), document.data?.get("birthday").toString(), document.data?.get("profilepic").toString())
                                Log.d(TAG, "DocumentSnapshot data: ${document.data}")
                            } else {
                                userData.value = null
                                Log.d(TAG, "No such document")
                            }
                        }.addOnFailureListener { exception ->
                            Log.d(TAG, "get failed with ", exception)
                        }

                    Log.d("Sign In", "signInWithEmail:success")

                } else {
                    userData.value = null
                    Log.w("Sing In", "signInWithEmail:failure", task.exception)
                }
            }
    }

    fun signOut() {
        Firebase.auth.signOut()
    }

    fun searchUser(emailphonenickname: String) {

    }

    fun passwordChange(id: String, password: String) {

    }

}