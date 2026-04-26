Feature: Crear pregunta con area de conocimiento y distractoress

Scenario: Crear pregunta con area de conocimiento y distractores
  Given se tiene un servidor corriendo con la aplicación desplegada
  When creo un area de conocimiento llamada "matematicas"
  And creo una pregunta con el texto "Cuanto es 2 + 2?"
  And asocio la pregunta al area de conocimiento "matematicas"
  And creo el distractor 3 como opcion incorrecta "true"
  And creo el distractor 4 como opcion correcta "true"
  And asocio los distractores a la pregunta
  Then puedo consultar la pregunta recien creada
  And verifico que la pregunta pertenece al area "matematicas"
  And verifico que la pregunta tiene el distractor 3
  And verifico que la pregunta tiene el distractor 4
  And verifico que el distractor 4 esta marcado como correcto
