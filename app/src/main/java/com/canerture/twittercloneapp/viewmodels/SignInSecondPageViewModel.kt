package com.canerture.twittercloneapp.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation
import com.canerture.twittercloneapp.fragments.SignInFragmentDirections
import com.canerture.twittercloneapp.models.User
import com.canerture.twittercloneapp.repos.LoginRepository
import com.google.android.material.snackbar.Snackbar

class SignInSecondPageViewModel : ViewModel() {

    private var loginrepo = LoginRepository()
    var userData = MutableLiveData<User?>()

    init {
        userData = loginrepo.getUserData()
    }

    fun signIn(emailphonenickname: String, password: String) {
        loginrepo.signIn(emailphonenickname, password)
    }

}