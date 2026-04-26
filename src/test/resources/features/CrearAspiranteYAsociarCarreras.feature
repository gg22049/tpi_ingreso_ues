Feature:Crear perfil de aspirante, asociarle una identificacion y asociarlo a varias opciones de carrera

Scenario: Crear perfil de aspirante, asociarle una identificacion y asociarlo a varias opciones de carrera
  Given la aplicación se encuentra desplegada y el servidor está activo
  When creo un aspirante con nombres, apellidos, fecha de nacimiento y correo:
    | nombres           | Juan                          |
    | apellidos         | Perez                         |
    | correo            | aspirante@test.com                 |
    | fechaNacimiento   | 1900-12-27T00:00:00Z          |
  And registro una identificacion para el aspirante con tipo "DUI" y valor "123456789"
  And asocio al aspirante la opcion de carrera "I30515"
  And asocio al aspirante la opcion de carrera "I30516"
  Then puedo consultar el perfil del aspirante recien creado
  And verifico que el aspirante fue creado correctamente
  And verifico que la identificacion "123456789" pertenece al aspirante creado
  And verifico que el aspirante tiene asociada la opcion "I30515"
  And verifico que el aspirante tiene asociada la opcion "I30516"
