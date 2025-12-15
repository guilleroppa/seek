# Reto técnico de Seek

## Requisitos
- Java 17
- Spring Boot 4.0.0
- Maven 3.9.8
- MYSQL

## Configuración
- Clona el repositorio
- Configura la base de datos en `application.properties`
- Limpia y contruye el proyecto `mvn clean install`
- Ejecuta el proyecto con `mvn spring-boot:run`

## Endpoints
- `POST /clientes`: Registra cliente
- Request Body:
  ```json
  {
      "nombre": "Veronika",
      "apellido": "Galvez",
      "edad": 33,
      "fechaNacimiento": "1992-07-20"
  }
- `GET /clientes`
- `GET /clientes/metricas`
- `GET /clientes/con-esperanza`

## Pruebas
- Ejecuta las pruebas con `mvn test`

## Enlaces
- Swagger: http://localhost:8080/swagger-ui/index.html
- MYSQL DB: http://localhost:3306/clientesdb?useSSL=false&serverTimezone=UTC
