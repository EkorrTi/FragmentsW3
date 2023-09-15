package com.example.fragmentsw3

import java.io.Serializable

data class Contact(
    var name: String,
    var surname: String,
    var phone: String,
    val avatarURL: String,
) : Serializable {
    val fullName = "$name $surname"
}
