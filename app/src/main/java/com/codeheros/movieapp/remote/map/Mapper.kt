package com.codeheros.movieapp.remote.map

/**
 * Interface that helps reducing boilerplate code when
 * transforming a remote model as a local model, sometimes
 * more that one remote model transform into a core model, so,
 * instead of duplicating code, a Mapper can be used.
 *
 * Created by sankar on 2021-10-11.
 */
interface Mapper<R, L> {
    /**
     * Defines how to transform a remote R model into a
     * L local model / entity.
     */
    fun map(remote: R): L
}