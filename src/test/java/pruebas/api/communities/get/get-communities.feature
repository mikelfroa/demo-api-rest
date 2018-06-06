# language: es
@rest @controller @v1 @aplicaciones @_idAplicacion @ayudas @get @get-ayudas
Característica: Obtener todos las ayudas
  Como usuario
  Quiero conocer todas las ayudas disponibles
  Para conocer las ayudas que hay

  Escenario: Obtener todas las ayudas de la aplicación 14
    * url urlApi
    Dado path '/communities'
    Cuando method get
    Entonces status 200