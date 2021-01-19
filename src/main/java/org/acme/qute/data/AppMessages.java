package org.acme.qute.data;

import io.quarkus.qute.i18n.Message;
import io.quarkus.qute.i18n.MessageBundle;

@MessageBundle
public interface AppMessages {

    @Message("Hello {name ?: 'world'}!")
    String helloWorld(String name);

}
