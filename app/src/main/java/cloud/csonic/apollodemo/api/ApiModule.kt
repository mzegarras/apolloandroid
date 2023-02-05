package cloud.csonic.apollodemo.api

import cloud.csonic.apollodemo.BuildConfig
import cloud.csonic.apollodemo.repository.Repository
import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.network.okHttpClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {
    private const val BASE_URL = "http://52.251.68.150:4000/graphql"

    @Singleton
    @Provides
    fun providesHttpLoggingInterceptor() = HttpLoggingInterceptor()
        .apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    @Singleton
    @Provides
    fun providesOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor { chain ->
                val original = chain.request()
                val request = original.newBuilder()
                    .addHeader("name", "android")
                    .addHeader("version", BuildConfig.VERSION_NAME)
                    .method(original.method, original.body)
                chain.proceed(request.build())
            }
            .addInterceptor(httpLoggingInterceptor)
            .build()


    @Singleton
    @Provides
    fun getApolloClient(okHttpClient: OkHttpClient): ApolloClient {
        return ApolloClient.Builder()
            .serverUrl(BASE_URL)
            .okHttpClient(okHttpClient)
            .build();

    }

    @Singleton
    @Provides
    fun providesRepository(apolloClient:ApolloClient) = Repository(apolloClient)


}