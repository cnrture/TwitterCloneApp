package com.canerture.twittercloneapp.viewmodels

import android.net.Uri
import androidx.lifecycle.ViewModel
import com.canerture.twittercloneapp.repos.LoginRepository

class SignUpViewModel: ViewModel() {

    private var loginrepo = LoginRepository()

    fun signUp(name: String, nickname: String, phone: String, birthday: String, email: String, password: String, imageName: String, selectedPicture: Uri?) {
        loginrepo.signUp(name, nickname, phone, birthday, email, password, imageName, selectedPicture)
    }

}