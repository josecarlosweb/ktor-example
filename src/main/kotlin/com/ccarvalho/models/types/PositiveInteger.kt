package com.ccarvalho.models.types

@JvmInline
value class PositiveInteger(val value: Int){
    init {
        require(value > 0){
            "The value must be grater than 0"
        }
    }
}