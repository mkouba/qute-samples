package org.acme.qute.samples;

import javax.inject.Singleton;

import org.acme.qute.Sample;

@Singleton
public class LetSample implements Sample {

    @Override
    public String getTitle() {
        return "Local Variables";
    }

    @Override
    public int getPriority() {
        return -1;
    }

}
