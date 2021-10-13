package com.codeheros.movieapp.utils

import android.view.LayoutInflater
import android.view.ViewGroup

/**
 * Created by sankar on 2021-10-11.
 */

val ViewGroup.layoutInflater: LayoutInflater
    get() = LayoutInflater.from(context)