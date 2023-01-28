package uz.easycongroup.smartenergy.data.datasource.database.converter

import androidx.room.TypeConverter
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.json.Json
import uz.easycongroup.smartenergy.core.data.serializer.actual
import java.math.BigDecimal
import java.text.SimpleDateFormat
import java.util.*

internal class RoomTypeConverters {

    @TypeConverter
    fun fromBigDecimal(bigDecimal: BigDecimal?): String? =
        bigDecimal?.toString()

    @TypeConverter
    fun toBigDecimal(string: String?): BigDecimal? =
        string?.let { BigDecimal(it) }

    @TypeConverter
    fun fromImageList(images: List<String>): String =
        json.encodeToString(ListSerializer(String.serializer()), images)

    @TypeConverter
    fun toImageList(jsonContent: String): List<String> =
        json.decodeFromString(ListSerializer(String.serializer()), jsonContent)

    @TypeConverter
    fun fromDate(date: Date?): String? =
        date?.time?.let { dateFormat.format(it) }

    @TypeConverter
    fun toDate(string: String?): Date? =
        string?.let { dateFormat.parse(it) }


    @Suppress("EXPERIMENTAL_API_USAGE")
    private val json by lazy { Json.actual }

    @Suppress("SpellCheckingInspection")
    private val dateFormat =
        SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale("uz", "UZ"))
}