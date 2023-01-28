package uz.easycongroup.smartenergy.data.models.user.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import uz.easycongroup.smartenergy.data.models.user.role.UserRole

@Entity(tableName = "users")
internal data class UserEntity(

    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Long,

    @ColumnInfo(name = "full_name")
    val fullName: String,

    @ColumnInfo(name = "user_role")
    val userRole: UserRole,

    @ColumnInfo(name = "info")
    val info: String,

    @ColumnInfo(name = "login")
    val username: String
)