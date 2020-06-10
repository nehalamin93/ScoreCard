package com.nehal.interfaces;

import com.nehal.constants.BatsmanStatus;

public interface ManageBatsman {
    Integer getScore();
    Integer getBalls();
    Integer getRunUnitVsCount(Integer unit);
    String getName();
    void updateScore(Integer run);
    BatsmanStatus getStatus();
    void setStatus(BatsmanStatus status);
}
