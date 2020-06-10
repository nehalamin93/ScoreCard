package com.nehal.model.output;

import com.nehal.constants.OutputType;

public abstract class DeliveryOutput {
    private Integer score;
    private OutputType outputType;

    public DeliveryOutput(Integer score, OutputType outputType) {
        this.score = score;
        this.outputType = outputType;
    }

    public Integer getScore() {
        return score;
    }

    public OutputType getOutputType() {
        return outputType;
    }
}
