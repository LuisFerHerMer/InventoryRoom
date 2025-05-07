package com.example.inventory.data

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Clase de datos de entidad que representa una única fila en la base de datos.
 *
 * Esta clase de datos Kotlin está anotada con `@Entity`, lo que indica a Room
 * que esta clase representa una tabla en la base de datos. Cada instancia de
 * esta clase corresponderá a una fila en la tabla "items".
 */
@Entity(tableName = "items")
data class Item(
    /**
     * Identificador único del elemento.
     *
     * Esta propiedad está anotada con `@PrimaryKey`, indicando que es la clave
     * primaria de la tabla "items". El atributo `autoGenerate = true` especifica
     * que la base de datos generará automáticamente valores únicos para este ID
     * al insertar nuevos elementos. Por defecto, su valor es 0 hasta que se
     * inserta el elemento en la base de datos y Room le asigna un ID.
     */
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    /**
     * El nombre del elemento.
     *
     * Esta propiedad almacena el nombre o la descripción del elemento del inventario.
     * Corresponde a una columna en la tabla "items".
     */
    val name: String,

    /**
     * El precio del elemento.
     *
     * Esta propiedad representa el precio unitario del elemento.
     * Corresponde a una columna en la tabla "items".
     */
    val price: Double,

    /**
     * La cantidad disponible del elemento.
     *
     * Esta propiedad indica el número de unidades de este elemento que están
     * actualmente en el inventario. Corresponde a una columna en la tabla "items".
     */
    val quantity: Int
)