package uz.easycongroup.smartenergy.presentation.utils

import uz.easycongroup.smartenergy.R
import uz.easycongroup.smartenergy.data.models.user.role.UserRole
import uz.easycongroup.smartenergy.data.models.user.role.UserRole.*
import uz.easycongroup.smartenergy.domain.data.models.act.type.ActType
import uz.easycongroup.smartenergy.domain.data.models.act.type.ActType.*
import uz.easycongroup.smartenergy.domain.data.models.photo.PhotoAction
import uz.easycongroup.smartenergy.domain.data.models.photo.PhotoAction.ACTION_ADD_FROM_CAMERA
import uz.easycongroup.smartenergy.domain.data.models.photo.PhotoAction.ACTION_ADD_FROM_GALLERY
import uz.easycongroup.smartenergy.domain.data.models.sort.SortType
import uz.easycongroup.smartenergy.domain.data.models.sort.SortType.*

val ActType.nameResId: Int
    get() = when (this) {
        SET_COUNTER -> R.string.act_type_set_counter
        REPLACE_COUNTER -> R.string.ac_type_replace_counter
        ILLEGAL_USAGE -> R.string.act_type_illegal_usage
    }

val PhotoAction.nameResId
    get() = when (this) {
        ACTION_ADD_FROM_CAMERA -> R.string.photo_action_from_camery
        ACTION_ADD_FROM_GALLERY -> R.string.photo_action_from_gallery
    }

val PhotoAction.iconResId
    get() = when (this) {
        ACTION_ADD_FROM_CAMERA -> R.drawable.ic_action_add_from_camera
        ACTION_ADD_FROM_GALLERY -> R.drawable.ic_action_add_from_gallery
    }

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