package com.canerture.twittercloneapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.canerture.twittercloneapp.R
import com.canerture.twittercloneapp.databinding.FragmentSignInForgetPasswordBinding
import com.canerture.twittercloneapp.viewmodels.SignInForgetPasswordFragmentViewModel
import com.google.android.material.snackbar.Snackbar

class SignInForgetPasswordFragment : Fragment() {

    private lateinit var binding : FragmentSignInForgetPasswordBinding
    private val viewModel : SignInForgetPasswordFragmentViewModel by viewModels()

    private var id : Int? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sign_in_forget_password, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.signInForgetPasswordFragmentObject = this

        viewModel.userData.observe(viewLifecycleOwner) {
            if (it != null) {
                binding.emailphonenicknameText.text = "Merhaba ${it.nickname}"
                binding.id = it.id
                binding.searchButton.visibility = View.INVISIBLE
                binding.emailphonenicknameEditText.visibility = View.INVISIBLE
                binding.savePasswordButton.visibility = View.VISIBLE
                binding.passwordEditText.visibility = View.VISIBLE
            }   else {
                Snackbar.make(view, "Kullanıcı bulunamadı!", 1000).show()
            }
        }

        viewModel.passwordChangeCheck.observe(viewLifecycleOwner) {
            if (it == 1) {
                Snackbar.make(view, "Şifreniz başarıyla değiştirildi!", 1000).show()
                Navigation.findNavController(view).navigate(R.id.action_signInForgetPasswordFragment_to_signInFragment)
            }   else {
                Snackbar.make(view, "Bir hata oluştu!", 1000).show()
            }
        }

    }

    fun cancel() {
        requireActivity().finish()
    }

    fun searchUser(emailphonenickname: String) {
        viewModel.searchUser(emailphonenickname)
    }

    fun passwordChange(id: String, password: String) {
        viewModel.passwordChange(id, password)
    }
}