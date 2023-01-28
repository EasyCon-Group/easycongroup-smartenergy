package uz.easycongroup.smartenergy.presentation.application.di.data

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import uz.easycongroup.smartenergy.data.datasource.preference.auth.AuthPreference
import uz.easycongroup.smartenergy.data.datasource.preference.user.UserPreference
import javax.inject.Singleton

@Module
internal object DataDaggerModulePreference {

    @JvmStatic
    @Provides
    @Singleton
    fun provideAuthPreference(
        context: Context
    ): AuthPreference =
        AuthPreference(get(context, "preference_auth"))

    @JvmStatic
    @Provides
    @Singleton
    fun provideUserPreference(
        context: Context
    ): UserPreference =
        UserPreference(get(context, "preference_user"))

    private fun get(context: Context, preferenceName: String): SharedPreferences =
        context.getSharedPreferences(preferenceName, Context.MODE_PRIVATE)
}