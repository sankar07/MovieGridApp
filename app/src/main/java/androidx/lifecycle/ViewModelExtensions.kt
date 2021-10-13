package androidx.lifecycle

import com.codeheros.movieapp.BuildConfig
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import com.codeheros.movieapp.model.Result
import java.io.Closeable
import kotlin.coroutines.CoroutineContext

private const val IO_JOB_KEY = "${BuildConfig.APPLICATION_ID}.IO_JOB_KEY"

/**
 *
 * Created by sankar corona on 2019-10-13.
 */
val ViewModel.ioViewModelScope: CoroutineScope
    get() {
        val scope: CoroutineScope? = this.getTag(IO_JOB_KEY)
        return scope?.let { safeScope ->
            safeScope
        } ?: run {
            setTagIfAbsent(IO_JOB_KEY, CloseableCoroutineScope(SupervisorJob() + Dispatchers.IO))
        }
    }

fun <T> ViewModel.launchInIOForLiveData(block: suspend () -> Result<out T>): LiveData<Result<out T>> =
    MediatorLiveData<Result<out T>>().apply {
        value = Result.loading()
        ioViewModelScope.launch {
            postValue(block())
        }
    }

internal class CloseableCoroutineScope(context: CoroutineContext) : Closeable, CoroutineScope {
    override fun close() = coroutineContext.cancel()
    override val coroutineContext: CoroutineContext = context
}