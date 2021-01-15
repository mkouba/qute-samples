package org.acme.qute.samples;

import java.time.LocalDateTime;

import javax.enterprise.inject.Produces;
import javax.inject.Named;
import javax.inject.Singleton;

import org.acme.qute.Sample;

@Named
@Singleton
public class InjectSample implements Sample {

    @Override
    public String getTitle() {
        return "Inject CDI Beans";
    }

    @Override
    public Difficulty getDifficulty() {
        return Difficulty.ADVANCED;
    }

    @Named("now")
    @Produces
    LocalDateTime now() {
        return LocalDateTime.now();
    }

}
