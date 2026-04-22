Feature: creacion de jornada para proximas pruebas

Scenario: Crear una jornada 
Given Se tiene un servidor corriendo con la aplicacion desplegada
When puedo crear una Jornada nueva para pruebas(examen)
And puedo asociar distintas aulas para esa jornada
Then puedo consultar la fecha en que se llevara a cabo la jornada 
And consultar que aulas estan asignadas a dicha jornada 
 
