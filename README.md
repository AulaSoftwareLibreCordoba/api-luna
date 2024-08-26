Actualmente para probar a usar la base de datos se pueden hacer de dos maneras:

1. En caso de no utilizar Docker:
   Clonar el repo de github
   Cambiar el application.properties que está en api-luna/src/main/resources/application.properties por el siguiente contenido:
          spring.application.name=api-luna

          #base de datos
          spring.datasource.url=jdbc:mysql://localhost:3306/DB_api_luna
          spring.datasource.username=root
          spring.datasource.password=
          spring.jpa.hibernate.ddl-auto=update
          
          
          #variables de programa
          jwt.access-token-expiration: 86400000
          jwt.refresh-token-expiration: 604800000
          jwt.secret =4321secretoequiposoftwarelibreperonoEsSuficientementeLargo1234
          
          #swagger
          springdoc.swagger-ui.path=/swagger-ui.html
          springdoc.api-docs.path=/v3/api-docs

   Tener creado la base de datos en mysql llamada DB_api_luna (no es necesario crear las tablas)
   Utilizar el comando mvn spring-boot:run
   Si no hay ningún fallo se mantendrá a la espera, si quieres asegurarte de que está corriendo pon esto en el buscador y te debe salir algo parecido a esto: []
   http://localhost:8080/denuncias

2. En caso de utilizar Docker:
  Clonar el repo de github
  Navegar hasta la carpeta donde esté el projecto
  (Recordar tener iniciado Docker)
  Poner por consola docker-compose up --build
  Para saber si está corriendo lo puedes saber tanto con el comando Docker ps o porbando la url http://localhost:8080/denuncias y te debe salir algo como [] 
