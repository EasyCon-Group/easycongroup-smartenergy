
package uz.easycongroup.smartenergy.data.datasource.database.database.persistant

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import uz.easycongroup.smartenergy.data.datasource.database.converter.RoomTypeConverters
import uz.easycongroup.smartenergy.data.datasource.database.database.dao.UserEntityDao
import uz.easycongroup.smartenergy.data.models.user.entity.UserEntity

@TypeConverters(RoomTypeConverters::class)
@Database(
    entities = [UserEntity::class],
    version = 1,
    exportSchema = false
)
internal abstract class AppDatabase : RoomDatabase() {

    abstract val userEntityDao: UserEntityDao

    companion object {

        fun create(context: Context, databaseName: String): AppDatabase =
            Room.databaseBuilder(context, AppDatabase::class.java, databaseName)
                .fallbackToDestructiveMigration()
                .build()
    }
}