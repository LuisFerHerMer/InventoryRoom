package com.example.inventory.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * Clase de base de datos con un objeto Instance singleton.
 *
 * Esta clase abstracta define la base de datos Room para la aplicación de inventario.
 * Utiliza el patrón singleton para asegurar que solo haya una única instancia de la
 * base de datos en toda la aplicación, lo cual es una práctica recomendada para
 * gestionar los recursos de la base de datos de manera eficiente.
 */
@Database(entities = [Item::class], version = 1, exportSchema = false)
abstract class InventoryDatabase : RoomDatabase() {

    /**
     * Proporciona una instancia del Data Access Object (DAO) para la entidad [Item].
     *
     * El DAO es una interfaz que define los métodos para interactuar con la base de datos
     * en relación con la tabla de `Item`. Room genera automáticamente la implementación
     * de esta interfaz.
     *
     * @return Una instancia de [ItemDao].
     */
    abstract fun itemDao(): ItemDao

    companion object {
        @Volatile
        private var Instance: InventoryDatabase? = null

        /**
         * Obtiene la instancia singleton de la base de datos.
         *
         * Si la instancia de la base de datos ya existe, se devuelve la instancia existente.
         * De lo contrario, se crea una nueva instancia de la base de datos utilizando
         * Room Database Builder y se guarda en la variable [Instance] para futuras
         * solicitudes. El uso de `synchronized` asegura que la creación de la base de datos
         * sea thread-safe.
         *
         * @param context El contexto de la aplicación necesario para construir la base de datos.
         * @return La instancia singleton de [InventoryDatabase].
         */
        fun getDatabase(context: Context): InventoryDatabase {
            // si la instancia no es nula, la devuelve, de lo contrario crea una nueva instancia de la base de datos.
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, InventoryDatabase::class.java, "item_database")
                    /**
                     * Establecer esta opción en el constructor de la base de datos de tu aplicación
                     * significa que Room elimina permanentemente todos los datos de las tablas de tu
                     * base de datos cuando intenta realizar una migración sin una ruta de migración
                     * definida.
                     */
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }
        }
    }
}