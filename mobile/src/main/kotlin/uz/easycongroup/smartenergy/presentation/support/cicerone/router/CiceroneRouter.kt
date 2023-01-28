package uz.easycongroup.smartenergy.presentation.support.cicerone.router

import ru.terrakok.cicerone.Router

abstract class CiceroneRouter : Router(){

    @Deprecated(
        message = "use back method",
        level = DeprecationLevel.ERROR,
        replaceWith = ReplaceWith(
            "back()",
            "uz.easycongroup.smartenergy.presentation.support.cicerone.router.CiceroneRouter"
        )
    )
    override fun exit() {
        super.exit()
    }

    fun back() =
        super.exit()
}