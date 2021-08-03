package com.canerture.twittercloneapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.canerture.twittercloneapp.R
import com.canerture.twittercloneapp.databinding.FragmentSignInSecondPageBinding

class SignInSecondPageFragment : Fragment() {

    private lateinit var binding : FragmentSignInSecondPageBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignInSecondPageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.cancel.setOnClickListener {
            requireActivity().finish()
        }

        binding.forgetPasswordText.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_signInSecondPageFragment_to_signInForgetPasswordFragment)
        }

    }
}