package uz.easycongroup.smartenergy.presentation.application.di.data

import android.content.Context
import dagger.Module
import dagger.Provides
import uz.easycongroup.smartenergy.data.datasource.database.database.dao.UserEntityDao
import uz.easycongroup.smartenergy.data.datasource.database.database.persistant.AppDatabase
import uz.easycongroup.smartenergy.presentation.application.di.data.DataDaggerModuleDao.UserDatabaseProviders
import javax.inject.Singleton

@Module(includes = [UserDatabaseProviders::class])
internal object DataDaggerModuleDao {

    @Module
    object UserDatabaseProviders {

        @JvmStatic
        @Provides
        @Singleton
        fun appDatabase(
            context: Context
        ): AppDatabase =
            AppDatabase.create(context, "app.db")

        @JvmStatic
        @Provides
        @Singleton
        fun userEntityDao(
            appDatabase: AppDatabase
        ): UserEntityDao =
            appDatabase.userEntityDao
    }
}