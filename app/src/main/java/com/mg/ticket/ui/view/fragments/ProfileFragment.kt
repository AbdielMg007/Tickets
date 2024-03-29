package com.mg.ticket.ui.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.mg.ticket.R
import com.mg.ticket.databinding.FragmentProfileBinding

class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private lateinit var binding: FragmentProfileBinding
    private lateinit var auth: FirebaseAuth


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentProfileBinding.bind(view)
        setup()
    }

    private fun setup() {
        //Show your email with firebase auth
        auth = Firebase.auth
        val user = Firebase.auth.currentUser
        user?.let {
            val email = it.email
            binding.tvNameUser.text = email
        }
    }

}