Feature: Crear perfil de aspirante, asociarle una identificacion y asociarlo a varias opciones de carrera

Scenario: Crear perfil de aspirante, asociarle una identificacion y asociarlo a varias opciones de carrera
  Given existe una identificacion tipo "DUI"
  And existe un aspirante:
    | nombres           | Juan                          |
    | apellidos         | Perez                         |
    | correo            | aspirante.cucmber.e2e@test.com|
    | fechaNacimiento   | 1900-12-27T00:00:00Z          |
  When asocio al aspirante la identificacion con valor "123456789"
  And asocio al aspirante la primera opcion de carrera "I30515"
  And asocio al aspirante la segunda opcion de carrera "I30516"
  Then puedo consultar el perfil del aspirante recien creado con correo "aspirante.cucmber.e2e@test.com"
  And verifico que la identificacion "123456789" pertenece al aspirante creado
  And verifico que el aspirante tiene asociada la opcion uno "I30515"
  And verifico que el aspirante tiene asociada la opcion dos "I30516"
