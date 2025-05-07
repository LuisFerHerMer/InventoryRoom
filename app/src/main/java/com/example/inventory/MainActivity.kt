package com.example.inventory

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.inventory.ui.theme.InventoryTheme

/**
 * Actividad principal de la aplicación de inventario.
 *
 * Esta clase representa el punto de entrada principal de la interfaz de usuario
 * de la aplicación. Extiende [ComponentActivity], que es una clase base para
 * actividades que utilizan Jetpack Compose para construir su interfaz de usuario.
 */
class MainActivity : ComponentActivity() {
    /**
     * Se llama cuando se crea la actividad por primera vez.
     *
     * Este método de ciclo de vida de la actividad se utiliza para realizar la
     * inicialización esencial de la actividad. Aquí, se habilita el modo "edge-to-edge",
     * se llama a la implementación de [onCreate] de la superclase y se configura
     * el contenido de la interfaz de usuario utilizando Jetpack Compose.
     *
     * @param savedInstanceState Si la actividad se vuelve a inicializar después de
     * haber sido cerrada previamente, este Bundle contiene los datos que guardó
     * más recientemente en [onSaveInstanceState]. Puede ser nulo.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        /**
         * Habilita el modo "edge-to-edge". Esto permite que la aplicación dibuje
         * detrás de las barras de navegación y de estado del sistema, proporcionando
         * una apariencia más inmersiva.
         */
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        /**
         * Establece el contenido de la interfaz de usuario de la actividad utilizando
         * el DSL de Compose. El lambda proporcionado a [setContent] define la jerarquía
         * de composables que se mostrarán en la pantalla.
         */
        setContent {
            /**
             * Aplica el tema de la aplicación de inventario ([InventoryTheme]) a la
             * jerarquía de composables. Esto asegura que los elementos de la interfaz
             * de usuario sigan el estilo visual definido en el tema.
             */
            InventoryTheme {
                /**
                 * Un contenedor básico que ocupa todo el espacio disponible en su padre.
                 * Se utiliza aquí para envolver el contenido principal de la aplicación.
                 */
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    /**
                     * Invoca el composable de nivel superior [InventoryApp], que contiene y
                     * gestiona la navegación entre las diferentes pantallas de la aplicación.
                     */
                    InventoryApp()
                }
            }
        }
    }
}