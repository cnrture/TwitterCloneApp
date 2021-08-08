package com.canerture.twittercloneapp.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.canerture.twittercloneapp.MainActivity
import com.canerture.twittercloneapp.R
import com.canerture.twittercloneapp.databinding.FragmentSignInSecondPageBinding
import com.canerture.twittercloneapp.viewmodels.SignInSecondPageViewModel
import com.canerture.twittercloneapp.viewmodels.SignInViewModel
import com.canerture.twittercloneapp.viewmodels.SignUpViewModel
import com.google.android.material.snackbar.Snackbar

class SignInSecondPageFragment : Fragment() {

    private lateinit var binding : FragmentSignInSecondPageBinding
    private val viewModel : SignInSecondPageViewModel by viewModels()
    val args: SignInSecondPageFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sign_in_second_page, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.signInSecondPageFragmentObject = this

        binding.emailphonenickname = args.emailphonenickname

        val prefences = requireActivity().getSharedPreferences("com.canerture.twittercloneapp", Context.MODE_PRIVATE)
        val editor = prefences!!.edit()

        viewModel.userData.observe(viewLifecycleOwner) {
            if (it != null) {
                val intent = Intent(requireActivity(), MainActivity::class.java)
                startActivity(intent)
                editor.putString("id", it.id)
                editor.putString("name", it.name)
                editor.putString("nickname", it.nickname)
                editor.putString("phone", it.phone)
                editor.putString("birthday", it.birthday)
                editor.putString("profilepic", it.profilpic)
                editor.apply()
            }   else {
                Toast.makeText(context, "Kullanıcı bulunamadı!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun signInButton(emailphonenickname: String, password: String) {
        if (emailphonenickname.isNotEmpty() && password.isNotEmpty()) {
            viewModel.signIn(emailphonenickname, password)
        }   else {
            Snackbar.make(requireView(), "Şifre giriniz!", 1000).show()
        }
    }

    fun cancel() {
        requireActivity().finish()
    }

    fun forgetPasswordText() {
        Navigation.findNavController(requireView()).navigate(R.id.action_signInSecondPageFragment_to_signInForgetPasswordFragment)
    }
}