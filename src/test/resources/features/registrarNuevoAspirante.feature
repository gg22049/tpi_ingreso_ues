Feature: Registro de aspirantes

Scenario: Registrar un aspirante con datos validos
Given que no existe un aspirante con correo "aspirante@test.com"
When registro un aspirante con:
    | nombres           | Juan                          |
    | apellidos         | Perez                         |
    | correo            | aspirante@test.com                 |
    | fechaNacimiento   | 1900-12-27T00:00:00Z          |
Then el sistema responde con codigo 201
And el aspirante queda almacenado con correo "aspirante@test.com"