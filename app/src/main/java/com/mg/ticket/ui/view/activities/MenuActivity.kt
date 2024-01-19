package com.mg.ticket.ui.view.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.mg.ticket.R
import com.mg.ticket.databinding.ActivityMenuBinding
import com.mg.ticket.ui.view.fragments.ProfileFragment
import com.mg.ticket.ui.view.fragments.TicketsFragment

class MenuActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMenuBinding
    var isFabOpen = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(ProfileFragment())
        setup()
    }

    private fun setup() {
        binding.apps.setOnClickListener {
            if (isFabOpen)
                closeFab()
            else
                showFab()
        }

        binding.profile.setOnClickListener{
            replaceFragment(ProfileFragment())
            closeFab()
        }

        binding.tickets.setOnClickListener{
            replaceFragment(TicketsFragment())
            closeFab()
        }

        binding.logOut.setOnClickListener {
            Firebase.auth.signOut()
            finish()
        }

    }

    private fun showFab(){
        isFabOpen = true
        binding.apps.setImageResource(R.drawable.baseline_add_24)
        binding.profile.animate().translationY(-540F)
        binding.tickets.animate().translationY(-360F)
        binding.logOut.animate().translationY(-180F)
    }

    private fun closeFab(){
        isFabOpen = false
        binding.apps.setImageResource(R.drawable.baseline_add_24)
        binding.profile.animate().translationY(0F)
        binding.tickets.animate().translationY(0F)
        binding.logOut.animate().translationY(0F)
    }


    private fun replaceFragment(fragment: Fragment){
        val currentUser = FirebaseAuth.getInstance().currentUser
        if (currentUser != null) {
            val fragmentManager = supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.fragmentSet, fragment)
            fragmentTransaction.commit()
        } else {
            val returnLogin = Intent(this, LoginActivity::class.java)
            startActivity(returnLogin)
            finish()
            Toast.makeText(applicationContext,"Inicia sesion para acceder a este apartado", Toast.LENGTH_SHORT).show()
        }
    }
}