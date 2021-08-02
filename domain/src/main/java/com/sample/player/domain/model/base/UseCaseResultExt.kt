package com.sample.player.domain.model.base

inline fun <T, R> UseCaseResult<T>.doOnSuccess(func: (result: T) -> R): UseCaseResult<T> {
    if (this is SuccessResult) {
        func.invoke(data)
    }
    return this
}


fun <T, R> UseCaseResult<T>.doOnError(func: (error: ErrorEntity) -> R): UseCaseResult<T> {
    if (this is ErrorResult) {
        func.invoke(error)
    }
    return this
}