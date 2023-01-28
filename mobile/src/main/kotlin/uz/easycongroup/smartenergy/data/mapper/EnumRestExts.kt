package uz.easycongroup.smartenergy.data.mapper

import uz.easycongroup.smartenergy.domain.data.models.rest.RemoteConstants.CONST_SORT_BY_DISCOUNT
import uz.easycongroup.smartenergy.domain.data.models.rest.RemoteConstants.CONST_SORT_BY_NEW
import uz.easycongroup.smartenergy.domain.data.models.rest.RemoteConstants.CONST_SORT_BY_POPULARITY
import uz.easycongroup.smartenergy.domain.data.models.rest.RemoteConstants.CONST_SORT_BY_PRICE
import uz.easycongroup.smartenergy.domain.data.models.rest.RemoteConstants.CONST_SORT_BY_RATING
import uz.easycongroup.smartenergy.domain.data.models.rest.RemoteConstants.CONST_SORT_BY_REVIEW
import uz.easycongroup.smartenergy.domain.data.models.sort.SortType
import uz.easycongroup.smartenergy.domain.data.models.sort.SortType.*

val SortType?.restCode: String
    get() = when (this) {
        null -> ""
        SORT_BY_POPULARITY -> CONST_SORT_BY_POPULARITY
        SORT_BY_PRICE -> CONST_SORT_BY_PRICE
        SORT_BY_RATING -> CONST_SORT_BY_RATING
        SORT_BY_REVIEW -> CONST_SORT_BY_REVIEW
        SORT_BY_DISCOUNT -> CONST_SORT_BY_DISCOUNT
        SORT_BY_NEW -> CONST_SORT_BY_NEW
    }