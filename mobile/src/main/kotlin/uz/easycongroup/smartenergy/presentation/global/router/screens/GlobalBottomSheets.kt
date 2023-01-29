package uz.easycongroup.smartenergy.presentation.global.router.screens

import androidx.fragment.app.Fragment
import ru.terrakok.cicerone.android.support.SupportAppScreen
import uz.easycongroup.smartenergy.domain.data.models.act.type.ActType
import uz.easycongroup.smartenergy.domain.data.models.sort.SortType
import uz.easycongroup.smartenergy.presentation.presentation.common.features.acttype.ActTypeFragment
import uz.easycongroup.smartenergy.presentation.presentation.common.features.datepicker.bottomsheet.DatePickerFragment
import uz.easycongroup.smartenergy.presentation.presentation.common.features.photoaction.PhotoActionFragment
import uz.easycongroup.smartenergy.presentation.presentation.common.features.sorttype.SortTypeFragment

object GlobalBottomSheets {

    data class ActTypeScreen(
        private val actType: ActType?
    ) : SupportAppScreen() {
        override fun getFragment(): Fragment =
            ActTypeFragment.newInstance(actType)
    }

    data class DatePickerScreen(
        val dialogTitle: String? = null,
        val defaultDate: Long? = null,
        val minDate: Long? = null,
        val maxDate: Long? = null,
        val hideDaySpinner: Boolean = false
    ) : SupportAppScreen() {
        override fun getFragment(): Fragment =
            DatePickerFragment.newInstance(
                dialogTitle = dialogTitle,
                selectedDate = defaultDate,
                minDate = minDate,
                maxDate = maxDate,
                hideDaySpinner = hideDaySpinner
            )
    }

    object PhotoActionScreen : SupportAppScreen() {
        override fun getFragment(): Fragment =
            PhotoActionFragment.newInstance()
    }

    data class SortTypeScreen(
        private val sortType: SortType?
    ) : SupportAppScreen() {
        override fun getFragment(): Fragment =
            SortTypeFragment.newInstance(sortType)
    }
}