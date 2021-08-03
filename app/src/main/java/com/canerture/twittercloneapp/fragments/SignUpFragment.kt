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

        binding.back.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_signUpFragment_to_loginFragment)
        }

        binding.signUpFragmentObject = this
        binding.signUpViewModeltObject = viewModel

    }
}