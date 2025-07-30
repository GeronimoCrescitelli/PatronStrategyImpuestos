# Sistema de Facturación Internacional con Patrón Strategy

Este proyecto es una API REST de facturación internacional desarrollada en **Spring Boot**. Permite crear y consultar facturas en distintos países, calculando el impuesto correspondiente según la nacionalidad seleccionada. El cálculo de impuestos está implementado mediante el **Patrón Strategy**, facilitando la extensión del sistema a nuevos países y reglas fiscales.

---

## ¿Qué es el Patrón Strategy?

El **patrón Strategy** es un patrón de diseño de comportamiento que permite definir una familia de algoritmos, encapsular cada uno de ellos y hacerlos intercambiables. El patrón Strategy permite que el algoritmo varíe independientemente de los clientes que lo utilizan.

### Ventajas de utilizar Strategy

- **Abierto a extensión, cerrado a modificación:** Puedes agregar nuevas estrategias (algoritmos) sin modificar el código existente.
- **Desacoplamiento:** El cliente no necesita conocer los detalles de cada algoritmo, sólo utiliza la interfaz común.
- **Simplicidad en el mantenimiento:** Las reglas o comportamientos especiales se encapsulan en clases propias, facilitando la comprensión y mantenimiento del código.
- **Flexibilidad:** Permite intercambiar algoritmos en tiempo de ejecución, si fuera necesario.

**En este proyecto**, el cálculo del impuesto según el país se encapsula en diferentes estrategias, permitiendo agregar o modificar reglas impositivas de manera simple y segura.

---

## Estructura del Proyecto

```
src/
├── main/
│   ├── java/
│   │   └── com/
│   │       └── example/
│   │           └── demo/
│   │               ├── controller/
│   │               │   ├── FacturaController.java
│   │               ├── dto/
│   │               │   ├── FacturaRequestDTO.java
│   │               │   └── FacturaResponseDTO.java
│   │               ├── strategy/
│   │               │   ├── ImpuestoStrategy.java
│   │               │   ├── ImpuestoArgentinaStrategy.java
│   │               │   ├── ImpuestoBrasilStrategy.java
│   │               │   └── ImpuestoUsaStrategy.java
│   │               ├── model/
│   │               │   ├── Factura.java
│   │               │   └── Pais.java
│   │               ├── repository/
│   │               │   └── FacturaRepository.java
│   │               ├── service/
│   │               │   └── FacturaService.java
│   │               └── StrategyPatternApplication.java
│   │               └── DatosIniciales.java
│   └── resources/
│       └── application.properties
├── test/
│   └── java/...
└── build.gradle
```

---

## Dependencias principales

El proyecto utiliza las siguientes tecnologías:

- **Spring Boot**: Framework principal para desarrollo de aplicaciones Java.
- **Spring Data JPA**: Acceso y persistencia de datos con JPA/Hibernate.
- **H2 Database**: Base de datos en memoria para pruebas y desarrollo.
- **Lombok**: Anotaciones para reducir código boilerplate (getters, setters, builders, etc).
- **Springdoc OpenAPI/Swagger**: Generación automática de documentación y pruebas interactivas de la API.

### Fragmento relevante de `build.gradle`:

```gradle
implementation 'org.springframework.boot:spring-boot-starter-web'
implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
runtimeOnly 'com.h2database:h2'
compileOnly 'org.projectlombok:lombok:1.18.32'
annotationProcessor 'org.projectlombok:lombok:1.18.32'
implementation 'org.springdoc:springdoc-openapi-starter-webmvc-ui:2.5.0'
```

---

## Endpoints principales

- `POST   /api/facturas`  
  Crear una nueva factura.

- `GET    /api/facturas`  
  Listar todas las facturas.

- `GET    /api/facturas/{id}`  
  Consultar factura por ID.

- `GET    /api/facturas/pais/{pais}`  
  Consultar facturas filtradas por país (`ARGENTINA`, `BRASIL`, `ESTADOS_UNIDOS`).

- `GET    /api/facturas/impuesto/{pais}`  
  Consultar la descripción del impuesto aplicado en un país.

---

## Ejemplo de uso

### Crear una factura

```bash
curl -X POST http://localhost:8080/api/facturas \
  -H "Content-Type: application/json" \
  -d '{"cliente":"Ana García","monto":1500,"pais":"ARGENTINA"}'
```

### Consultar el impuesto aplicable en Brasil

```bash
curl -X GET http://localhost:8080/api/facturas/impuesto/BRASIL
```
**Respuesta:**  
`En Brasil el impuesto aplicado es del 17% (ICMS).`

---

## ¿Cómo ejecutar el proyecto?

1. **Clona el repositorio**  
   ```bash
   git clone <url-del-repositorio>
   cd <carpeta-del-proyecto>
   ```

2. **Ejecuta el proyecto**  
   ```bash
   ./gradlew bootRun
   ```
   *(En Windows: `gradlew.bat bootRun`)*

3. **Accede a la API y Swagger**  
   - API disponible en: `http://localhost:8080/`
   - Swagger UI: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)  
     o  
     [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

4. **Base de datos en memoria**  
   - Consola H2: [http://localhost:8080/h2-console](http://localhost:8080/h2-console)
   - JDBC URL: `jdbc:h2:mem:testdb`
   - Usuario: `sa`  
   - (Sin contraseña)

---

## ¿Cómo extender el sistema a un nuevo país?

1. Crea una nueva clase en `strategy/` que implemente `ImpuestoStrategy`.
2. Implementa los métodos `calcularImpuesto` y `obtenerDescripcionImpuesto`.
3. Agrega el nuevo país al enum `Pais`.
4. ¡Listo! El sistema detectará automáticamente la nueva estrategia.

