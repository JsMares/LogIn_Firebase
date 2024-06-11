package com.example.login_firebase.models

data class UserModel(
    val userId: String,
    val email: String,
    val userName: String,
    val lastName: String
) {
    fun toMap(): MutableMap<String, Any> {
        return mutableMapOf(
            "userId" to this.userId,
            "email" to this.email,
            "userName" to this.userName,
            "lastName" to this.lastName
        )
    }
}
