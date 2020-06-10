package com.nehal.model.output;

import com.nehal.constants.OutputType;

public class NoBall extends DeliveryOutput {

    public NoBall(Integer score) {
        super(score, OutputType.NO_BALL);
    }
}
