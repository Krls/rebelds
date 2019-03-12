# rebelds
Concatel Rebelds Project

Requerimientos:

3. Back End Exam 3

As a imperial programmer your receive the order of implement a new web service that allows to register any rebeld 
identification.

The empire has conquered all universe, but it still remains some group of hidden rebelds. Dark forces, soldiers for the empire,
distributed over all know galaxies and solar systems forces empire's citizens to be identified. They need you to develop a 
distributed service that would be able to be called from any location over the universe.

As interoperability is a must, you decide to use an old fashioned and very extended technology called wcf web services. 
The web service should be RestFull to be called from anywhere an http/tcp network is. The web service has to expose a method 
that accept a list of strings with the name of the rebeld and name of the planet, and response true is register goes fine. 
The regiter has to be done to file with a datetime with the string "rebeld (name) on (planet) at (datetime)".

Separate responsabilities in separate layers: distributed services, application and domain. Implement error logging and manage 
exceptions in every layer. Implement unit test for any layer, too. Take care of proper naming convections and SOLID principles.
You can use any Dependency Injection, Unit Testing, Mocking frameworks or any other that you consider necessary.

Información de despliegue:

Proyecto desarrollado con el framework Spring Boot y el gestor de dependencias MAVEN.
- Una vez desplegado el proyecto en local, la URL de acceso es:
    http://localhost:8082/identification-service/registerRebeld
    
- Un ejemplo de JSON para enviar el listado de rebeldes.
[
	{
        "name": "Carles",
        "planet": "Tierra"
    },
	{	
    	"name": "Maria",
		"planet": "Marte"
    }
]

