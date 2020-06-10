package com.nehal.interfaces;

import java.io.IOException;

public interface ManageScoreBoard {
    void displayScoreBoard();
    void play() throws IOException;
    void setupScoreBoard() throws IOException;
}
