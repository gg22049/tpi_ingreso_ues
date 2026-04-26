Feature: Construir clave de prueba con area, pregunta y distractores

Scenario: Construir clave de prueba con area, pregunta y distractores
  Given se tiene un tipo de prueba "Nuevo Ingreso En linea" "true":
  And crear una prueba:
  |tipoPrueba|Nuevo Ingreso|
  |indicaciones|Responda con lapiz H2B|
  |nombre|Prueba de ingreso 2026|
  |puntajeMaximo|100|
  |notaprobacion|60|
  |duracion|150|
  And existe una clave llamada "Clave A" asociada a la prueba
  And existe un area de conocimiento llamada "sociales"
  And existe la pregunta "¿En que año se firmaron los acuerdos de paz? "asociada al area sociales
  And existe el distractor "2001" como opcion incorrecta
  And existe el distractor "1992" como opcion correcta
  When asocio el area "sociales" a la clave "Clave A" con cantidad 1 y porcentaje 100
  And asocio la pregunta a la clave y al area "sociales" con porcentaje 100
  And asocio los distractores de la pregunta a la clave
  Then puedo consultar la clave de la prueba
  And verifico que la clave contiene el area "sociales"
  And verifico que la clave contiene la pregunta asociada
  And verifico que la clave contiene los distractores de la pregunta