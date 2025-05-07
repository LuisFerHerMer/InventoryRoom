package com.example.inventory

import androidx.compose.material.icons.Icons.Filled
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.inventory.R.string
import com.example.inventory.ui.navigation.InventoryNavHost

/**
 * Composable de nivel superior que representa las pantallas de la aplicación.
 *
 * Este composable es el punto de entrada para la interfaz de usuario de la aplicación
 * de inventario construida con Jetpack Compose. Contiene y gestiona la navegación
 * entre las diferentes pantallas de la aplicación utilizando un [NavHostController].
 */
@Composable
fun InventoryApp(navController: NavHostController = rememberNavController()) {
    /**
     * Invoca el [InventoryNavHost] para configurar y gestionar la navegación dentro de la aplicación.
     * El [NavHostController] proporcionado se utiliza para controlar a qué composable se navega
     * y cómo se realiza la transición entre ellos. Si no se proporciona un [NavHostController],
     * se crea uno nuevo utilizando [rememberNavController()].
     */
    InventoryNavHost(navController = navController)
}

/**
 * Barra de aplicación para mostrar el título y, condicionalmente, la navegación hacia atrás.
 *
 * Este composable define una barra de aplicación personalizada que se puede utilizar en
 * diferentes pantallas de la aplicación. Muestra un título centrado y, si se indica
 * mediante el parámetro [canNavigateBack], muestra un icono de flecha hacia atrás que
 * invoca la función [navigateUp] cuando se pulsa.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InventoryTopAppBar(
    title: String,
    canNavigateBack: Boolean,
    modifier: Modifier = Modifier,
    scrollBehavior: TopAppBarScrollBehavior? = null,
    navigateUp: () -> Unit = {}
) {
    CenterAlignedTopAppBar(
        /**
         * Muestra el título proporcionado en la barra de aplicación.
         */
        title = { Text(title) },
        /**
         * Aplica un [Modifier] opcional a la barra de aplicación para personalizar su apariencia
         * o diseño.
         */
        modifier = modifier,
        /**
         * Permite proporcionar un [TopAppBarScrollBehavior] para habilitar efectos de desplazamiento
         * en la barra de aplicación cuando el contenido de la pantalla se desplaza.
         */
        scrollBehavior = scrollBehavior,
        /**
         * Define el icono de navegación que se muestra en el lado izquierdo de la barra de
         * aplicación. Solo se muestra si [canNavigateBack] es `true`.
         */
        navigationIcon = {
            if (canNavigateBack) {
                /**
                 * Muestra un [IconButton] que contiene el icono de navegación hacia atrás.
                 */
                IconButton(onClick = navigateUp) {
                    /**
                     * Muestra el icono de flecha hacia atrás utilizando [Icons.Filled.ArrowBack].
                     */
                    Icon(
                        imageVector = Filled.ArrowBack,
                        /**
                         * Proporciona una descripción de contenido localizada para el icono, importante
                         * para la accesibilidad. Se obtiene de la cadena de recurso `string.back_button`.
                         */
                        contentDescription = stringResource(string.back_button)
                    )
                }
            }
        }
    )
}