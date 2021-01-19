package org.acme.qute.data;

import io.quarkus.qute.TemplateExtension;

@TemplateExtension(namespace = "str")
public class StringExtensions {

    static String reverse(String val) {
        return new StringBuilder(val).reverse().toString();
    }
}