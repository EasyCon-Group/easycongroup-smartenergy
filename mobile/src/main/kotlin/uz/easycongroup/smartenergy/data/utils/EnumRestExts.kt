package uz.easycongroup.smartenergy.data.utils

import uz.easycongroup.smartenergy.domain.data.models.act.type.ActType
import uz.easycongroup.smartenergy.domain.data.models.act.type.ActType.*

val ActType.restCode: String
    get() = this.name
