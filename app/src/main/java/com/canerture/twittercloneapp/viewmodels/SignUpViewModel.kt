package com.canerture.twittercloneapp.viewmodels

import androidx.lifecycle.ViewModel
import com.canerture.twittercloneapp.repos.LoginRepository

class SignUpViewModel: ViewModel() {

    private var loginrepo = LoginRepository()

    init {

    }

    fun signUp(name: String, nickname: String, phone: String, birthday: String, email: String, password: String) {
        loginrepo.signUp(name, nickname, phone, birthday, email, password)
    }

}