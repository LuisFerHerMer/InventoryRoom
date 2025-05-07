package com.example.inventory.data

import kotlinx.coroutines.flow.Flow

/**
 * Repositorio que proporciona operaciones de inserción, actualización, eliminación y recuperación de [Item]
 * desde una fuente de datos dada.
 *
 * Esta interfaz define el contrato para un repositorio que gestiona el acceso a los datos
 * de la entidad [Item]. Actúa como una capa de abstracción entre la capa de la interfaz
 * de usuario (o la capa de lógica de negocio) y la fuente de datos subyacente (que podría
 * ser una base de datos local, una API remota, etc.). Esto permite que el resto de la
 * aplicación interactúe con los datos de [Item] sin necesidad de conocer los detalles
 * específicos de la implementación de la fuente de datos.
 */
interface ItemsRepository {
    /**
     * Recupera todos los elementos de la fuente de datos dada.
     *
     * Esta función devuelve un `Flow` que emite una lista de todos los objetos [Item]
     * disponibles en la fuente de datos. El uso de `Flow` permite la observación
     * asíncrona de los cambios en la colección de elementos.
     *
     * @return Un `Flow` que emite una lista de todos los [Item].
     */
    fun getAllItemsStream(): Flow<List<Item>>

    /**
     * Recupera un elemento de la fuente de datos dada que coincide con el [id] proporcionado.
     *
     * Esta función devuelve un `Flow` que emite el objeto [Item] cuyo ID coincide con el
     * valor proporcionado. Si no se encuentra ningún elemento con ese ID, el `Flow`
     * emitirá `null`. El uso de `Flow` permite la observación asíncrona del elemento.
     *
     * @param id El ID del [Item] que se va a recuperar.
     * @return Un `Flow` que emite el [Item] con el ID especificado, o `null` si no se encuentra.
     */
    fun getItemStream(id: Int): Flow<Item?>

    /**
     * Inserta un [Item] en la fuente de datos.
     *
     * Esta función de suspensión (`suspend`) se utiliza para insertar un nuevo objeto [Item]
     * en la fuente de datos subyacente. Debe ser llamada desde una corrutina o desde
     * otra función de suspensión.
     *
     * @param item El [Item] que se va a insertar.
     */
    suspend fun insertItem(item: Item)

    /**
     * Elimina un [Item] de la fuente de datos.
     *
     * Esta función de suspensión (`suspend`) se utiliza para eliminar el objeto [Item]
     * proporcionado de la fuente de datos subyacente. Debe ser llamada desde una
     * corrutina o desde otra función de suspensión.
     *
     * @param item El [Item] que se va a eliminar.
     */
    suspend fun deleteItem(item: Item)

    /**
     * Actualiza un [Item] existente en la fuente de datos.
     *
     * Esta función de suspensión (`suspend`) se utiliza para actualizar los datos del
     * objeto [Item] proporcionado en la fuente de datos subyacente. Debe ser llamada
     * desde una corrutina o desde otra función de suspensión.
     *
     * @param item El [Item] con los datos actualizados.
     */
    suspend fun updateItem(item: Item)
}