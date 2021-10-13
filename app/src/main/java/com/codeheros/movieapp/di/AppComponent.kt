package com.codeheros.movieapp.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.support.AndroidSupportInjectionModule
import com.codeheros.movieapp.local.di.LocalModule
import com.codeheros.movieapp.MovieApplication
import com.codeheros.movieapp.remote.di.RemoteModule
import javax.inject.Singleton

/**
 * Created by sankar on 2021-10-11.
 */
@Singleton
@Component(
    modules = [
        UiModule::class,
        LocalModule::class,
        RemoteModule::class,
        ViewModelModule::class,
        AndroidInjectionModule::class,
        AndroidSupportInjectionModule::class
    ]
)
interface AppComponent {
    @Component.Builder
    interface Builder {
        fun build(): AppComponent
        @BindsInstance
        fun application(application: Application): Builder
        fun localModule(localModule: LocalModule): Builder
        fun remoteModule(remoteModule: RemoteModule): Builder
    }

    fun inject(app: MovieApplication)
}