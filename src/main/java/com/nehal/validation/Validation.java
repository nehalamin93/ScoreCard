package com.nehal.validation;

import com.nehal.factory.DeliveryOutputFactory;

public class Validation {

    public static boolean validIntegerString(String input)
    {
        try {
            Integer.parseInt(input);
        } catch(NumberFormatException ex) {
            return false;
        }
        return true;
    }

    public static boolean validateDeliveryOutput(String deliveryOutput) {
        try {
            return DeliveryOutputFactory.getOutput(deliveryOutput) != null;
        } catch (Exception ex) {
            return false;
        }
    }
}
