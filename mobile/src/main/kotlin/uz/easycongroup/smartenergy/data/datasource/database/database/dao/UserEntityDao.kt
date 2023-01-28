package uz.easycongroup.smartenergy.data.datasource.database.database.dao

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import kotlinx.coroutines.flow.Flow
import uz.easycongroup.smartenergy.data.models.user.entity.UserEntity

@Dao
internal abstract class UserEntityDao {

    @Query("SELECT * FROM users LIMIT 1")
    abstract fun getLastSignedUserEntity(): Flow<UserEntity>

    @Query("DELETE FROM users")
    abstract fun deleteAllUsers()

    @Update(onConflict = REPLACE)
    abstract fun update(userEntity: UserEntity)

    @Insert(onConflict = REPLACE)
    abstract fun insert(userEntity: UserEntity)

    @Insert(onConflict = REPLACE)
    abstract fun insert(collection: Collection<UserEntity>): LongArray

    @Update(onConflict = REPLACE)
    abstract fun update(collection: Collection<UserEntity>)
}