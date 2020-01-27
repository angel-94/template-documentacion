/**
 * Hero is the main entity we'll be using to . . .
 * <p>
 * Please see the {@link com.baeldung.javadoc.Person} class for true identity
 * * <h1>Hello, World!</h1>
 * * The HelloWorld program implements an application that
 * * simply displays "Hello World!" to the standard output.
 * * <p>
 * * Giving proper comments in your program makes it more
 * * user friendly and it is assumed as a high quality code.
 * *
 * <p>
 * * @author Manuel Angel Ruiz Sumano - [Meltsan-Team]
 * * @version 1.0.0
 * * QueueServiceImpl:
 * * description:
 */
package com.mts.demo;

import com.mts.queue.config.QueueConfig;
import com.mts.queue.exception.QueueException;
import com.mts.queue.util.QueueUtils;
import com.rabbitmq.client.Channel;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

import static com.mts.queue.config.QueueConstant.*;

@Service
public class QueueProducerServiceImpl implements QueueProducerService {

    private static final Logger log = Logger.getLogger(QueueProducerServiceImpl.class);

    @Autowired
    private QueueConfig queueConfig;


    // tag::codigo1[]
    /**
     * Comnetarios sobre el metodo
     * @param message el mensaje
     * */
    @Override
    public void queueExample(String message) throws QueueException {
        try {
            Channel channel = queueConfig.createChannel();
            channel.exchangeDeclare(AML_EXCHANGE_NAME, AML_EXCHANGE_TYPE); // <1>
            for (int i = 0; i < 10; i++) {
                String routingKey = i % 2 == 0 ? AML_SEND_MAIL : AML_CASE_ASSIGNMENT;
                channel.basicPublish(
                        AML_EXCHANGE_NAME,
                        routingKey,
                        null,
                        message.getBytes(StandardCharsets.UTF_8)); // <2> 
                log.info("[x] Send message -> " + message);
            }
            channel.close();
        } catch (IOException | TimeoutException e) {
            log.error("Error en el envio de mensajes -> " + e.getMessage());
        }
    }
    // end::codigo1[]



    /**
     * Comentarios
     * @param message el mensaje
     * @param routingKey el routingKey
     * */
    @Override
    // tag::uno[]
    public void putMessageInQueue(Object message, String routingKey) {
        String json = QueueUtils.convertObject2Json(message);
        try {
            Channel channel = queueConfig.createChannel();
            channel.exchangeDeclare(AML_EXCHANGE_NAME, AML_EXCHANGE_TYPE);
            channel.basicPublish(
                    AML_EXCHANGE_NAME,
                    routingKey,
                    null,
                    json.getBytes(StandardCharsets.UTF_8));
            log.info("[x] Send message -> " + message);
            channel.close();
        } catch (IOException | TimeoutException | QueueException e) {
            e.printStackTrace();
        }
    }
    // end::uno[]

    // tag::hello[]
    /**
     * Comentarios
     * */
    public void holaMundo() { //<1>
        System.out.println("Hola mundo"); //<2>
    }
    // end::hello[]


    /**
     * Comentarios
     * */
    public void holaMundo() { //<1>
        System.out.println("Hola mundo"); //<2>
    }


    /**
     * Comentarios
     * */
    public void holaMundo() { //<1>
        System.out.println("Hola mundo"); //<2>
    }

    /**
     * Comentarios
     * */
    public void holaMundo() { //<1>
        System.out.println("Hola mundo"); //<2>
    }


}
