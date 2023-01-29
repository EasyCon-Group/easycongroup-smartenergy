package uz.easycongroup.smartenergy.presentation.global.router

import uz.easycongroup.smartenergy.domain.data.models.act.type.ActType
import uz.easycongroup.smartenergy.domain.data.models.sort.SortType
import uz.easycongroup.smartenergy.presentation.global.router.screens.GlobalBottomSheets
import uz.easycongroup.smartenergy.presentation.global.router.screens.GlobalFragments
import uz.easycongroup.smartenergy.presentation.presentation.features.main.router.MainRouter.MainTabs
import uz.easycongroup.smartenergy.presentation.support.cicerone.router.CiceroneRouter

class GlobalRouter : CiceroneRouter() {

    fun openActSubmitScreen() =
        navigateTo(GlobalFragments.ActSubmitScreen)

    fun openAuthStartScreen(isAsRootScreen: Boolean = false) {
        if (isAsRootScreen) newRootScreen(GlobalFragments.AuthStartScreen)
        else navigateTo(GlobalFragments.AuthStartScreen)
    }

    fun openMainScreen(tab: MainTabs = MainTabs.ProfileTab) =
        newRootScreen(GlobalFragments.MainScreen(tab))

    fun openNotificationListScreen() =
        navigateTo(GlobalFragments.NotificationListScreen)

    fun openOrderListScreen() =
        navigateTo(GlobalFragments.OrderList)

    /**
     * bottom sheets
     * */

    fun openActTypeScreen(actType: ActType?) =
        navigateTo(GlobalBottomSheets.ActTypeScreen(actType))

    fun openDatePickerScreen(
        dialogTitle: String? = null,
        defaultDate: Long? = null,
        minDate: Long? = null,
        maxDate: Long? = null,
        hideDaySpinner: Boolean = false
    ) =
        navigateTo(
            GlobalBottomSheets.DatePickerScreen(
                dialogTitle = dialogTitle,
                defaultDate = defaultDate,
                minDate = minDate,
                maxDate = maxDate,
                hideDaySpinner = hideDaySpinner
            )
        )

    fun openSortTypeScreen(sortType: SortType?) =
        navigateTo(GlobalBottomSheets.SortTypeScreen(sortType))

    fun openPhotoActionSelectionScreen() =
        navigateTo(GlobalBottomSheets.PhotoActionScreen)
}