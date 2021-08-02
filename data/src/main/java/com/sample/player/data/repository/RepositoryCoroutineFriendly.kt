package com.sample.player.data.repository

import com.sample.player.domain.model.base.UseCaseResult
import kotlinx.coroutines.*

open class RepositoryCoroutineFriendly {

    suspend fun <T> runCommand(func: suspend () -> UseCaseResult<T>): UseCaseResult<T> {

        return supervisorScope {
            return@supervisorScope try {
                func.invoke()
            } catch (exception : Exception) {
                com.sample.player.data.ErrorHandler().handleException(exception)
            }
        }

    }
}