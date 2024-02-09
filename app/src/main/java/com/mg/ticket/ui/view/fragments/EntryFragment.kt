package com.mg.ticket.ui.view.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isInvisible
import androidx.fragment.app.commit
import com.google.firebase.auth.FirebaseAuth
import com.mg.ticket.R
import com.mg.ticket.databinding.FragmentEntryBinding
import com.mg.ticket.ui.view.activities.MenuActivity


class EntryFragment : Fragment(R.layout.fragment_entry) {

    private lateinit var binding: FragmentEntryBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentEntryBinding.bind(view)
        setup()
    }

    private fun setup() {
        binding.progressBar.isInvisible = true
        binding.forgotTv.setOnClickListener {
            forgotTvAction()
        }
        binding.loginEntryTv.setOnClickListener {
            loginEntryTvAction()
        }
        binding.entryBtn.setOnClickListener {
            binding.progressBar.isInvisible = false
            entryBtnAction()
        }
    }

    private fun entryBtnAction() {
        if (binding.emailInput.text.isEmpty() || binding.passwordInput.text.isEmpty()) {
            binding.progressBar.isInvisible = true
            Toast.makeText(context, resources.getString(R.string.text_empty_alert), Toast.LENGTH_SHORT).show()
            return
        }

        FirebaseAuth.getInstance().signInWithEmailAndPassword(
            binding.emailInput.text.toString(),
            binding.passwordInput.text.toString()
        ).addOnCompleteListener {
            if (it.isSuccessful) {
                val nextScreen = Intent(context, MenuActivity::class.java)
                binding.progressBar.isInvisible = true
                startActivity(nextScreen)
            } else {
                binding.progressBar.isInvisible = true
                showAlert()
            }
        }
    }


    private fun loginEntryTvAction() {
        requireActivity().supportFragmentManager.commit {
            replace(R.id.fragmentLogin, CreateAccountFragment())
            addToBackStack(null)
        }
    }

    private fun forgotTvAction() {
        requireActivity().supportFragmentManager.commit {
            replace(R.id.fragmentLogin, ForgotPassFragment())
            addToBackStack(null)
        }
    }

    private fun showAlert() {
        val builder = context?.let { AlertDialog.Builder(it) }
        builder?.setTitle(resources.getString(R.string.error))
        builder?.setMessage(resources.getString(R.string.error_create_account))
        builder?.setPositiveButton(resources.getString(R.string.accept), null)
        builder?.create()?.show()
    }
}