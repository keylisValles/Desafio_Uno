# Generador Datos Desafio

Servicio que permite obtener una lista de fechas que un período dado (fecha inicio, fecha fin), el objetivo es listar aquellas fechas que no están en la lista de fecha dada según el rango indicado.
Este proyecto expone un API REST que recibe la siguiente información:

# Entrada
Json con la siguente estructura e información:

*id*: identificador
*fechaCreacion*: Fecha de inicio de la secuencia
*fechaFin*: Fecha de fin de la secuencia
*fechas*: Lista de fechas que están en el rango de la fecha que se encuentra en “fechaCreacion” hasta la fecha “fechaFin” de manera aleatoria
Ejemplo de Json
   {
    "id": 6,
    "fechaCreacion": "1969-03-01",
    "fechaFin": "1970-01-01",
    "fechas": [
	"1969-03-01",
	"1969-05-01",
	"1969-09-01",
	"1970-01-01"
     ]
    }

# Salida
La respuesta que el servicio entrega es la siguiente.
Json con la siguente estructura e información:

*id*: identificador
*fechaCreacion*: Fecha de inicio de la secuencia
*fechaFin*: Fecha de fin de la secuencia
*fechasFaltantes*: Lista de fechas que están en el rango de la fecha que se encuentra en “fechaCreacion” hasta la fecha “fechaFin” y que no están en la lista "fechas" que se recibió como parámetro de entrada.
Ejemplo de Json

   {
    "id": 6,
    "fechaCreacion": "1969-03-01",
    "fechaFin": "1970-01-01",
    "fechasFaltantes": [
	"1969-04-01",
	"1969-06-01",
	"1969-07-01",
	"1969-08-01",
	"1969-10-01",
	"1969-11-01",
	"1969-12-01"
    ]
   }

## Nota:
El formato de las fechas es yyyy-MM-dd
El servicio entrega 1 periodos, el periodo contiene un id, una fecha inicial, una fecha final y una lista de fechas faltantes.

## Construido con 

Java 8
Spring-Boot v1.5.9.RELEASE
Apache Maven 3.6.0
Apache-tomcat-8.5.42
IDE: IntelliJ IDEA

## Compilar y ejecutar el proyecto 

1. Clonar el proyecto
2. Se requiere Java y Maven instalado, para copilar el proyecto, se debe ingresar al directorio *ApiPeriodos* ejecutar el siguiente comando maven: mvn package
3. Luego de compilar el proyecto ingresar al directorio *target* ejecutar el siguiente comando java: java -jar api-periodos-1.0.0.jar
4. Al configurar el servidor, debe estar disponible el puerto *8080* en el PC donde se ejecute esta API
5. En caso que no se reconozca alguna librería del proyecto, ejecutar los siguientes comandos maven: mvn compile y luego mvn install 


## Pruebas

Para realizar las pruebas del servicio se debe realizar la petición por postman de forma local con la siguiente ruta:

http://localhost:8080/periodos/obtenerPeriodosPerdidos

La estructura del JSON:

```
{
"id": 6,
"fechaCreacion": "1969-01-01",
"fechaFin": "1970-01-01",
"fechas": [
	"1969-03-01",
	"1969-05-01",
	"1969-09-01",
	"1970-01-01"
	]
}
```

El resultado de la petición es JSON con las fechas faltantes de rango de fechas recibido sin tomar en cuenta las fechas que vienen en el objeto lista "fechas" del request de entrada.

```
{
    "id": 6,
    "fechaCreacion": "1969-01-01",
    "fechaFin": "1970-01-01",
    "fechasFaltantes": [
        "1969-01-01",
        "1969-02-01",
        "1969-04-01",
        "1969-06-01",
        "1969-07-01",
        "1969-08-01",
        "1969-10-01",
        "1969-11-01",
        "1969-12-01"
    ]
}

```

## Autor

Keylis Valles 

