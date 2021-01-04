package com.ua.domain.boundary.request


import com.ua.domain.boundary.error.ErrorModel
import kotlinx.coroutines.CancellationException

class Request<T> {
    private var onComplete: ((T) -> Unit)? = null
    private var onError: ((ErrorModel) -> Unit)? = null
    private var onCancel: ((CancellationException) -> Unit)? = null

    fun onComplete(block: (T) -> Unit) {
        onComplete = block
    }

    fun onError(block: (ErrorModel) -> Unit) {

        onError = block

    }

    fun onCancel(block: (CancellationException) -> Unit) {
        onCancel = block
    }


    operator fun invoke(result: T) {
        onComplete?.let {
            it.invoke(result)
        }
    }

    operator fun invoke(error: ErrorModel) {
        onError?.let {
            it.invoke(error)

        }
    }

    operator fun invoke(error: CancellationException) {
        onCancel?.let {
            it.invoke(error)
        }
    }
}