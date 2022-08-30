package com.ccarvalho.models.types

@JvmInline
value class Email(val email: String) {
    init {
        val emailRegex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}\$"
        require(emailRegex.toRegex().matches(email)) {
            "Please put a valid email"
        }
    }
}