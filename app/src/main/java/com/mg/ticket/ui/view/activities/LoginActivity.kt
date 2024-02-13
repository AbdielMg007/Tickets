package com.mg.ticket.ui.view.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.commit
import com.google.firebase.auth.FirebaseAuth
import com.mg.ticket.R
import com.mg.ticket.databinding.ActivityLoginBinding
import com.mg.ticket.ui.view.fragments.EntryFragment

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
    public override fun onResume() {
        //Check if you have already logged
        super.onResume()
        val currentUser = FirebaseAuth.getInstance().currentUser
        if (currentUser != null) {
            val pass = Intent(this, MenuActivity::class.java)
            startActivity(pass)
            finish()
        }
    }

    override fun onPause() {
        super.onPause()
        finish()
    }
}