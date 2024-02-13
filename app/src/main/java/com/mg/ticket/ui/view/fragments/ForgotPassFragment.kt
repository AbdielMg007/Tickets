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
import com.mg.ticket.ui.helper.FragmentBack.backPress

class ForgotPassFragment : Fragment(R.layout.fragment_forgot_pass) {

    private lateinit var binding: FragmentForgotPassBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentForgotPassBinding.bind(view)
        setup()
    }

    private fun setup() {
        //With this function if press the button back return to EntryFragment
        backPress(this)
        binding.entryForgotBtn.setOnClickListener{
            //Show warning if is empty entryForgotBtn
            if(binding.emailForgotInput.text.isEmpty())
                Toast.makeText(context,resources.getString(R.string.add_account), Toast.LENGTH_SHORT).show()
            //If the email is correct continue with firebase ResetPass
            FirebaseAuth.getInstance().sendPasswordResetEmail(
                binding.emailForgotInput.text.toString()
            ).addOnCompleteListener {
                //Show if it's all good
                val messageResId = if (it.isSuccessful) R.string.sent_account else R.string.error
                Toast.makeText(context, resources.getString(messageResId), Toast.LENGTH_SHORT).show()
            }
        }
    }
}