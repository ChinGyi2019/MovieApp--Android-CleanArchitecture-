package com.chingyi.domain

import kotlinx.coroutines.withContext
import javax.inject.Inject

abstract class CoroutineUseCase<Input, Output> constructor(
    protected val dispatcherProvider: DispatcherProvider
) {

    suspend fun execute(input: Input): Output {
        return withContext(dispatcherProvider.io()) {
            provide(input)
        }
    }

    protected abstract suspend fun provide(input: Input): Output
}