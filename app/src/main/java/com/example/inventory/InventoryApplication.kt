package com.example.inventory

import android.app.Application
import com.example.inventory.data.AppContainer
import com.example.inventory.data.AppDataContainer

/**
 * Clase de aplicación principal para la aplicación de inventario.
 *
 * Esta clase extiende [Application] y se inicializa antes que cualquier otra
 * componente de la aplicación. Se utiliza principalmente para configurar
 * el contenedor de dependencias ([AppContainer]) que se utilizará para
 * proporcionar instancias de las diferentes dependencias a través de la aplicación.
 */
class InventoryApplication : Application() {

    /**
     * Instancia de [AppContainer] utilizada por el resto de las clases para obtener dependencias.
     *
     * Esta propiedad lateinit se inicializa en el método [onCreate()] y proporciona
     * una forma centralizada para que otras partes de la aplicación accedan a las
     * implementaciones de las interfaces de repositorio y otros servicios necesarios.
     * Al utilizar un contenedor, se facilita la gestión de las dependencias y se
     * promueve un código más limpio y fácil de probar.
     */
    lateinit var container: AppContainer

    /**
     * Se llama cuando se crea la aplicación por primera vez.
     *
     * Este método de ciclo de vida de la aplicación se utiliza para realizar la
     * inicialización esencial de la aplicación. En este caso, se encarga de crear
     * una instancia de la implementación concreta de [AppContainer] ([AppDataContainer])
     * y asignarla a la propiedad [container]. El contexto de la aplicación (`this`)
     * se pasa al constructor de [AppDataContainer] para que pueda inicializar las
     * dependencias que requieren un contexto, como la base de datos.
     */
    override fun onCreate() {
        super.onCreate()
        /**
         * Crea una instancia de [AppDataContainer] pasando el contexto de la aplicación.
         * Esta instancia se asigna a la propiedad [container], haciendo que el contenedor
         * de dependencias esté disponible para el resto de la aplicación.
         */
        container = AppDataContainer(this)
    }
}