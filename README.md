# Cats and Dogs

*Backend* and *Frontend* are sample applications demonstrating the use of Service Registry and Circuit Breaker from Spring Cloud Services with Container-to-Container (C2C) Networking from Cloud Foundry.

This app is a "blend" of the CF Netowrking Release
[cats-and-dogs](https://github.com/cloudfoundry/cf-networking-release/tree/develop/src/example-apps/cats-and-dogs)
example app, and the Spring Cloud Services
[who is home?](https://github.com/spring-cloud-services-samples/whoishome)
example app.

## Building and Deploying

Using Gradle, run `./gradlew build` (OS X, Linux) or `gradlew.bat build` (Windows).

```
$ ./gradlew build
```

Create the service registry and circuit dashboard

```
$ ./scripts/create_services.sh
```

Deploy the frontend and backend apps

```
$ ./scripts/deploy_apps.sh
```

You may also need to set the `TRUST_CERTS` environment variable to the API endpoint of your Elastic Runtime instance (as in `api.example.com`), then restage the applications so that the changes will take effect. Setting `TRUST_CERTS` causes Spring Cloud Services to add the the SSL certificate at the specfied API endpoint to the JVM's truststore, 
so that the client application can communicate with a Service Registry service instance
(see the [Service Registry Documentation](http://docs.pivotal.io/spring-cloud-services/service-registry/writing-client-applications.html#self-signed-ssl-certificate).

```
$ cf set-env backend TRUST_CERTS api.wise.com
Setting env variable 'TRUST_CERTS' to 'api.wise.com' for app backend in org myorg / space development as user...
OK
TIP: Use 'cf restage' to ensure your env variable changes take effect
$ cf restage backend
```

```
$ cf set-env frontend TRUST_CERTS api.wise.com
Setting env variable 'TRUST_CERTS' to 'api.wise.com' for app frontend in org myorg / space development as user...
OK
TIP: Use 'cf restage' to ensure your env variable changes take effect
$ cf restage frontend
```

**NOTE**

> By default, the Spring Cloud Services Starters for Service Registry causes all application endpoints to be secured by HTTP Basic authentication. For more information or if you wish to disable this, [see the documentation](http://docs.pivotal.io/spring-cloud-services/service-registry/writing-client-applications.html#disable-http-basic-auth). HTTP Basic authentication is disabled in these sample applications.

## Trying It Out

Visit `[ROUTE]/`, where `[ROUTE]` is the route bound to the Frontend application.
The Frontend application will use the Service Registry to look up the Backend application
and get a message back, which contains a URL to a cat picture.

When the Frontend and Backend applications are initially deployed,
the Frontend application will not be able to contact the Backend application.
This is because a policy has not been created between the applications
using the [CF CLI network policy commands](https://github.com/cloudfoundry-incubator/cf-networking-release).

To create a policy, you can run the following command
to allow communication between Frontend and Backend applications:

`cf add-network-policy frontend --destination-app backend --protocol tcp --port 8080`

To remove the network policy, you can run this command:

`cf remove-network-policy frontend --destination-app backend --protocol tcp --port 8080`

And to see existing policies:

`cf network-policies`

For more information about the Service Registry and its use in a client application,
see the [Service Registry documentation](http://docs.pivotal.io/spring-cloud-services/service-registry/writing-client-applications.html).
