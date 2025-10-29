# HuertoHogarMovil

Aplicación móvil desarrollada en Android Studio con Kotlin y Jetpack Compose, basada en el caso semestral “HuertoHogar”, una tienda online de productos saludables y sostenibles.  
Este proyecto es de la asignatura Desarrollo de Aplicaciones Móviles y tiene como objetivo crear a una aplicación móvil usando Material 3, navegación, formularios, Base de datos local, gestión de estado, animaciones y recursos nativos (Ej: Cámara).

---

## Integrantes

Sebastián Soto
Franco González

---

## Herramientas utilizadas
- Lenguaje: Kotlin  
- Framework: Jetpack Compose
- Arquitectura: MVVM  
- Almacenamiento local: SQLite

---

## Funcionalidades

### Pantalla de Bienvenida
- Presenta el nombre de la app y un botón para comenzar.
- Diseñada con componentes Material 3.

### Registro / Login
- Formulario con validaciones visuales y lógicas (campos obligatorios, etc).

### Catálogo de productos
- Lista de productos con nombre, descripción, precio, stock e imagen.
- Filtros por categoría.
- Animación de color al seleccionar filtro.

### Carrito de compras
- Cálculo automático del total.
- Función “Vaciar carrito”.
- Recurso nativo: Compartir boleta del pedido.

### Perfil de usuario
- Muestra la información del usuario registrado.
- Recurso nativo: Captura de foto de perfil con CameraX y guardado local.

---

##  Pasos para ejecutar
1. Clonar el repositorio:
   git clone https://github.com/TOXICSNNZ/HuertoHogarMovil

2. Abrir el proyecto en Android Studio.

3. Verificar que las dependencias de Gradle funcionen.

4. Ejecutar la app en un emulador o dispositivo Android.

5. Otorgar permisos de cámara al abrir el perfil.

6. Para probar el recurso nativo de compartir debe agregar productos al carrito y presionar Confirmar pedido.