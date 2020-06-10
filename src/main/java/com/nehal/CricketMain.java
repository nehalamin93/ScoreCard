package com.nehal;

import com.nehal.controller.GameController;

import java.io.IOException;

public class CricketMain {
    public static void main(String[] args) throws IOException {
        System.out.println("Starting Cricket Match ...");
        GameController gameController = new GameController();
        gameController.start();
    }
}
