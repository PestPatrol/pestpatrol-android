package com.core.network.util

import android.os.Handler
import android.os.Looper
import java.util.concurrent.Executor
import java.util.concurrent.Executors

class AppExecutors {
    private val networkIO: Executor = Executors.newSingleThreadExecutor()
    private val diskIO: Executor = Executors.newFixedThreadPool(3)
    private val mainThread: Executor

    init {
        this.mainThread = MainThreadExecutor()
    }

    fun getNetworkIO(): Executor {
        return networkIO
    }

    fun getDiskIO(): Executor {
        return diskIO
    }

    fun mainThread(): Executor {
        return mainThread
    }

    private class MainThreadExecutor: Executor {
        private val mainThreadHandler = Handler(Looper.getMainLooper())

        override fun execute(command: Runnable?) {
            command?.let { mainThreadHandler.post(it) }
        }
    }
}