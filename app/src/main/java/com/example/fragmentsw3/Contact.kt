package com.example.fragmentsw3

import java.io.Serializable

data class Contact(
    val name: String,
    val surname: String,
    val phone: String
): Serializable
