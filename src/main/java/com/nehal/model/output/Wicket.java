package com.nehal.model.output;

import com.nehal.constants.OutputType;

public class Wicket extends DeliveryOutput {

    public Wicket(Integer score) {
        super(score, OutputType.WICKET);
    }
}
