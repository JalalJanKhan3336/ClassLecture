package com.thesoftparrot.classlecture.ali.room.relation;

import com.thesoftparrot.classlecture.ali.room.entity.CellNumber;
import com.thesoftparrot.classlecture.ali.room.entity.Person;

import java.util.List;

public class PersonWithCellNumber {
    private Person person;
    private List<CellNumber> cellNumberList;

    public PersonWithCellNumber(Person person, List<CellNumber> cellNumberList) {
        this.person = person;
        this.cellNumberList = cellNumberList;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public List<CellNumber> getCellNumberList() {
        return cellNumberList;
    }

    public void setCellNumberList(List<CellNumber> cellNumberList) {
        this.cellNumberList = cellNumberList;
    }
}
