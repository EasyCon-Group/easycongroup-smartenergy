package uz.easycongroup.smartenergy.core.data.serializer

import kotlinx.serialization.SerializationStrategy
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject

@Suppress("EXPERIMENTAL_API_USAGE")
internal fun <T> SerializationStrategy<T>.asJsonElement(format: Json = Json.actual, obj: T) =
    with(Json) { format.encodeToJsonElement(this@asJsonElement, obj) }

@Suppress("EXPERIMENTAL_API_USAGE")
internal fun <T> SerializationStrategy<T>.asJsonElement(obj: T) =
    asJsonElement(Json.actual, obj)

internal fun JsonObject.Companion.create(vararg pair: Pair<String, JsonElement?>): JsonObject =
    create(pair.toMap())

internal fun JsonObject.Companion.create(content: Map<String, JsonElement?>): JsonObject {
    val result: MutableMap<String, JsonElement> = hashMapOf()

    for (entry: Map.Entry<String, JsonElement?> in content) {
        if (entry.value == null) continue
        result[entry.key] = checkNotNull(entry.value)
    }

    return JsonObject(result)
}