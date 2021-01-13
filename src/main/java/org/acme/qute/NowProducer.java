package org.acme.qute;

import java.time.LocalDateTime;

import javax.enterprise.inject.Produces;
import javax.inject.Named;
import javax.inject.Singleton;

@Singleton
public class NowProducer {

    // Named beans are accessible directly from the templates via the "inject" namespace
    @Named("now")
    @Produces
    LocalDateTime now() {
        return LocalDateTime.now();
    }
    
}
