package uz.easycongroup.smartenergy.data.models.user.role

enum class UserRole {
    MANAGER,
    SUPERVISOR,
    CITIZEN;

    val isManager: Boolean
        get() = this == MANAGER

    val isSupervisor: Boolean
        get() = this == SUPERVISOR

    val isCitizen: Boolean
        get() = this == CITIZEN

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