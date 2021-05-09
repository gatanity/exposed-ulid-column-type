package net.millenary.exposed.sql

import com.github.guepardoapps.kulid.ULID
import com.google.common.io.BaseEncoding
import org.jetbrains.exposed.sql.ColumnType

/**
 * Binary column for storing [ULID].
 */
class ULIDColumnType: ColumnType() {

    override fun nonNullValueToString(value: Any): String = valueToUlid(value)

    override fun notNullValueToDB(value: Any): Any = BaseEncoding.base32().decode(valueToUlid(value))

    override fun sqlType(): String = dataType

    override fun valueFromDB(value: Any): String = valueToUlid(value)

    private fun valueToUlid(value: Any): String = when {
        value is ByteArray -> ULID.fromString(BaseEncoding.base32().encode(value))
        value is String && ULID.isValid(value) -> value
        value is String -> ULID.fromString(value)
        else -> error("Unexpected value of type ULID: $value of ${value::class.qualifiedName}")
    }

    companion object {
        const val dataType: String = "BINARY(16)"
    }
}
