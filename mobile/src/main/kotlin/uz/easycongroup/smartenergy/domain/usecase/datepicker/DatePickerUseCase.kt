package uz.easycongroup.smartenergy.domain.usecase.datepicker

interface DatePickerUseCase {

    fun setSelectedDate(value: Long)

    fun returnSelectedItem()

    fun resetSelection()
}