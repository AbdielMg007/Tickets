package com.mg.ticket.ui.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.activity.addCallback
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.mg.ticket.R
import com.mg.ticket.databinding.FragmentForgotPassBinding

class ForgotPassFragment : Fragment(R.layout.fragment_forgot_pass) {

    private lateinit var binding: FragmentForgotPassBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentForgotPassBinding.bind(view)
        setup()
    }

    private fun setup() {
        backPress()
        binding.entryForgotBtn.setOnClickListener{
            if(binding.emailForgotInput.text.isEmpty()){
                Toast.makeText(context,resources.getString(R.string.add_account), Toast.LENGTH_SHORT).show()
            }
            FirebaseAuth.getInstance().sendPasswordResetEmail(
                binding.emailForgotInput.text.toString()
            ).addOnCompleteListener {
                val messageResId = if (it.isSuccessful) R.string.sent_account else R.string.error
                Toast.makeText(context, resources.getString(messageResId), Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun backPress() {
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            findNavController().navigate(R.id.action_global_entryFragment)
        }
    }
}