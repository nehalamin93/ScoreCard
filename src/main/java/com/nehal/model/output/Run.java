package com.nehal.model.output;

import com.nehal.constants.OutputType;

public class Run extends DeliveryOutput {

    public Run(Integer score) {
        super(score, OutputType.RUN);
    }
}
