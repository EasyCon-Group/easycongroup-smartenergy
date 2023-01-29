package uz.easycongroup.smartenergy.domain.usecase.act.exception

class ActSubmitException(
    val isTitleEmpty: Boolean,
    val isActTypeNotSelected: Boolean,
    val isDescriptionEmpty: Boolean,
    val isPhoneNotAdded: Boolean
) : Exception() {

    val isPassed: Boolean
        get() = isTitleEmpty
                || isActTypeNotSelected
                || isDescriptionEmpty
                || isPhoneNotAdded
}