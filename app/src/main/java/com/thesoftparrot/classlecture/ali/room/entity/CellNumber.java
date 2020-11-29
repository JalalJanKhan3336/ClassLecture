package com.thesoftparrot.classlecture.ali.room.entity;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(
                tableName = "cell_numbers",
                foreignKeys = @ForeignKey(
                        entity = Person.class,
                        parentColumns = "id",
                        childColumns = "personId")
)
public class CellNumber {
    @PrimaryKey(autoGenerate = true)
    private long id;
    private String number;
    private long personId;

    public CellNumber(String number, long personId) {
        this.number = number;
        this.personId = personId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public long getPersonId() {
        return personId;
    }

    public void setPersonId(long personId) {
        this.personId = personId;
    }
}
