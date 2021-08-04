package com.canerture.twittercloneapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.canerture.twittercloneapp.R
import com.canerture.twittercloneapp.databinding.FragmentSignUpBinding
import com.canerture.twittercloneapp.viewmodels.SignUpViewModel
import com.google.android.material.snackbar.Snackbar

class SignUpFragment : Fragment() {

    private lateinit var binding: FragmentSignUpBinding
    private val viewModel : SignUpViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignUpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.signUpFragmentObject = this

        binding.back.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_signUpFragment_to_loginFragment)
        }

        viewModel.signUpCheck.observe(viewLifecycleOwner) {
            if (it == 1) {
                Snackbar.make(view, "Başarıyla kayıt oldunuz!", 1000).show()
            }   else {
                Snackbar.make(view, "Bir şeyler ters gitti!", 1000).show()
            }
        }

    }

    fun signUp(name: String, nickname: String, phone: String, birthday: String, email: String, password: String) {
        if (name.isNotEmpty() && nickname.isNotEmpty() && phone.isNotEmpty() && birthday.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()) {
            viewModel.signUp(name, nickname, phone, birthday, email, password)
        }   else {
            Snackbar.make(requireView(), "Lütfen tüm boşlukları doldurun!", 1000).show()
        }
    }
}