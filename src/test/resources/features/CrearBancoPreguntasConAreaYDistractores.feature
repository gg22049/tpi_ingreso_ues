Feature: Crear pregunta y asociar area de conocimiento y distractores

Scenario: Crear pregunta con area de conocimiento y distractores
  Given existe un area de conocimiento llamada "matematicas"
  And existe un distractor uno activo con valor "4"
  And existe un distractor dos activo con valor "6"
  When creo una pregunta con el texto "Cuanto es 2 + 2?"
  And asocio la pregunta al area de conocimiento "matematicas"
  And asocio el distractor uno a la pregunta marcando correcto como "true"
  And asocio el distractor dos a la pregunta marcando correcto como "false"
  Then puedo consultar la pregunta recien creada con el texto "Cuanto es 2 + 2?"
  And verifico que la pregunta pertenece al area
  And verifico que la pregunta se asocio al distractor uno
  And verifico que la pregunta se asocio al distractor dos
