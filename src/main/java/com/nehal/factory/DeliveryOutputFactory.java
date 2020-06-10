package com.nehal.factory;

import com.nehal.constants.OutputType;
import com.nehal.helper.Helper;
import com.nehal.model.output.*;

import static com.nehal.constants.Constants.DEFAULT_NO_BALL_RUN;
import static com.nehal.constants.Constants.DEFAULT_WIDE_RUN;

public class DeliveryOutputFactory {
    public static DeliveryOutput getOutput(String output) {

        Integer run = Helper.parseIntegerWithSuffixString(output);
        String outputStr = Helper.parseStringWithPrefixInt(output);
        if(outputStr.contentEquals("")) {
            outputStr = "R";
        }

        OutputType outputType = OutputType.getOutputType(outputStr);
        switch (outputType) {
            case RUN:
                return new Run(run);
            case WIDE:
                return new Wide(DEFAULT_WIDE_RUN + run);
            case WICKET:
                return new Wicket(run);
            case NO_BALL:
                return new NoBall(DEFAULT_NO_BALL_RUN + run);
            default:
                return null;
        }
    }
}
