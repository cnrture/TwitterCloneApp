package com.canerture.twittercloneapp.retrofit

import com.canerture.booksapp.retrofit.RetrofitClient
import com.canerture.booksapp.retrofit.TweetsDAOInterface
import com.canerture.booksapp.retrofit.UsersDAOInterface

class ApiUtils {

    companion object {
        private const val BASE_URL = "http://twitterclone.canerture.com/"

        fun getTweetsDAOInterface(): TweetsDAOInterface {
            return RetrofitClient.getClient(BASE_URL).create(TweetsDAOInterface::class.java)
        }

        fun getUserDAOInterface(): UsersDAOInterface {
            return RetrofitClient.getClient(BASE_URL).create(UsersDAOInterface::class.java)
        }
    }

}