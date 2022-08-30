package com.ccarvalho.models

import com.ccarvalho.models.types.Email
import com.ccarvalho.models.types.MaxTwoLetters
import com.ccarvalho.models.types.MinFiveLetters
import com.ccarvalho.models.types.PositiveInteger
import org.jetbrains.exposed.sql.Table

data class Subscription(
    val id: Long? = null,
    val name: MinFiveLetters,
    val email: Email,
    val address: MinFiveLetters,
    val city: MinFiveLetters,
    val state: MaxTwoLetters,
    val age: PositiveInteger
) {
    constructor(name: String, email: String, address: String, city: String, state: String, age: Int) : this(
        null,
        MinFiveLetters(name),
        Email(email),
        MinFiveLetters(address),
        MinFiveLetters(city),
        MaxTwoLetters(state),
        PositiveInteger(age)
    )
}


object Subscriptions : Table() {
    val id = long("id").autoIncrement()
    val name = varchar("name", 255)
    val email = varchar("email", 255)
    val address = varchar("address", 255)
    val city = varchar("city", 255)
    val state = varchar("state", 2)
    val age = integer("age")

    override val primaryKey = PrimaryKey(id)
}