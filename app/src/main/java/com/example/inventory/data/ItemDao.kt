package com.example.inventory.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

/**
 * Objeto de acceso a datos de la base de datos del Inventario.
 *
 * Esta interfaz define los métodos utilizados para interactuar con la tabla "items"
 * de la base de datos del inventario. Al estar anotada con `@Dao`, Room proporciona
 * automáticamente la implementación de estos métodos en tiempo de compilación.
 */
@Dao
interface ItemDao {

    /**
     * Obtiene todos los elementos de la tabla "items" ordenados alfabéticamente por nombre.
     *
     * Esta función utiliza una consulta SQL para seleccionar todos los registros de la
     * tabla "items" y los ordena de forma ascendente según la columna "name".
     * El resultado se envuelve en un `Flow`, lo que permite observar los cambios en
     * la base de datos de forma asíncrona.
     *
     * @return Un `Flow` que emite una lista de todos los [Item] en la base de datos,
     * ordenados por nombre.
     */
    @Query("SELECT * from items ORDER BY name ASC")
    fun getAllItems(): Flow<List<Item>>

    /**
     * Obtiene un elemento específico de la tabla "items" basándose en su ID.
     *
     * Esta función utiliza una consulta SQL para seleccionar el registro de la tabla
     * "items" cuyo ID coincide con el valor proporcionado. El resultado se envuelve
     * en un `Flow`, lo que permite observar los cambios en este elemento específico
     * de forma asíncrona.
     *
     * @param id El ID del [Item] que se va a recuperar.
     * @return Un `Flow` que emite el [Item] con el ID especificado.
     */
    @Query("SELECT * from items WHERE id = :id")
    fun getItem(id: Int): Flow<Item>

    /**
     * Inserta un nuevo [Item] en la tabla "items".
     *
     * Esta función utiliza la anotación `@Insert` de Room para generar la consulta SQL
     * necesaria para insertar el objeto `item` proporcionado en la base de datos.
     * Se especifica la estrategia de conflicto como `OnConflictStrategy.IGNORE`, lo que
     * significa que si se intenta insertar un elemento con una clave primaria que ya
     * existe en la base de datos, la operación de inserción se ignorará.
     *
     * Esta función es una función de suspensión (`suspend`), lo que significa que debe
     * ser llamada desde una corrutina o desde otra función de suspensión.
     *
     * @param item El [Item] que se va a insertar en la base de datos.
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: Item)

    /**
     * Actualiza un [Item] existente en la tabla "items".
     *
     * Esta función utiliza la anotación `@Update` de Room para generar la consulta SQL
     * necesaria para actualizar el registro correspondiente al objeto `item` proporcionado
     * en la base de datos. Room utiliza la clave primaria del objeto para identificar
     * la fila que se debe actualizar.
     *
     * Esta función es una función de suspensión (`suspend`), lo que significa que debe
     * ser llamada desde una corrutina o desde otra función de suspensión.
     *
     * @param item El [Item] con los datos actualizados.
     */
    @Update
    suspend fun update(item: Item)

    /**
     * Elimina un [Item] de la tabla "items".
     *
     * Esta función utiliza la anotación `@Delete` de Room para generar la consulta SQL
     * necesaria para eliminar el registro correspondiente al objeto `item` proporcionado
     * de la base de datos. Room utiliza la clave primaria del objeto para identificar
     * la fila que se debe eliminar.
     *
     * Esta función es una función de suspensión (`suspend`), lo que significa que debe
     * ser llamada desde una corrutina o desde otra función de suspensión.
     *
     * @param item El [Item] que se va a eliminar de la base de datos.
     */
    @Delete
    suspend fun delete(item: Item)
}