package uz.easycongroup.smartenergy.presentation.presentation.common.features.datepicker.bottomsheet

import moxy.MvpPresenter
import uz.easycongroup.smartenergy.domain.usecase.datepicker.DatePickerUseCase
import uz.easycongroup.smartenergy.presentation.presentation.common.features.datepicker.bottomsheet.router.DatePickerRouter
import javax.inject.Inject

internal class DatePickerPresenter @Inject constructor(
    private val router: DatePickerRouter,
    private val useCase: DatePickerUseCase
) : MvpPresenter<DatePickerView>() {

    fun setSelectedDate(value: Long) =
        useCase.setSelectedDate(value)

    fun returnSelectedItem() {
        useCase.returnSelectedItem()
        dismiss()
    }

    fun resetSelection() {
        useCase.resetSelection()
    }

    private fun dismiss() {
        viewState.onDismissDialog()
    }
}