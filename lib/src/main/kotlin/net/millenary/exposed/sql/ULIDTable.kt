package net.millenary.exposed.sql

import com.github.guepardoapps.kulid.ULID
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IdTable
import org.jetbrains.exposed.sql.Column

/**
 * Identity table with [ULID] primary key.
 *
 * Id value will be generated on a client side just before an insert of a new row.
 *
 * @param name table name, by default name will be resolved from a class name with "Table" suffix removed (if present)
 * @param columnName name for a primary key, "id" by default
 */
open class ULIDTable(name: String = "", columnName: String = "id") : IdTable<String>(name) {

    override val id: Column<EntityID<String>> =
        registerColumn<String>("id", ULIDColumnType())
            .autoGenerateUlid()
            .entityId()

    override val primaryKey by lazy { super.primaryKey ?: PrimaryKey(id) }

    /** ULID column will auto generate its value on a client side just before an insert. */
    private fun Column<String>.autoGenerateUlid(): Column<String> = clientDefault { ULID.random() }
}
