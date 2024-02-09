package com.mg.ticket.ui.view.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isInvisible
import com.google.firebase.auth.FirebaseAuth
import com.mg.ticket.R
import com.mg.ticket.databinding.FragmentCreateAccountBinding
import com.mg.ticket.ui.view.activities.MenuActivity

class CreateAccountFragment : Fragment(R.layout.fragment_create_account) {
    private lateinit var binding: FragmentCreateAccountBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCreateAccountBinding.bind(view)
        setup()
    }

    private fun setup() {
        binding.progressBarCreateAccount.isInvisible = true
        binding.entryCreateBtn.setOnClickListener {
            binding.progressBarCreateAccount.isInvisible = false
            entryBtnAction()
        }
    }

    private fun entryBtnAction() {
        if(binding.emailCreateInput.text.isNotEmpty() && binding.passwordCreateInput.text.isNotEmpty()){
            FirebaseAuth.getInstance()
                .createUserWithEmailAndPassword(binding.emailCreateInput.text.toString(), binding.passwordCreateInput.text.toString()).addOnCompleteListener{
                    if (it.isSuccessful){
                        val nextScreen = Intent(context, MenuActivity::class.java)
                        binding.progressBarCreateAccount.isInvisible = true
                        startActivity(nextScreen)
                    }else{
                        binding.progressBarCreateAccount.isInvisible = true
                        showAlert()
                    }
                }
        }else{
            binding.progressBarCreateAccount.isInvisible = true
            Toast.makeText(context, resources.getString(R.string.text_empty_alert), Toast.LENGTH_SHORT).show()
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