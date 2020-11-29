package com.thesoftparrot.classlecture.ali.room.entity;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity (tableName = "laptops")
public class Laptop {

    @PrimaryKey (autoGenerate = true)
    private long laptopId;
    private String companyName;
    private String color;
    private double price;

    @Embedded
    private Processor processor;

    public Laptop(String companyName, String color, double price, Processor processor) {
        this.companyName = companyName;
        this.color = color;
        this.price = price;
        this.processor = processor;
    }

    public long getLaptopId() {
        return laptopId;
    }

    public void setLaptopId(long laptopId) {
        this.laptopId = laptopId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Processor getProcessor() {
        return processor;
    }

    public void setProcessor(Processor processor) {
        this.processor = processor;
    }
}
