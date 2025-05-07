package com.example.inventory.data

import kotlinx.coroutines.flow.Flow

/**
 * Implementación de [ItemsRepository] que interactúa con una fuente de datos local (offline),
 * en este caso, a través de un [ItemDao].
 *
 * Esta clase concreta implementa la interfaz [ItemsRepository] y se encarga de realizar
 * las operaciones de acceso a datos para la entidad [Item] utilizando un objeto [ItemDao].
 * Esto significa que los datos se almacenan y recuperan de la base de datos local definida
 * por Room.
 *
 * @property itemDao El Data Access Object ([ItemDao]) utilizado para interactuar con la
 * tabla "items" de la base de datos. Esta dependencia se inyecta en el constructor.
 */
class OfflineItemsRepository(private val itemDao: ItemDao) : ItemsRepository {

    /**
     * Recupera todos los elementos de la base de datos local como un flujo de lista de [Item].
     *
     * Esta función simplemente delega la llamada al método [getAllItems()] del [ItemDao]
     * para obtener un `Flow` que emite la lista de todos los elementos almacenados en la
     * tabla "items" de la base de datos, ordenados por nombre.
     *
     * @return Un `Flow` que emite una lista de todos los [Item] en la base de datos.
     */
    override fun getAllItemsStream(): Flow<List<Item>> = itemDao.getAllItems()

    /**
     * Recupera un elemento específico de la base de datos local como un flujo de [Item] (nullable).
     *
     * Esta función delega la llamada al método [getItem(id: Int)] del [ItemDao] para obtener
     * un `Flow` que emite el [Item] con el ID especificado. Si no se encuentra ningún
     * elemento con ese ID en la base de datos, el `Flow` emitirá `null`.
     *
     * @param id El ID del [Item] que se va a recuperar.
     * @return Un `Flow` que emite el [Item] con el ID especificado, o `null` si no se encuentra.
     */
    override fun getItemStream(id: Int): Flow<Item?> = itemDao.getItem(id)

    /**
     * Inserta un nuevo [Item] en la base de datos local.
     *
     * Esta función de suspensión (`suspend`) delega la llamada al método [insert(item: Item)]
     * del [ItemDao] para insertar el objeto [Item] proporcionado en la tabla "items" de la
     * base de datos.
     *
     * @param item El [Item] que se va a insertar en la base de datos.
     */
    override suspend fun insertItem(item: Item) = itemDao.insert(item)

    /**
     * Elimina un [Item] de la base de datos local.
     *
     * Esta función de suspensión (`suspend`) delega la llamada al método [delete(item: Item)]
     * del [ItemDao] para eliminar el objeto [Item] proporcionado de la tabla "items" de la
     * base de datos.
     *
     * @param item El [Item] que se va a eliminar de la base de datos.
     */
    override suspend fun deleteItem(item: Item) = itemDao.delete(item)

    /**
     * Actualiza un [Item] existente en la base de datos local.
     *
     * Esta función de suspensión (`suspend`) delega la llamada al método [update(item: Item)]
     * del [ItemDao] para actualizar el registro correspondiente al objeto [Item] proporcionado
     * en la tabla "items" de la base de datos.
     *
     * @param item El [Item] con los datos actualizados.
     */
    override suspend fun updateItem(item: Item) = itemDao.update(item)
}