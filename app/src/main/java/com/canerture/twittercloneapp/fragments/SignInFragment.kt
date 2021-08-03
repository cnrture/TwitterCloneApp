package com.canerture.twittercloneapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.canerture.twittercloneapp.R
import com.canerture.twittercloneapp.databinding.FragmentSignInBinding

class SignInFragment : Fragment() {

    private lateinit var binding: FragmentSignInBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignInBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.cancel.setOnClickListener {
            requireActivity().finish()
        }

        binding.nextButton.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_signInFragment_to_signInSecondPageFragment)
        }

        binding.forgetPasswordText.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_signInFragment_to_signInForgetPasswordFragment)
        }

    }
}