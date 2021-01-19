package org.acme.qute.samples;

import java.util.Collections;
import java.util.List;

import javax.inject.Singleton;

import org.acme.qute.Sample;

@Singleton
public class IncludeSample implements Sample {

    @Override
    public String getTitle() {
        return "Include Template";
    }
    
    @Override
    public List<String> getAdditionalSnippetNames() {
        return Collections.singletonList("hello.html");
    }

    
    public Difficulty getDifficulty() {
        return Difficulty.ADVANCED;
    }

    @Override
    public int getPriority() {
        // this sample should go before template inheritance
        return 0;
    }
    

}
