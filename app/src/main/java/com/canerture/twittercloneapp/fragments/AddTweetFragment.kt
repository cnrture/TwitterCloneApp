package com.canerture.twittercloneapp.fragments

import android.app.Activity
import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.Navigation
import com.canerture.twittercloneapp.R
import com.canerture.twittercloneapp.databinding.FragmentAddTweetBinding
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.canerture.twittercloneapp.viewmodels.AddTweetViewModel
import com.google.android.material.snackbar.Snackbar
import java.io.IOException
import java.util.*


class AddTweetFragment : Fragment() {

    private lateinit var binding: FragmentAddTweetBinding
    private val viewModel : AddTweetViewModel by viewModels()
    private lateinit var activityResultLauncher : ActivityResultLauncher<Intent>
    private lateinit var permissionLauncher : ActivityResultLauncher<String>
    var selectedBitmap : Bitmap? = null
    var tweetImage : Uri? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_tweet, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.addTweetFragmentObject = this

        registerLauncher()

    }

    fun cancel() {
        Navigation.findNavController(requireView()).navigate(R.id.action_addTweetFragment_to_homeFragment)
    }

    fun selectImage() {
        if(ContextCompat.checkSelfPermission(requireContext(), android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(requireActivity(), android.Manifest.permission.READ_EXTERNAL_STORAGE)) {
                Snackbar.make(requireView(), "Permission needed for gallery", Snackbar.LENGTH_INDEFINITE).setAction("Give Permission",
                    View.OnClickListener {
                        permissionLauncher.launch(android.Manifest.permission.READ_EXTERNAL_STORAGE)
                    }).show()
            } else {
                permissionLauncher.launch(android.Manifest.permission.READ_EXTERNAL_STORAGE)
            }
        } else {
            val intentToGallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            activityResultLauncher.launch(intentToGallery)
        }
    }

    private fun registerLauncher() {
        activityResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val intentFromResult = result.data
                if (intentFromResult != null) {
                    tweetImage = intentFromResult.data
                    try {
                        if (Build.VERSION.SDK_INT >= 28) {
                            val source = ImageDecoder.createSource(requireActivity().contentResolver, tweetImage!!)
                            selectedBitmap = ImageDecoder.decodeBitmap(source)
                            binding.profilePicture.setImageBitmap(selectedBitmap)
                        } else {
                            selectedBitmap = MediaStore.Images.Media.getBitmap(requireActivity().contentResolver, tweetImage)
                            binding.profilePicture.setImageBitmap(selectedBitmap)
                        }
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                }
            }
        }
        permissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) { result ->
            if (result) {
                //permission granted
                val intentToGallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                activityResultLauncher.launch(intentToGallery)
            } else {
                //permission denied
                Toast.makeText(requireContext(), "Permisson needed!", Toast.LENGTH_LONG).show()
            }
        }
    }

    fun addTweet(tweetText: String) {
        val uuid = UUID.randomUUID()
        val tweetImageName = "$uuid.jpg"

        val prefences = requireActivity().getSharedPreferences("com.canerture.twittercloneapp", Context.MODE_PRIVATE)
        val id = prefences.getString("id", "null")
        val profilepic = prefences.getString("profilepic", "null")
        val name = prefences.getString("name", "null")
        val nickname = prefences.getString("nickname", "null")

        if (tweetImage != null) {

            println("girdi")

            viewModel.addTweet(id!!, profilepic!!, name!!, nickname!!, tweetText, tweetImageName, tweetImage)

        }   else {
            println("girmedi")
        }
    }


}