package uz.easycongroup.smartenergy.domain.data.models.act.creation

import android.net.Uri
import uz.easycongroup.smartenergy.domain.data.models.act.type.ActType

data class ActSubmitParams(
    val actType: ActType,
    val title: String,
    val description: String,
    val images: List<Uri>
)