package org.acme.qute.usertags;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.acme.qute.CheckedTemplates;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import io.quarkus.qute.api.CheckedTemplate;

@Path("items")
public class ItemResource {

    @ConfigProperty(name = "items.discount", defaultValue = "10")
    int discount;

    @ConfigProperty(name = "items.price-limit", defaultValue = "15")
    int discountPriceLimit;

    @GET
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance get() {
        List<Item> data = new ArrayList<>();
        data.add(new Item(new BigDecimal(10), "Apple"));
        data.add(new Item(new BigDecimal(16), "Pear"));
        data.add(new Item(new BigDecimal(30), "Orange"));
        return CheckedTemplates.items(data).data("discount", (discount < 0 || discount > 100) ? 10 : discount)
                .data("discountLimit", discountPriceLimit);
    }

}