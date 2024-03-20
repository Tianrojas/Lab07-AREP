# Taller 7 - Desarrollo de Aplicación Web Segura

## Descripción

En este proyecto, se desarrolló una aplicación web segura utilizando Spark. La aplicación permite autenticación de usuarios y garantiza la integridad de los datos a través de la comunicación segura entre los servicios.

## Arquitectura del Prototipo

El prototipo sigue una arquitectura cliente-servidor, donde el servidor ofrece servicios de autenticación y autorización. Se implementa una capa de seguridad adicional utilizando HTTPS para garantizar la integridad y confidencialidad de la comunicación.

## Estructura del Repositorio

El repositorio contiene los siguientes elementos principales:

- **`src/`:** Directorio que contiene el código fuente de la aplicación.
- **`keystores/`:** Directorio que contiene los archivos de almacenamiento de claves para configurar HTTPS.
- **`README.md`:** Documentación detallada del proyecto.

## Componentes Principales

Los componentes principales del proyecto son:

- **AuthServer:** Clase principal que configura y ejecuta el servidor de autenticación.
- **SecureUrlReader:** Clase que maneja la lectura segura de URL para autenticar usuarios.
- **Encoder:** Clase que proporciona funcionalidades de codificación de contraseñas.
- **AuthDB:** Clase que simula una base de datos de usuarios para la autenticación.

## Flujo de Trabajo

El flujo de trabajo del proyecto es el siguiente:

1. El servidor AuthServer se inicia y configura para proporcionar servicios de autenticación.
2. El cliente (navegador web) envía solicitudes de autenticación al servidor.
3. El servidor verifica las credenciales del usuario utilizando la clase AuthDB y SecureUrlReader.
4. El servidor responde al cliente con un mensaje de éxito o error según la autenticación.

## Ejemplo de Uso

Para ejecutar el proyecto, sigue estos pasos:

1. Clona el repositorio desde GitHub.
2. Accede al directorio del proyecto.
3. Ejecuta la aplicación Java utilizando los siguientes comandos:
   ```bash
   java -cp "target/classes:target/dependency/*" org.eci.auth.AuthServer
   java -cp "target/classes;target/dependency/*" org.eci.auth.AuthDB
   ```
4. Accede a la aplicación desde tu navegador web utilizando la URL proporcionada por el servidor.
   ```bash
   https://localhost:4500/index.html
   ```
5. Video despliegue en AWS:
   ![Video guiado](https://github.com/Tianrojas/Lab07-AREP/blob/main/media/2024-03-20%03-37-04.mp4)

## Instrucciones de Ejecución

1. Asegurate que tienes Maven y JavaJDK 17 actualizado con las versiones correctas.
2. Ejecuta la aplicación Java utilizando el comando proporcionado anteriormente.
3. Accede a la aplicación desde tu navegador web utilizando la URL proporcionada por el servidor.
