package uz.easycongroup.smartenergy.presentation.support.cicerone.router

import ru.terrakok.cicerone.Router

abstract class NestedRouter {

    protected var ciceroneRouter: Router? = null
        private set

    fun setCiceroneRouter(value: Router) {
        ciceroneRouter = value
    }

}