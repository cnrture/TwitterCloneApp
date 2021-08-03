package com.canerture.twittercloneapp.repos

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.canerture.booksapp.retrofit.UsersDAOInterface
import com.canerture.twittercloneapp.models.User
import com.canerture.twittercloneapp.response.CRUDResponse
import com.canerture.twittercloneapp.retrofit.ApiUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginRepository {

    private var userData = MutableLiveData<List<User>>()
    private var signUpCheck = MutableLiveData<Int>()
    private var usersDIF : UsersDAOInterface = ApiUtils.getUserDAOInterface()

    init {
        userData = MutableLiveData()
        signUpCheck = MutableLiveData()
    }

    fun getUserData() : MutableLiveData<List<User>> {
        return userData
    }

    fun signUp(name: String, nickname: String, phone: String, birthday: String, email: String, password: String) {
        usersDIF.signUp(name, nickname, phone, birthday, email, password).enqueue(object : Callback<CRUDResponse> {
            override fun onResponse(call: Call<CRUDResponse>, response: Response<CRUDResponse>) {
                if (response.body()?.success == 1) {
                    signUpCheck.value = 1
                }   else {
                    signUpCheck.value = 0
                }
            }

            override fun onFailure(call: Call<CRUDResponse>, t: Throwable) {
                Log.e("Error", t.localizedMessage!!.toString())
            }

        })
    }

    fun signIn() {

    }

    fun signOut() {

    }

}