package com.sample.player.data

import com.sample.player.domain.model.base.ErrorEntity
import com.sample.player.domain.model.base.ErrorResult

class ErrorHandler {

    fun handleException(throwable: Throwable) : ErrorResult {
        return ErrorResult(ErrorEntity(throwable))
    }

}
