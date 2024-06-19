package com.example.login_firebase.viewModel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.login_firebase.models.DataModel
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore

class HomeViewModel : ViewModel() {
    private val auth: FirebaseAuth = Firebase.auth
    var state by mutableStateOf(DataModel())
        private set

    fun signOut() {
        auth.signOut()
    }

    fun getUserById(userId: String) {
        FirebaseFirestore.getInstance().collection("Users")
            .whereEqualTo("userId", userId)
            .get()
            .addOnSuccessListener { documents ->
                if (!documents.isEmpty) {
                    val document = documents.documents[0]
                    val data = document.toObject(DataModel::class.java)
                    state = state.copy(
                        email = data!!.email,
                        lastName = data.lastName,
                        userName = data.userName
                    )
                } else {
                    Log.d("ERROR", "No documents")
                }
            }
            .addOnFailureListener { exception ->
                Log.d("Error", "Error getting documents: ", exception)
            }
    }
}