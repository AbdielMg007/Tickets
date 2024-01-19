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
            Toast.makeText(context,"Favor de llenar todos los campos", Toast.LENGTH_SHORT).show()
        }
    }

    private fun showAlert() {
        val builder = context?.let { AlertDialog.Builder(it) }
        builder?.setTitle("Error")
        builder?.setMessage("Se ha generado un error al crear la cuenta, revise si los datos son correctos")
        builder?.setPositiveButton("Aceptar", null)
        builder?.create()?.show()
    }
}