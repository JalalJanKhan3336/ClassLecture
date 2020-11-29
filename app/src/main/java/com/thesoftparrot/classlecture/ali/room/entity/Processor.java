package com.thesoftparrot.classlecture.ali.room.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity (tableName = "processors")
public class Processor {

    @PrimaryKey(autoGenerate = true)
    private long processorId;

    private String processorCompanyName;

    private String frequency;

    private double processorPrice;

    public Processor(String processorCompanyName, String frequency, double processorPrice) {
        this.processorCompanyName = processorCompanyName;
        this.frequency = frequency;
        this.processorPrice = processorPrice;
    }

    public long getProcessorId() {
        return processorId;
    }

    public void setProcessorId(long processorId) {
        this.processorId = processorId;
    }

    public String getProcessorCompanyName() {
        return processorCompanyName;
    }

    public void setProcessorCompanyName(String processorCompanyName) {
        this.processorCompanyName = processorCompanyName;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public double getProcessorPrice() {
        return processorPrice;
    }

    public void setProcessorPrice(double processorPrice) {
        this.processorPrice = processorPrice;
    }
}
