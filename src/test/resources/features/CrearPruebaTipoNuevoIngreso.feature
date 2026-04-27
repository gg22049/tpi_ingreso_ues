Feature:  Crear prueba de ingreso con tipo de prueba y clave
Scenario: Crear prueba de ingreso con tipo de prueba y clave
  Given existe un tipo de prueba llamado "Nuevo ingreso" 
  When creo una prueba:
  |tipoPrueba|Nuevo Ingreso|
  |indicaciones|Responda con lapiz H2B|
  |nombre|Prueba de ingreso 2026|
  |puntajeMaximo|100|
  |notaAprobacion|60|
  |duracion|150|
  And creo una clave para la prueba llamada "Clave A"
  Then puedo consultar la prueba recien creada "Prueba de ingreso 2026"
  And verifico que la prueba tiene asociado el tipo "Nuevo ingreso"
  And verifico que la prueba tiene asociada la clave "Clave A"
