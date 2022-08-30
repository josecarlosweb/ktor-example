package com.ccarvalho.models.types

@JvmInline
value class MinFiveLetters(val value: String){
    init {
        require(value.length >= 5) {
            "The value must contains 5 or more letters"
        }
    }
}