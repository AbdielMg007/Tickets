package com.mg.ticket.ui.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
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
        binding.entryForgotBtn.setOnClickListener{
            if(binding.emailForgotInput.text.isNotEmpty()){
                FirebaseAuth.getInstance().sendPasswordResetEmail(binding.emailForgotInput.text.toString()).addOnCompleteListener {
                    if (it.isSuccessful){
                        Toast.makeText(context,"Se ah enviado un correo para restablecer su contraseña", Toast.LENGTH_SHORT).show()
                        requireActivity().supportFragmentManager.popBackStack()
                    }
                    else
                        Toast.makeText(context,"Ah ocurrido un error, revise si el correo es autentico", Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(context,"Ingrese su correo", Toast.LENGTH_SHORT).show()
            }

        }
    }
}