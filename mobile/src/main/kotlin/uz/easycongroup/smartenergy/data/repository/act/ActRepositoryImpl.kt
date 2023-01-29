package uz.easycongroup.smartenergy.data.repository.act

import android.content.Context
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import uz.easycongroup.smartenergy.data.datasource.rest.service.ActRestService
import uz.easycongroup.smartenergy.data.utils.restCode
import uz.easycongroup.smartenergy.domain.data.models.act.creation.ActSubmitParams
import uz.easycongroup.smartenergy.domain.data.repository.act.ActRepository
import java.io.File
import javax.inject.Inject

internal class ActRepositoryImpl @Inject constructor(
    private val actRestService: ActRestService,
    private val applicationContext: Context
) : ActRepository {

    override fun submitAct(params: ActSubmitParams): Flow<Unit> {
        val requestBodyBuilder: MultipartBody.Builder = MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart("category_id", params.actType.restCode)
            .addFormDataPart("title", params.title)
            .addFormDataPart("description", params.description)

        params.images.forEach { uri ->
            val photoFile = File(applicationContext.cacheDir, "/${uri.lastPathSegment}")
            requestBodyBuilder.addFormDataPart("images[]", photoFile.name, photoFile.body)
        }

        return actRestService.submitAct(requestBodyBuilder.build())
            .map { Unit }
    }

    private val File.body
        get() = asRequestBody("image/jpg".toMediaTypeOrNull())
}