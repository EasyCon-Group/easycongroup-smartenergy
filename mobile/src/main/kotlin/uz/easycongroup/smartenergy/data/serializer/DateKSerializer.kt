package uz.easycongroup.smartenergy.data.serializer

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.text.SimpleDateFormat
import java.util.*

internal object DateKSerializer {
    //    "created_at": "2020-03-27T09:57:31.441492+05:00",
    private val dateFormat =
        SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSSZ", Locale("uz", "UZ"))

    object NotNullable : KSerializer<Date> {
        override val descriptor = PrimitiveSerialDescriptor(
            serialName = "DateKSerializer",
            kind = PrimitiveKind.STRING
        )

        override fun deserialize(decoder: Decoder): Date =
            checkNotNull(dateFormat.parse(decoder.decodeString()))

        override fun serialize(encoder: Encoder, obj: Date) {
            encoder.encodeString(dateFormat.format(obj))
        }
    }

    object Nullable : KSerializer<Date?> {
        override val descriptor = PrimitiveSerialDescriptor(
            serialName = "DateKSerializer",
            kind = PrimitiveKind.STRING
        )

        override fun deserialize(decoder: Decoder): Date? =
            dateFormat.parse(decoder.decodeString())

        override fun serialize(encoder: Encoder, obj: Date?) {
            obj?.let { encoder.encodeString(dateFormat.format(obj)) }
        }
    }
}