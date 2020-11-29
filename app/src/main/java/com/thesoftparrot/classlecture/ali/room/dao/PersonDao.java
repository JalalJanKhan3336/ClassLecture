package com.thesoftparrot.classlecture.ali.room.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.thesoftparrot.classlecture.ali.room.entity.Person;
import com.thesoftparrot.classlecture.ali.room.relation.PersonWithCellNumber;

import java.util.List;

@Dao
public interface PersonDao {

    @Insert
    void addNewPerson(Person person);

    @Insert
    void addPersonList(List<Person> personList);

    @Update
    void updateExistingPerson(Person person);

    @Update
    void updateExistingPersonList(List<Person> personList);

    @Delete
    void deletePerson(Person person);

    @Delete
    void deletePersonList(List<Person> personList);

    @Query("delete from persons")
    void clearTable();

    @Query("select * from persons where id = :myId LIMIT 1")
    Person getSinglePerson(long myId);

    @Transaction
    @Query("select * from cell_numbers where personId = :personId LIMIT 1")
    PersonWithCellNumber getSinglePersonWithCellNumber(long personId);

    @Query("select * from persons")
    List<Person> getAllPersons();

}
