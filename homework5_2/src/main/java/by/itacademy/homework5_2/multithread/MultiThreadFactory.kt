package by.itacademy.homework5_2.multithread

import android.content.Context

const val THREAD_POOL_HELPER = 1
const val COMPLETABLE_FUTURE_HELPER = 2
const val RXJAVA_HELPER = 3

class MultiThreadFactory {
    fun createHelper(context: Context, type: Int): MultiThreadOperations {
        return when (type) {
            THREAD_POOL_HELPER -> ThreadPoolHelper(context)
            COMPLETABLE_FUTURE_HELPER -> CompletableFutureHelper(context)
            RXJAVA_HELPER -> RXJavaHelper(context)
            else -> RXJavaHelper(context)
        }
    }
}