package uz.easycongroup.smartenergy.domain.data.repository.state

interface StateRepository {

    fun isLoginHasBeen(): Boolean
    fun setLoginHasBeen(value: Boolean)
}