package com.canerture.twittercloneapp.repos

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.canerture.twittercloneapp.retrofit.UsersDAOInterface
import com.canerture.twittercloneapp.models.User
import com.canerture.twittercloneapp.response.CRUDResponse
import com.canerture.twittercloneapp.response.UserResponse
import com.canerture.twittercloneapp.retrofit.ApiUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginRepository {

    private var userData = MutableLiveData<List<User>?>()
    private var signUpCheck = MutableLiveData<Int>()
    private var passwordChangeCheck = MutableLiveData<Int>()
    private var usersDIF : UsersDAOInterface = ApiUtils.getUserDAOInterface()

    init {
        userData = MutableLiveData()
        signUpCheck = MutableLiveData()
        passwordChangeCheck = MutableLiveData()
    }

    fun getUserData() : MutableLiveData<List<User>?> {
        return userData
    }

    fun signUpCheck() : MutableLiveData<Int>{
        return signUpCheck
    }

    fun passwordChangeCheck() : MutableLiveData<Int>{
        return passwordChangeCheck
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
                Log.e("Sign Up Error", t.localizedMessage!!.toString())
                signUpCheck.value = 0
            }

        })
    }

    fun signIn(emailphonenickname: String, password: String) {
        usersDIF.signIn(emailphonenickname, password).enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                val tempUser = response.body()!!.users

                if (tempUser[0].signincheck == 1) {
                    userData.value = tempUser
                }   else {
                    userData.value = null
                }

            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                Log.e("Sign In Error", t.localizedMessage!!.toString())
                userData.value = null
            }

        })
    }

    fun signOut() {

    }

    fun searchUser(emailphonenickname: String) {
        usersDIF.searchUser(emailphonenickname).enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                val tempUser = response.body()!!.users
                if (tempUser[0].searchcheck == 1) {
                    userData.value = tempUser
                }   else {
                    userData.value = null
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                Log.e("Search User Error", t.localizedMessage!!.toString())
            }

        })
    }

    fun passwordChange(id: Int, password: String) {
        usersDIF.passwordChange(id, password).enqueue(object : Callback<CRUDResponse> {
            override fun onResponse(call: Call<CRUDResponse>, response: Response<CRUDResponse>) {
                val temp = response.body()!!.success
                if (temp == 1) {
                    passwordChangeCheck.value = 1
                }   else {
                    passwordChangeCheck.value = 0
                }
            }

            override fun onFailure(call: Call<CRUDResponse>, t: Throwable) {
                Log.e("Sign In Error", t.localizedMessage!!.toString())
            }

        })
    }

}