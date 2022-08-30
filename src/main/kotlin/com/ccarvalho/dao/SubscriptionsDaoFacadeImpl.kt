package com.ccarvalho.dao

import com.ccarvalho.dao.DatabaseFactory.dbQuery
import com.ccarvalho.models.Subscription
import com.ccarvalho.models.Subscriptions
import com.ccarvalho.models.types.Email
import com.ccarvalho.models.types.MaxTwoLetters
import com.ccarvalho.models.types.MinFiveLetters
import com.ccarvalho.models.types.PositiveInteger
import org.jetbrains.exposed.sql.*

class SubscriptionsDaoFacadeImpl : DaoFacade<Subscription> {

    override fun resultRowToModel(resultRow: ResultRow) = Subscription(
        id = resultRow[Subscriptions.id],
        name = MinFiveLetters(resultRow[Subscriptions.name]),
        email = Email(resultRow[Subscriptions.email]),
        address = MinFiveLetters(resultRow[Subscriptions.address]),
        city = MinFiveLetters(resultRow[Subscriptions.city]),
        state = MaxTwoLetters(resultRow[Subscriptions.state]),
        age = PositiveInteger(resultRow[Subscriptions.age])
    )

    override suspend fun findAll(): List<Subscription> = dbQuery {
        Subscriptions.selectAll().map(::resultRowToModel)
    }

    override suspend fun findOne(id: Long): Subscription? = dbQuery {
        Subscriptions
            .select { Subscriptions.id eq id }
            .map(::resultRowToModel)
            .singleOrNull()
    }

    override suspend fun update(id: Long, model: Subscription): Boolean = dbQuery {
        Subscriptions.update({ Subscriptions.id eq id }) {
            it[name] = model.name.value
            it[email] = model.email.email
            it[address] = model.address.value
            it[city] = model.city.value
            it[state] = model.state.value
            it[age] = model.age.value
        } > 0
    }

    override suspend fun delete(id: Long): Boolean = dbQuery {
        Subscriptions.deleteWhere { Subscriptions.id eq id } > 0
    }

    override suspend fun save(model: Subscription): Subscription? = dbQuery {
        val insertStatement = Subscriptions.insert {
            it[name] = model.name.value
            it[email] = model.email.email
            it[address] = model.address.value
            it[city] = model.city.value
            it[state] = model.state.value
            it[age] = model.age.value
        }
        insertStatement.resultedValues?.singleOrNull()?.let(::resultRowToModel)
    }
}