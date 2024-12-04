
Aplicación de Spring Boot que implementa una API REST para consultar precios según criterios específicos utilizando arquitectura hexagonal.
Este proyecto incluye una base de datos H2 en memoria y soporta pruebas unitarias e integradas.


## Requerimientos
Java 17+
Maven 3.8.1+

## Instalación
1. Clonar el repositorio
2. Ejecutar el comando `mvn clean install` en la raíz del proyecto
3. Ejecutar el comando `mvn spring-boot:run` en la raíz del proyecto
4. La aplicación estará disponible en `http://localhost:8080`
5. Para acceder a la consola de la base de datos H2, ingresar a `http://localhost:8080/h2-console` con los siguientes datos:
    - JDBC URL: `jdbc:h2:mem:testdb`
    - User Name: `bcnc`
    - Password: `bcncpass`
6. Para ejecutar las pruebas unitarias e integradas, ejecutar el comando `mvn test` en la raíz del proyecto

## Uso
La aplicación expone los siguientes endpoints:
/prices
- GET: Obtiene el precio vigente para un producto en una cadena y fecha específica
    - Parámetros:
        - productId: Identificador del producto
        - brandId: Identificador de la cadena
        - requestedDate: Fecha en formato `yyyy-MM-ddTHH:mm:ss`
    - Ejemplo: `http://localhost:8080/prices?productId=35455&brandId=1&requestedDate=2020-06-14T10:00:00`

## Estructura del proyecto
- `src/main/java/com/bcnc/`: Paquete principal
    - `application/`: Clases de aplicación
    - `domain/`: Clases de dominio
    - `infrastructure/`: Clases de infraestructura

- `src/test/java/com/bcnc/`: Paquete de pruebas
- `src/main/resources/`: Recursos de la aplicación
    - `application.properties`: Configuración de la aplicación
    - `data.sql`: Script de inicialización de la base de datos
- `pom.xml`: Archivo de configuración de Maven
- `README.md`: Archivo de documentación

