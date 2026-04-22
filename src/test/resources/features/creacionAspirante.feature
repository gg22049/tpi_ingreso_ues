Feature: crear un aspirante

Scenario:  crear perfil de aspirante y asociacion a una opcion de carrera
Given Se tiene un servidor corriendo con la aplicacion desplegada
When puedo crear un aspirante
And puedo asociarlo a una opcion de carrera, ejemplo I300515
Then puedo consultar el perfil del aspirante recien creado 
And verificar la opcion de carrera a la que se asocio
