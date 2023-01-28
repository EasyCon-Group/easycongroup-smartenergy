package uz.easycongroup.smartenergy.core.data.serializer

import kotlinx.serialization.*
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

object DefaultDateSerializer {

    class NotNullable(pattern: String = "yyyy-MM-dd") : KSerializer<Date> {
        private val dateFormat: DateFormat =
            SimpleDateFormat(pattern, Locale("uz", "UZ"))

        override val descriptor =
            PrimitiveSerialDescriptor(
                serialName = "NotNullableDateSerializer",
                kind = PrimitiveKind.STRING
            )

        override fun deserialize(decoder: Decoder): Date =
            checkNotNull(dateFormat.parse(decoder.decodeString()))

        override fun serialize(encoder: Encoder, value: Date) {
            encoder.encodeString(dateFormat.format(value))
        }
    }

    class Nullable(pattern: String = "yyyy-MM-dd") : KSerializer<Date?> {
        private val dateFormat: DateFormat =
            SimpleDateFormat(pattern, Locale("uz", "UZ"))

        override val descriptor =
            PrimitiveSerialDescriptor(
                serialName = "NullableDateKSerializer",
                kind = PrimitiveKind.STRING
            )

        override fun deserialize(decoder: Decoder): Date? =
            dateFormat.parse(decoder.decodeString())

        override fun serialize(encoder: Encoder, value: Date?) {
            value?.let { encoder.encodeString(dateFormat.format(it)) } ?: encoder.encodeNull()
        }
    }
}