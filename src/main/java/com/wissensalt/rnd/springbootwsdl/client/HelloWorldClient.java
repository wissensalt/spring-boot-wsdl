package com.wissensalt.rnd.springbootwsdl.client;

import com.wissensalt.types.helloworld.Greeting;
import com.wissensalt.types.helloworld.ObjectFactory;
import com.wissensalt.types.helloworld.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ws.client.core.WebServiceTemplate;

/**
 * Created on 3/26/19.
 *
 * @author <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
@Component
public class HelloWorldClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(HelloWorldClient.class);

    @Autowired
    private WebServiceTemplate webServiceTemplate;

    public String sayHello(String p_FirstName, String p_LastName) {
        ObjectFactory objectFactory = new ObjectFactory();
        Person person = objectFactory.createPerson();

        person.setFirstName(p_FirstName);
        person.setLastName(p_LastName);

        LOGGER.info("Client Sending Person : First Name {}, Last Name {}", person.getFirstName(), person.getLastName());

        Greeting greeting = (Greeting) webServiceTemplate.marshalSendAndReceive(person);

        LOGGER.info("Client Received Greeting {}", greeting.getGreeting());

        return greeting.getGreeting();
    }
}
