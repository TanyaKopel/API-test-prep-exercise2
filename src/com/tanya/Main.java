package com.tanya;

public class Main {

    public static void main(String[] args) {

        while (DownloadSystem.instance.isRunning()) {
            DownloadSystem.instance.executeChoice(DownloadSystem.instance.chooseMenuOption());
        }
    }
}
