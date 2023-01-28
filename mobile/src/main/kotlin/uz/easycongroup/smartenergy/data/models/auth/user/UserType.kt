package uz.easycongroup.smartenergy.data.models.auth.user

enum class UserType {
    user,        // simple user
    simple_user, //
    agent,       // agent
    unknown;

    companion object {

        val default: UserType = unknown

        fun valueOfOrDefault(name: String?): UserType =
            name?.let { runCatching { valueOf(it) }.getOrNull() } ?: default
    }
}