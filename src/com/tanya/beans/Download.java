package com.tanya.beans;

import java.util.Calendar;

public class Download {

    private final String fileName;
    private final String url;
    private final double size;
    private double dataFetched;
    private boolean isActive;
    private Calendar isFinishedAt;

    public Download(String fileName, String url, double size) {
        this.fileName = fileName;
        this.url = url;
        this.size = size;
        this.dataFetched = 0;
        this.isActive = true;
        this.isFinishedAt = null;

    }

    public String getFileName() {
        return fileName;
    }

    public String getUrl() {
        return url;
    }

    public double getSize() {
        return size;
    }

    public double getDataFetched() {
        return dataFetched;
    }

    public void setDataFetched(double dataFetched) {
        this.dataFetched = dataFetched;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Calendar getIsFinishedAt() {
        return isFinishedAt;
    }

    public void setIsFinishedAt(Calendar isFinishedAt) {
        this.isFinishedAt = isFinishedAt;
    }

    public void addData(double data) {
        while (this.dataFetched < this.size) {
            this.dataFetched = +data;
        }
        this.dataFetched = size;
        this.setIsFinishedAt(Calendar.getInstance());
        this.setActive(false);
    }

    public static double percentageDownloaded(double dataFetched, double size) {
        return (dataFetched / size) * 100;
    }

    @Override
    public String toString() {
        return "Download{" +
                "fileName='" + fileName + '\'' +
                ", url='" + url + '\'' +
                ", size=" + size +
                ", dataFetched=" + dataFetched +
                ", isActive=" + isActive +
                ", isFinishedAt=" + isFinishedAt +
                '}';
    }


}
