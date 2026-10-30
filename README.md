# VeterinariaWeb - LAB7 de práctica (estilo del PPT)

Proyecto de estudio que sigue el enunciado de LAB7 y las clases JDBC/MVC del ZIP IWEB_PARCIAL. El PDF indica que no debe utilizarse IA durante la evaluación individual; usar este proyecto únicamente para practicar fuera de esa evaluación.

## Configuración
1. La base de datos `Veterinaria` y sus tablas ya deben existir en MySQL.
2. Sustituir `TU_PASSWORD` en `src/main/java/com/veterinaria/dao/DaoBase.java` por tu contraseña local. NO publiques ese archivo con la contraseña real en GitHub: vuelve a poner el marcador antes de subirlo.
3. Abrir el proyecto en IntelliJ IDEA, recargar Maven y desplegar el WAR en Tomcat 10.1.
4. Abrir la ruta `/Veterinaria/MascotaServlet` (ajustar el context path según la configuración del artefacto).

## Qué incluye
Beans: Mascota, Especie, Veterinario y Dueno.
DAO: DaoBase y MascotaDao.
Servlet: MascotaServlet.
Vistas: listaMascotas.jsp y nuevaMascota.jsp.

## Método abstracto sin parámetros
El LAB7 escribe `crear()` y `borrar()` y no especifica parámetros. Para respetar esas firmas sin usar genericos `<T>` ni `Object`, MascotaDao guarda temporalmente una mascota en un atributo `private Mascota mascota` y un ID en `private int idMascota`. El Servlet llama `dao.setMascota(mascota); dao.crear();` o `dao.setIdMascota(id); dao.borrar();`. Es una **adaptación didáctica nuestra**, no un ejemplo exacto del PPT. En proyectos mayores suelen elegirse firmas con parámetros.

No se usó `BigDecimal`, `System.getenv()`, `Object` ni `<T>` en el código Java. El `peso` del Bean es double, como se acordó.

## Verificación
Se comprueba la compilación Java de los Beans y DAO con `javac`. No se ha probado aún la conexión real a tu MySQL ni el despliegue Tomcat en tu computadora.


## DAO incluidos
- DaoBase.java (conexion JDBC y metodos abstractos crear/borrar sin parametros)
- MascotaDao.java (listar, filtrar, crear y borrar mascotas)
- EspecieDao.java (listar opciones de especies)
- VeterinarioDao.java (listar opciones de veterinarios)
- DuenoDao.java (listar opciones de duenos)

Los DAO de catalogos cumplen los metodos abstractos heredados, pero lanzan
UnsupportedOperationException si se intenta crear/borrar; el examen solo
solicita crear/borrar mascotas. El Servlet usa cada DAO por separado.
