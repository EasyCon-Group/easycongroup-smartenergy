package uz.easycongroup.smartenergy.presentation.utils.enumexts

import uz.easycongroup.smartenergy.R
import uz.easycongroup.smartenergy.data.models.user.role.UserRole
import uz.easycongroup.smartenergy.data.models.user.role.UserRole.*
import uz.easycongroup.smartenergy.domain.data.models.sort.SortType
import uz.easycongroup.smartenergy.domain.data.models.sort.SortType.*

val SortType.nameResId: Int
    get() = when (this) {
        SORT_BY_POPULARITY -> R.string.sort_type_by_popularity
        SORT_BY_PRICE -> R.string.sort_type_by_price
        SORT_BY_RATING -> R.string.sort_type_by_rating
        SORT_BY_REVIEW -> R.string.sort_type_by_feedback
        SORT_BY_DISCOUNT -> R.string.sort_type_by_discount
        SORT_BY_NEW -> R.string.sort_type_by_new
    }

val UserRole.nameResId: Int
    get() = when (this) {
        MANAGER -> R.string.user_role_manager
        SUPERVISOR -> R.string.user_role_supervisor
        CITIZEN -> R.string.user_role_citizen
    }