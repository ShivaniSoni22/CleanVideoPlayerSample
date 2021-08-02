package com.sample.player.domain.model.base

data class ErrorEntity(
        val originalError: Throwable? = null,
        val errorCode: Int = 0,
        val errorMessage: String = ""
)
