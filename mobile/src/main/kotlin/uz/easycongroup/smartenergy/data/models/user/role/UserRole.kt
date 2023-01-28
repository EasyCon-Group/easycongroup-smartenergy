package uz.easycongroup.smartenergy.data.models.user.role

enum class UserRole {
    MANAGER,
    SUPERVISOR,
    CITIZEN;

    companion object {

        val DEFAULT: UserRole = CITIZEN

        fun valueOrDefault(code: String?): UserRole = when (code?.lowercase()) {
            "manager" -> MANAGER
            "supervisor" -> SUPERVISOR
            "citizen" -> CITIZEN
            else -> DEFAULT
        }
    }
}