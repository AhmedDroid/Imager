package di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import network.PhotosService
import repos.PhotosRepo
import repos.PhotosRepoImpl
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RepoModule {

    @Singleton
    @Provides
    fun providePhotosRepo(photosService: PhotosService): PhotosRepo
            = PhotosRepoImpl(photosService)

}