Assumptions:

1) Jboss(WildFly) Application Server (Version 10.1.0) is available.
2) Mysql database is available.

Steps to followed before building and deploying the application
1) Start MySQL DB server.
2) Create a datasource with name java:jboss/datasources/unicomysqlds in wildfly
3) Run the dbScript.sql available.
4) Create the JMS queue "jms/queue/AssignUnicoJMS".

Security

1. The services of REST and SOAP are secured using security at application level.
2. Non-invasive http interceptors provide security services.
3. Users are required to send user name and encrypted password as http headers(http headers should be "UserName" = gcdappuser and "Password" = enrypted(password)).
4. Using public/private key mechanism passwords are encrypted/decrypted
5. In github public key has been uploaded
6. Application security can be further enhanced using container managed security and enabling SSL on the server.

Technologies used

Programming Language - Java(Version -7)
Server - Wildfly(JBoss) Application Server (Version 10.1.0)
Databse - MySQL Server : 5.6.36 MySQL Community Server (GPL)
Unit Testing - JUnit and Mockito
 Build tool - Maven
JAX-WS(Soap) - Apache CXF 
JAX-RS(Rest) - RestEasy 
Spring - Dependency Injection for Unit Testing
Persistence : JPA (Hibernate)
JMS - ActiveMQ (Part of wildfly AS)

MultiUser Environment:

Applications designed to handle multiple users. 
Each rest call is handled by a new object, while methods to handle Soap calls has been synchronized.

UserName/Password (to be set in http headers)
gcdappuser/password
password must be encrypted and Base64 encoded before it is sent to the server.

Testing:
Application was tested using custom build java rest client/Postman and Soap UI.

URLS:
Rest Push Service URL:
http://localhost:8080/gcd-web/rest/gcd/push/100/200

SOAP Service WSDL
http://localhost:8080/gcd-web/GcdService?wsdl

SOAP Service:
http://localhost:8080/gcd-web/GcdService
