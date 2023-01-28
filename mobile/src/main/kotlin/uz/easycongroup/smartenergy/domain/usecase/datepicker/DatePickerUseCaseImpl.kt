package uz.easycongroup.smartenergy.domain.usecase.datepicker

import uz.easycongroup.smartenergy.domain.sharedflow.global.date.DateSelectionSharedFlow
import javax.inject.Inject

class DatePickerUseCaseImpl @Inject constructor(
    private val dateSelectionSharedFlow: DateSelectionSharedFlow
) : DatePickerUseCase {

    var selectedDate: Long? = null

    override fun setSelectedDate(value: Long) {
        selectedDate = value
    }

    override fun returnSelectedItem() {
        if (selectedDate != null)
            dateSelectionSharedFlow.tryEmit(selectedDate!!)
    }

    override fun resetSelection() {
        selectedDate = null
    }
}