package cloud.csonic.apollodemo.module

//import cloud.csonic.apollodemo.repository.DataRepository
//import cloud.csonic.apollodemo.repository.DataRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@InstallIn(ViewModelComponent::class) // Scope our dependencies
@Module
abstract class ProfileModule {

    // To be read as — When someone asks for DataRepository, create a DataRepoImpl and return it.
    //@Binds
    //abstract fun getProfileSource(repo: DataRepositoryImpl): DataRepository
}