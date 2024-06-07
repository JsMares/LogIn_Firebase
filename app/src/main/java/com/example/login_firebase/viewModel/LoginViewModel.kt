package com.example.login_firebase.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import kotlinx.coroutines.launch

class LoginViewModel: ViewModel() {

    private val auth: FirebaseAuth = Firebase.auth

    fun login(email: String, password: String, onSuccess: (String) -> Unit) {
        viewModelScope.launch {
            try {
                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener {  task ->
                        if (task.isSuccessful) {
                            onSuccess("OK")
                        } else {
                            onSuccess("ERROR")
                        }
                    }
            }
            catch (e: Exception) {
                Log.d("ERROR EN JETPACK", "ERROR: ${e.localizedMessage}")
            }
        }
    }
}