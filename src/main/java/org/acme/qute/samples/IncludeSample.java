package org.acme.qute.samples;

import javax.inject.Singleton;

import org.acme.qute.Sample;

@Singleton
public class IncludeSample implements Sample {

    @Override
    public String getTitle() {
        return "Simple Include";
    }
    
    public Difficulty getDifficulty() {
        return Difficulty.ADVANCED;
    }

}
