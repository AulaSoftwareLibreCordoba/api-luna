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
