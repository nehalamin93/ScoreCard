package com.nehal.constants;

import java.util.HashMap;
import java.util.Map;

public enum OutputType {
    WIDE("Wd"),
    WICKET("W"),
    NO_BALL("Nb"),
    RUN("R");

    private String type;
    private OutputType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public static final Map<String, OutputType> getOutputTypeMap = new HashMap();

    static {
        for(OutputType op: OutputType.values()) {
            getOutputTypeMap.put(op.getType(), op);
        }
    }

    public static OutputType getOutputType(String type) {
        return getOutputTypeMap.get(type);
    }
}
