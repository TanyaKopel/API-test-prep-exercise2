package com.tanya.tasks;

import com.tanya.beans.Download;
import com.tanya.Util;

public class DownloadTask implements Runnable {

    private Download download;

    public DownloadTask(Download download) {
        this.download = download;
    }

@Override
    public void run() {
        while (this.download.isActive()) {
            try {
                Thread.sleep(Util.downloadDelay());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.download.addData(Util.downloadSpeed());

        }
    }


}



