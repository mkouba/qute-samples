package org.acme.qute;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.acme.qute.data.Item;
import org.acme.qute.samples.ExtensionMethodsSample;
import org.acme.qute.samples.UserTagSample;

import io.quarkus.qute.TemplateExtension;

@TemplateExtension
public class TemplateExtensions {

    /**
     * This extension method is used to add a virtual method "reversed" to any List instance.
     * 
     * @param list
     * @return the reversed list
     * @see ExtensionMethodsSample
     */
    static List<Object> reversed(List<Object> list) {
        List<Object> reversed = new ArrayList<>(list);
        Collections.reverse(reversed);
        return reversed;
    }

    /**
     * 
     * @param item
     * @param discount
     * @return the discounet price
     * @see UserTagSample
     */
    static BigDecimal discountedPrice(Item item, int discount) {
        return item.getPrice().subtract(TemplateExtensions.getDiscountValue(item, discount)).setScale(0, RoundingMode.HALF_UP);
    }

    /**
     * 
     * @param item
     * @param discount
     * @return the discount value
     * @see UserTagSample
     */
    static BigDecimal getDiscountValue(Item item, int discount) {
        if (discount < 0 || discount > 100) {
            throw new IllegalArgumentException("Invalid discount: " + discount);
        }
        BigDecimal percent = item.getPrice().divide(new BigDecimal(100));
        return new BigDecimal(discount).multiply(percent);
    }

}
