package com.remcoil.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Operator(
    val id: Int,
    val firstname: String,
    val second_name: String,
    val surname: String,
    val phone: String,
    val password: String
) {
    constructor(credentials: OperatorCredentials) : this(
        credentials.id!!,
        credentials.firstname,
        credentials.second_name,
        credentials.surname,
        credentials.phone,
        credentials.password,
    )
}
