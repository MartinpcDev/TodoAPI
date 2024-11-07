# TODO API

Challenge basado en: [TODO API](https://roadmap.sh/projects/todo-list-api)
Una API RESTful que permite a los usuarios gestionar una lista de tareas. La API incluye
autenticación de usuario, operaciones CRUD para las tareas, manejo de errores, medidas de seguridad
y soporte para paginación y filtrado.

## Objetivos

Este proyecto te ayudará a desarrollar y fortalecer las siguientes habilidades:

* Autenticación de usuarios
* Diseño de esquemas y bases de datos
* Diseño de API RESTful
* Operaciones CRUD
* Manejo de errores
* Seguridad

## Requisitos

El proyecto cumple con los siguientes requisitos:

* **Registro de Usuarios**: Permite crear un nuevo usuario.
* **Autenticación y Login**: Autenticacion de usuario y genera un token.
* **Operaciones CRUD**: Permite crear, leer, actualizar y eliminar tareas.
* **Autenticación de Usuarios**: Solo los usuarios autorizados pueden acceder a la lista de tareas.
* **Manejo de Errores y Seguridad**: Implementación de medidas de seguridad y manejo de errores.
* **Base de Datos**: Almacena los datos de usuarios y de la lista de tareas en una base de datos.
* **Validación de Datos**: Implementación de validaciones adecuadas en las entradas de datos.
* **Paginación y Filtrado**: La lista de tareas soporta paginación y filtrado para facilitar la
  navegación.

## Endpoints

La API incluye los siguientes endpoints clave:

Autenticación de Usuarios

* **Registro de Usuario** (`POST /register`): Registra un nuevo usuario.
* **Login** (`POST /login`): Autentica al usuario y genera un token de acceso.

## Gestión de Tareas

* **Crear Tarea** (POST /todos): Crea una nueva tarea (requiere autenticación).
* **Obtener Lista de Tareas** (GET /todos): Obtiene la lista de tareas con soporte para paginación y
  filtrado (requiere autenticación).
* **Obtener Lista de Tareas por usuarios** (GET /todos/me): Obtiene la lista de tareas por usuario
  (requiere autenticación).
* **Actualizar Tarea** (PUT /todos/{id}): Actualiza una tarea específica (requiere autenticación).
* **Eliminar Tarea** (DELETE /todos/{id}): Elimina una tarea específica (requiere autenticación).

## Uso

1. **Registro y Login**: Registra un usuario y autentícate para recibir el token de acceso.
2. **Acceso a Endpoints de Tareas**: Usa el token de acceso en el encabezado Authorization para
   realizar operaciones CRUD en las tareas.
3. **Paginación y Filtrado**: Los parámetros page, pageSize y otros filtros opcionales pueden usarse
   en el endpoint de obtener tareas.

## Tecnologías Usadas

* **Backend**: SpringBoot, Java
* **Base de Datos**: PostgreSQL
* **Autenticación**: Spring Security ,JSON Web Tokens (JWT)