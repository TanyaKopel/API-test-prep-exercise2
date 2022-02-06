package com.tanya;

import com.tanya.beans.Download;
import com.tanya.tasks.DownloadTask;
import com.tanya.Util;

import java.util.*;

public class DownloadSystem {

    private boolean isRunning;

    /*
    this.isRunning = true;
        finishedDownloadKiller = new Thread(() -> {
          while (true) {
            try {
              Thread.sleep(20000);
            } catch (InterruptedException e) {
              System.out.println("counter-killer stopped");
            }

            for (FakeDownload download : getDownloads()) {
              if (download.isExpired()) {
                downloads.remove(download);
              }
            }
          }
        });
        finishedDownloadKiller.start();
     */
    private DownloadSystem() {
        this.isRunning = true;
        Thread finishedDownloadKiller = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(20000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                downloadKiller();
            }
        });
        finishedDownloadKiller.start();
    }

    public static final DownloadSystem instance = new DownloadSystem();


    public boolean isRunning() {
        return this.isRunning;
    }

    public void setRunning(boolean running) {
        this.isRunning = running;
    }

    public static HashSet<Download> downloads = new HashSet<>() {
    };

    public void startNewDownload() {
        System.out.println("Please enter the file name: ");
        Util.SCANNER.nextLine();
        String name = Util.SCANNER.nextLine();
        System.out.println("Please enter the source url: ");
        String location = Util.SCANNER.nextLine();
        final double size = Util.randomDouble();
        Download download = new Download(name, location, size);
        new Thread(new DownloadTask(download)).start();
        downloads.add(new Download(name, location, size));

    }

    public void pauseDownload() {
        System.out.println("Please enter the file name: ");
        Util.SCANNER.nextLine();
        String name = Util.SCANNER.nextLine();
        System.out.println("Please enter the source url: ");
        String location = Util.SCANNER.next();
        for (Download download : downloads) {
            if (download.getFileName().equals(name) && download.getUrl().equals(location)) {
                download.setActive(false);
                return;
            }
        }
        System.out.println("The specified file does not exist");
    }

    public void resumeDownload() {
        System.out.println("Please enter the file name: ");
        Util.SCANNER.nextLine();
        String name = Util.SCANNER.nextLine();
        System.out.println("Please enter the source url: ");
        String location = Util.SCANNER.next();
        for (Download download : downloads) {
            if (download.getFileName().equals(name) && download.getUrl().equals(location)) {
                download.setActive(true);
                new Thread(new DownloadTask(download)).start();
                return;
            }
        }
        System.out.println("The specified file does not exist");
    }

    public void getProgress() {
        System.out.println("Please enter the file name: ");
        Util.SCANNER.nextLine();
        String name = Util.SCANNER.nextLine();
        System.out.println("Please enter the source url: ");
        String location = Util.SCANNER.nextLine();
        for (Download download : downloads) {
            if (download.getFileName().equals(name) && download.getUrl().equals(location)) {
                System.out.println(Download.percentageDownloaded
                        (download.getDataFetched(), download.getSize()) + "%");
                return;
            }
            System.out.println("The specified file does not exist");
        }
    }

    public void downloadKiller() {
        for (Download download : downloads) {
            if (download.percentageDownloaded(download.getDataFetched(), download.getSize()) == 100) {
                downloads.remove(download);
            }
        }
    }

    public int chooseMenuOption() {
        System.out.println(
                "Menu:\n" +
                        "1. Download new file\n" +
                        "2. Pause download\n" +
                        "3. Resume download\n" +
                        "4. View all downloads\n" +
                        "5. Exit Program\n" +
                        "Please choose and option:");
        return Util.SCANNER.nextInt();

    }

    public void executeChoice(int choice) {
        switch (choice) {
            case 1:
                startNewDownload();
                break;

            case 2:
                pauseDownload();
                break;

            case 3:
                resumeDownload();
                break;

            case 4:
                for (Download download : downloads) {
                    System.out.println(download.toString());
                }
                break;
            case 5:
                instance.setRunning(false);
                break;

            default:
                System.out.println("Invalid choice");


        }
    }


}
