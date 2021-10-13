package com.codeheros.movieapp.domain

import com.codeheros.movieapp.model.Result

/**
 * Top-level function that helps catching HTTP or Local Exceptions
 * from executing calls from the repository.
 *
 * Created by sankar on 2021-10-13.
 */
internal suspend fun <T> interact(suspendWork: suspend () -> T): Result<out T> = try {
    val payload = suspendWork()
    Result.success(payload)
} catch (e: Exception) {
    Result.error(e.localizedMessage)
}