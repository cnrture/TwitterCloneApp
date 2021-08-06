package com.canerture.twittercloneapp.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation
import com.canerture.twittercloneapp.fragments.SignInFragmentDirections
import com.canerture.twittercloneapp.models.User
import com.canerture.twittercloneapp.repos.LoginRepository
import com.google.android.material.snackbar.Snackbar

class SignInForgetPasswordFragmentViewModel : ViewModel() {

    private var loginrepo = LoginRepository()
    var passwordChangeCheck = MutableLiveData<Int>()
    var userData = MutableLiveData<List<User>?>()

    init {
        passwordChangeCheck = loginrepo.passwordChangeCheck()
        userData = loginrepo.getUserData()
    }

    fun searchUser(emailphonenickname: String) {
        loginrepo.searchUser(emailphonenickname)
    }

    fun passwordChange(id: Int, password: String) {
        loginrepo.passwordChange(id, password)
    }

}