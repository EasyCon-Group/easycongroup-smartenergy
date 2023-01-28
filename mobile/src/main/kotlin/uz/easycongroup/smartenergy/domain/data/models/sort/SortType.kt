package uz.easycongroup.smartenergy.domain.data.models.sort

enum class SortType {

    SORT_BY_POPULARITY,
    SORT_BY_PRICE,
    SORT_BY_REVIEW,
    SORT_BY_RATING,
    SORT_BY_DISCOUNT,
    SORT_BY_NEW;

    companion object {
        val DEFAULT = SORT_BY_POPULARITY
    }
}