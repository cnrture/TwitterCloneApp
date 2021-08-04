package com.canerture.twittercloneapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.canerture.twittercloneapp.R
import com.canerture.twittercloneapp.databinding.FragmentSignInBinding
import com.canerture.twittercloneapp.viewmodels.SignInViewModel
import com.google.android.material.snackbar.Snackbar

class SignInFragment : Fragment() {

    private lateinit var binding: FragmentSignInBinding
    private val viewModel : SignInViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignInBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.signInFragmentObject = this

    }

    fun nextButton(emailphonenickname: String) {
        if (emailphonenickname.isNotEmpty()) {
            val action = SignInFragmentDirections.actionSignInFragmentToSignInSecondPageFragment(emailphonenickname)
            Navigation.findNavController(requireView()).navigate(action)
        }   else {
            Snackbar.make(requireView(), "Wrong E-Mail, Phone or Nickname", 1000).show()
        }
    }

    fun cancel() {
        requireActivity().finish()
    }

    fun forgetPasswordText() {
        Navigation.findNavController(requireView()).navigate(R.id.action_signInFragment_to_signInForgetPasswordFragment)
    }
}