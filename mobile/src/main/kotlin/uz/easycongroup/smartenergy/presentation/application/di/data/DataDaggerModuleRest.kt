package uz.easycongroup.smartenergy.presentation.application.di.data

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Converter.Factory
import retrofit2.Retrofit
import retrofit2.create
import uz.easycongroup.smartenergy.BuildConfig
import uz.easycongroup.smartenergy.data.datasource.preference.auth.AuthPreference
import uz.easycongroup.smartenergy.data.datasource.preference.user.UserPreference
import uz.easycongroup.smartenergy.data.datasource.rest.error.handler.ErrorResponseHandler
import uz.easycongroup.smartenergy.data.datasource.rest.error.mapper.ErrorResponseMapper
import uz.easycongroup.smartenergy.data.datasource.rest.interceptor.auth.HeaderAuthBasicInterceptor
import uz.easycongroup.smartenergy.data.datasource.rest.interceptor.common.HeaderCommonParamsInterceptor
import uz.easycongroup.smartenergy.data.datasource.rest.interceptor.logging.HttpLoggingInterceptor
import uz.easycongroup.smartenergy.data.datasource.rest.retrofit.adapter.FlowCallAdapterFactory
import uz.easycongroup.smartenergy.data.datasource.rest.retrofit.converter.UnitConverterFactory
import uz.easycongroup.smartenergy.data.datasource.rest.retrofit.interceptor.withHttpErrorDispatcher
import uz.easycongroup.smartenergy.data.datasource.rest.service.AuthRestService
import uz.easycongroup.smartenergy.data.datasource.rest.service.PaymentRestService
import uz.easycongroup.smartenergy.data.datasource.rest.service.ProfileRestService
import uz.easycongroup.smartenergy.presentation.application.di.data.DataDaggerModuleRest.InterceptorProviders
import uz.easycongroup.smartenergy.presentation.application.di.data.DataDaggerModuleRest.OkHttpClientProviders
import uz.easycongroup.smartenergy.presentation.application.di.data.DataDaggerModuleRest.RestServiceProviders
import uz.easycongroup.smartenergy.presentation.application.di.data.DataDaggerModuleRest.RetrofitClientProviders
import uz.easycongroup.smartenergy.presentation.application.di.data.resttypes.AuthRest
import uz.easycongroup.smartenergy.presentation.application.di.data.resttypes.DefaultRest
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module(
    includes = [
        RestServiceProviders::class,
        InterceptorProviders::class,
        OkHttpClientProviders::class,
        RetrofitClientProviders::class
    ]
)
internal object DataDaggerModuleRest {

    @Module
    object RestServiceProviders {

        @JvmStatic
        @Provides
        @Singleton
        fun provideAuthRestService(
            @AuthRest retrofit: Retrofit
        ): AuthRestService = retrofit.create()

        @JvmStatic
        @Provides
        @Singleton
        fun providePaymentRestService(
            @DefaultRest retrofit: Retrofit
        ): PaymentRestService = retrofit.create()

        @JvmStatic
        @Provides
        @Singleton
        fun provideProfileRestService(
            @DefaultRest retrofit: Retrofit
        ): ProfileRestService = retrofit.create()
    }

    @Module
    object InterceptorProviders {

        @JvmStatic
        @Provides
        @Singleton
        fun provideHeaderAuthBasicInterceptor(
            authPreference: AuthPreference,
            userPreference: UserPreference
        ): HeaderAuthBasicInterceptor =
            HeaderAuthBasicInterceptor(authPreference, userPreference)

        @JvmStatic
        @Provides
        @Singleton
        fun provideHeaderCommonParamsInterceptor(
        ): HeaderCommonParamsInterceptor =
            HeaderCommonParamsInterceptor()

        @JvmStatic
        @Provides
        @Singleton
        fun provideHttpLoggingInterceptor(
        ): HttpLoggingInterceptor =
            HttpLoggingInterceptor(BuildConfig.LOG_ENABLED)

        @JvmStatic
        @Provides
        @Singleton
        fun provideChuckerInterceptor(
            context: Context
        ): ChuckerInterceptor =
            ChuckerInterceptor.Builder(context)
                .collector(ChuckerCollector(context))
                .maxContentLength(250000L)
                .redactHeaders(emptySet())
                .alwaysReadResponseBody(false)
                .build()
    }

    @Module
    object OkHttpClientProviders {
        @JvmStatic
        @Provides
        @DefaultRest
        @Singleton
        fun provideOkHttpClientForDefaultRest(
            chuckerInterceptor: ChuckerInterceptor,
            headerAuthBasicInterceptor: HeaderAuthBasicInterceptor,
            headerCommonParamsInterceptor: HeaderCommonParamsInterceptor,
            httpLoggingInterceptor: HttpLoggingInterceptor
        ): OkHttpClient =
            OkHttpClient.Builder()
                .addInterceptor(headerAuthBasicInterceptor)
                .addInterceptor(headerCommonParamsInterceptor)
                .addInterceptor(httpLoggingInterceptor)
                .addInterceptor(chuckerInterceptor)
                .withHttpErrorDispatcher(ErrorResponseHandler(), ErrorResponseMapper())
                .retryOnConnectionFailure(true)
                .followRedirects(false)
                .followSslRedirects(false)
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .build()


        @JvmStatic
        @Provides
        @AuthRest
        @Singleton
        fun provideOkHttpClientForAuthRest(
            chuckerInterceptor: ChuckerInterceptor,
            headerCommonParamsInterceptor: HeaderCommonParamsInterceptor,
            httpLoggingInterceptor: HttpLoggingInterceptor
        ): OkHttpClient =
            OkHttpClient.Builder()
                .addInterceptor(headerCommonParamsInterceptor)
                .addInterceptor(httpLoggingInterceptor)
                .addInterceptor(chuckerInterceptor)
                .withHttpErrorDispatcher(ErrorResponseHandler(), ErrorResponseMapper())
                .retryOnConnectionFailure(false)
                .followRedirects(false)
                .followSslRedirects(false)
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .build()

        @JvmStatic
        @Provides
        @Singleton
        fun JsonConverterFactory(
        ): Converter.Factory {
            val lazyJson: Json by lazy {
                Json {
                    isLenient = true
                    ignoreUnknownKeys = true
                    prettyPrint = true
                    allowSpecialFloatingPointValues = true
                    useArrayPolymorphism = true
                }
            }

            return lazyJson.asConverterFactory("application/json; charset=utf-8".toMediaType())
        }
    }

    @Module
    object RetrofitClientProviders {

        @JvmStatic
        @Provides
        @DefaultRest
        @Singleton
        fun provideRetrofitForDefaultRest(
            converterFactory: Converter.Factory,
            @DefaultRest okHttpClient: OkHttpClient
        ): Retrofit =
            Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .client(okHttpClient)
                .addCallAdapterFactory(FlowCallAdapterFactory)
                .addConverterFactory(converterFactory)
                .addConverterFactory(UnitConverterFactory)
                .build()

        @JvmStatic
        @Provides
        @AuthRest
        @Singleton
        fun provideRetrofitForAuthRest(
            converterFactory: Factory,
            @AuthRest okHttpClient: OkHttpClient
        ): Retrofit =
            Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .client(okHttpClient)
                .addCallAdapterFactory(FlowCallAdapterFactory)
                .addConverterFactory(converterFactory)
                .addConverterFactory(UnitConverterFactory)
                .build()
    }
}