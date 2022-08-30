package com.ccarvalho.models.types

@JvmInline
value class MaxTwoLetters(val value: String){

    init {
        require(value.length == 2){
            "The value must contains only 2 letters"
        }
    }
}