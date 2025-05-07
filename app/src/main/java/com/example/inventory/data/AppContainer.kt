package com.example.inventory.data

import android.content.Context

/**
 * Contenedor de la aplicación para la inyección de dependencias.
 *
 * Esta interfaz define el contrato para proporcionar las dependencias necesarias
 * a través de la aplicación. Actualmente, expone una única dependencia:
 * [ItemsRepository].
 */
interface AppContainer {
    val itemsRepository: ItemsRepository
}

/**
 * Implementación de [AppContainer] que proporciona una instancia de [OfflineItemsRepository].
 *
 * Esta clase concreta implementa la interfaz [AppContainer] y es responsable de
 * crear y proporcionar las instancias reales de las dependencias definidas en la interfaz.
 * En este caso, se encarga de crear una instancia de [OfflineItemsRepository].
 *
 * @property context El contexto de la aplicación necesario para acceder a la base de datos.
 */
class AppDataContainer(private val context: Context) : AppContainer {
    /**
     * Implementación para [ItemsRepository].
     *
     * Esta propiedad utiliza la delegación `lazy` para asegurar que la instancia de
     * [OfflineItemsRepository] se cree solo cuando se accede a ella por primera vez.
     * La instancia de [OfflineItemsRepository] se inicializa con una instancia de
     * [ItemDao] obtenida de la base de datos de inventario ([InventoryDatabase]).
     */
    override val itemsRepository: ItemsRepository by lazy {
        OfflineItemsRepository(InventoryDatabase.getDatabase(context).itemDao())
    }
}