package com.mg.ticket.ui.view.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.core.view.isInvisible
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.mg.ticket.R
import com.mg.ticket.databinding.FragmentEntryBinding
import com.mg.ticket.ui.helper.DialogUtils
import com.mg.ticket.ui.view.activities.MenuActivity


class EntryFragment : Fragment(R.layout.fragment_entry) {

    private lateinit var binding: FragmentEntryBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentEntryBinding.bind(view)
        setup()
    }

    private fun setup() {

        //Not show progressbar when start the fragment
        binding.progressBar.isInvisible = true

        binding.forgotTv.setOnClickListener {
            //If push the button forgotTv go to forgotPassFragment
            findNavController().navigate(R.id.action_entryFragment_to_forgotPassFragment)
        }

        binding.loginEntryTv.setOnClickListener {
            //If push the button loginEntryTv go to createAccountFragment
            findNavController().navigate(R.id.action_entryFragment_to_createAccountFragment)
        }
        binding.entryBtn.setOnClickListener {
            //If push the button entryBtn go to entryBtnAction and show the progressBar
            binding.progressBar.isInvisible = false
            entryBtnAction()
        }

    }

    private fun entryBtnAction() {
        if (binding.emailInput.text.isEmpty() || binding.passwordInput.text.isEmpty()) {
            //Show warning if is empty emailInput or passwordInput
            binding.progressBar.isInvisible = true
            Toast.makeText(context, resources.getString(R.string.text_empty_alert), Toast.LENGTH_SHORT).show()
            return
        }
        //If if is not empty emailInput and passwordInput continue with firebase login check
        FirebaseAuth.getInstance().signInWithEmailAndPassword(
            binding.emailInput.text.toString(),
            binding.passwordInput.text.toString()
        ).addOnCompleteListener {
            //If the access credentials are correct you go to MenuActivity
            if (it.isSuccessful) {
                val nextScreen = Intent(context, MenuActivity::class.java)
                startActivity(nextScreen)
                binding.progressBar.isInvisible = true
                return@addOnCompleteListener
            }
            //Show Alert Dialog if you have a mistake about your access credentials
            binding.progressBar.isInvisible = true
            DialogUtils.showAlertDialog(
                context,
                resources.getString(R.string.error),
                resources.getString(R.string.error_create_account),
                resources.getString(R.string.accept)
            )
        }
    }

}