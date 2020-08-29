# 2) socioregister-mock
A Springboot REST application to register and add Socios (second step with socioregister-CRUDS for a mock DB)

1) General Info About the Socio-Micro-Service-Demo

2) Specific Info Concerning Each Single Application



General Info =====================================

The Socio Micro Services Project will consist of about 10 small (backend) Springboot applications, deployed in a Docker Container/ Linux Oracle Virtual Box. SocioRegister is the principal part of a series of four applications called: starter, mock, jpa, socioregister. Together they show a stepwise buildup to a Springboot REST application, which contains use-cases for registering and adding Socios (similar to Facebook). This line of applications goes from an almost empty Springboot shell (starter: one controller method only) to a small but full-fledged REST application: SocioRegister which will be used as a component of our micro-services.

Next you`ll find four other serving applications. The simple SocioWeather, provides a weather-report by city by consulting an external REST-service called Open Weather. SocioBank, permits money transaction between Socios alse consulting an external service for exchange rates. The SocioSecurity, a Cookie/ Token based SpringSecurity (OAUTH2), still has to be written. Finally the SocioDbBatch application is interesting because it will update, on a daily bases, the databases of SocioRegister (socio_db) and SocioBank (soicio_bank_db). The DBs run on MySQL or Postgres.

From SocioRegister-jpa one finds backend-Validation (javax) and REST-Exception Handeling of Spring (RestControllerAdvice).

Testing, in general, will have an important focus and since we are dealing with Spring(boot) there will systematically testing based on five mayor strategies:

	-@ExtendWith(MockitoExtension.class)

	-@ExtendWith(SpringExtension.class) standalone setup (two ways)

	-@ExtendWith(SpringExtension.class) server tests (@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)

	-@DataJpaTest wich is database testing on H2

	-Spring Batch testing

Testing is still "work in progress"



Specific Info SocioRegister-mock =====================================

After starter the mock application would be the next step to revise. To get familiar with any Springboot-app it is a good habit first to look into the Maven pom (Gradle file or whatever dependency-framework you use). Besides the principle spring-boot-starter-web dependency, key to a WEB/REST-application, you'll find some new ones. Lombok, automatic getters and setters at POJOs, and Modelmapper, mapping DTO´s into Entities and visa versa.

Note the layers: Controller(s) (SocioController), Service(s) (SocioService, the interface and its implementation part SocioServiceImpl). And note also the related DTOs (SocioDTO) and Entities here called Model (SocioModel). 

Important: 1) the DTO(s) enter and exit the controller(s) 2) they are converted into Model(s) /visa versa at the Service(s)! At SocioRegister-mock there is still no real DB but a mock which is sited at SocioRepository (here will go the Model classes of the service to enter into the DB see SocioRegister-jpa).

I asume that you already looked into all those Spring annotations concerning the Controller class such as, 

	-@RestController, the standard REST controller annotation;
	
	-@RequestMapping("/socio"), the general mapping of the complete controller class:
	
	-@PostMapping(produces = "application/json", consumes = "application/json"), the mapping for each specific method, note the @Get, @Put etc. versions as well;
	
	-@RequestBody, entering a json object toether with consumes = "application/json";
	
	-path = "/{id}" and @PathVariable Long id, for obtaining ids etc;
	
	-(@RequestParam(value = "rows", required = false, defaultValue = "3") int rows), for obtaining the classical param values;
	
You should also get familiar with the typical REST-urls: (get:) /socio (get all), (get:) /socio/1 (get one with id=1), post: /socio (+ socio-json save socio), put: /socio/2 (+ socio-json update socio), delete: /socio/2 

The ResponseEntity class is another key element of a REST-application, you'll find it at all controller classes of the Demo. The ResponseEntity takes fully care of the controller response/ return. There are varous constructors available, one e.g. may set the (json) return object and the Http status: 

    return new ResponseEntity<>(socio, HttpStatus.OK);
    
Or even, if you want so, add headers to controller response. At the next demo example (jpa) you'll find out that the ResponseEntity also forms part of the global Spring exception handeling!

The annotations (@NotEmpty, @NotNull) at the SocioDTO are of the lib javax.validation. They will prevent fields to by empty or null (or whatever you want to validate) coming from the Frontpage. At the next step (jpa) I will show how to config messages concerning these validations.

At the test-directory you'll find a first step of controller-testing in Spring (MockitoExtension and see: MockMvcBuilders.standaloneSetup(socioController).build()). 

The Use-cases can be observed at the SocioController class. At the browser try the following urls (after running the main of SocioRegisterApplication):

	-http://localhost:8081/socio

	-http://localhost:8081/socio/2

	-http://localhost:8081/socio/username/js

And by using Postman:

	-post http://localhost:8081/socio  
		{
			"id": 4,
			"username": "pb",
			"firstName": "Pierre",
			"lastName": "Boulez",
			"email": "boulez@gmail.com",
			"active": true 
		} 
  
	-put http://localhost:8081/socio/4
		{
			"id": 4,
			"username": "pbxxx",
			"firstName": "Pierre",
			"lastName": "Boulez",
			"email": "boulez@gmail.com",
			"active": true 
		} 
   
	-delete http://localhost:8081/socio/4

Remember there is no DB present here, so you should have the (correct) id present at every single json object!
