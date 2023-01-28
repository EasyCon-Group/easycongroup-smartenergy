package uz.easycongroup.smartenergy.data.serializer

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.math.BigDecimal

internal object BigDecimalKSerializer {

    object NotNullable : KSerializer<BigDecimal> {
        override val descriptor =
            PrimitiveSerialDescriptor(
                serialName = "NotNullableBigDecimalKSerializer",
                kind = PrimitiveKind.STRING
            )

        override fun deserialize(decoder: Decoder): BigDecimal =
            BigDecimal(decoder.decodeString())

        override fun serialize(encoder: Encoder, obj: BigDecimal) {
            encoder.encodeString(obj.toString())
        }
    }

    object Nullable : KSerializer<BigDecimal?> {
        override val descriptor =
            PrimitiveSerialDescriptor(
                serialName = "NullableBigDecimalKSerializer",
                kind = PrimitiveKind.STRING
            )

        override fun deserialize(decoder: Decoder): BigDecimal? =
            BigDecimal(decoder.decodeString())

        override fun serialize(encoder: Encoder, obj: BigDecimal?) {
            obj?.let { encoder.encodeString(it.toString()) }
        }
    }
}