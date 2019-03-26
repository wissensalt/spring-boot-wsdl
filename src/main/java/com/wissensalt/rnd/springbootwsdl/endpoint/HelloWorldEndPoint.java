package com.wissensalt.rnd.springbootwsdl.endpoint;

import com.wissensalt.types.helloworld.Greeting;
import com.wissensalt.types.helloworld.ObjectFactory;
import com.wissensalt.types.helloworld.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

/**
 * Created on 3/26/19.
 *
 * @author <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
@Endpoint
public class HelloWorldEndPoint {

    private static final Logger LOGGER = LoggerFactory.getLogger(HelloWorldEndPoint.class);

    @ResponsePayload
    @PayloadRoot(
            namespace = "http://wissensalt.com/types/helloworld",
            localPart = "person"
    )
    public Greeting sayHello(@RequestPayload Person p_Person) {
        LOGGER.info("Request received Person : First Name {}, Last Name {}", p_Person.getFirstName(), p_Person.getLastName());
        String greeting = "Hello "+p_Person.getFirstName()+" "+p_Person.getLastName() + " !";

        ObjectFactory objectFactory = new ObjectFactory();
        Greeting response = objectFactory.createGreeting();
        response.setGreeting(greeting);

        LOGGER.info("Endpoint Sending Greeting {}", response.getGreeting());
        return response;
    }
}
