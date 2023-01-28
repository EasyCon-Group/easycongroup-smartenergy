package uz.easycongroup.smartenergy.presentation.presentation.features.main.features.home

import moxy.MvpView

interface HomeView : MvpView {

    fun onDefinedViewVisibility(
        isManagerViewsVisible: Boolean,
        isSupervisorViewsVisible: Boolean,
        isCitizenViewsVisible: Boolean,
    )

}