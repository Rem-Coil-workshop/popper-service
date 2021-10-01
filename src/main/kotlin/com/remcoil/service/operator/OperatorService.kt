package com.remcoil.service.operator

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.remcoil.config.AppConfig
import com.remcoil.config.JwtConfig
import com.remcoil.data.model.OperatorCredentials
import com.remcoil.dao.operator.OperatorDao
import com.remcoil.data.model.Operator
import java.util.*


class OperatorService(private val operatorDao: OperatorDao, private val config: JwtConfig) {

    fun getOperator(credentials: OperatorCredentials): String? {
        val operator = operatorDao.getOperator(credentials.phone)

        if (credentials.password == operator.password) {
            return generateToken(operator)
        }
        return null
    }

    fun createOperator(operator: Operator): String {
        val operator = operatorDao.createOperator(operator)
        return generateToken(operator)
    }

    private fun generateToken(operator: Operator) = JWT.create()
        .withClaim("firstname", operator.firstname)
        .withClaim("second_name", operator.second_name)
        .withClaim("surname", operator.surname)
        .withClaim("phone", operator.phone)
        .withExpiresAt(Date(System.currentTimeMillis() + config.time))
        .sign(Algorithm.HMAC256(config.secret))
}