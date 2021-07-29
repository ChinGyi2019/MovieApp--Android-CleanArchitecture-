package com.chingyi.domain

import io.reactivex.Flowable
import kotlinx.coroutines.withContext

abstract class  FlowableUseCase<Input, Output> {

    fun execute(input: Input): Output {
        return provide(input)
    }

    protected abstract  fun provide(input: Input): Output
}
