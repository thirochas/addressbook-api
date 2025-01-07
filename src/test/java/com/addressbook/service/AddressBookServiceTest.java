package com.addressbook.service;

import com.addressbook.dto.PersonDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddressBookServiceTest {

    private AddressBookService addressBookService;
    private List<PersonDTO> people;

    @BeforeEach
    public void setUp() {
        addressBookService = new AddressBookService();
        people = Arrays.asList(
                new PersonDTO("Bill McKnight", "Male", "16/03/77"),
                new PersonDTO("Paul Robinson", "Male", "15/01/85"),
                new PersonDTO("Gemma Lane", "Female", "20/11/91"),
                new PersonDTO("Sarah Stone", "Female", "20/09/80"),
                new PersonDTO("Wes Jackson", "Male", "14/08/74")
        );
    }

    @Test
    public void testCountMales() {
        long maleCount = addressBookService.countMales(people);
        assertEquals(3, maleCount);
    }

    @Test
    public void testFindOldestPerson() {
        PersonDTO oldestPerson = addressBookService.findOldestPerson(people);
        assertEquals("Wes Jackson", oldestPerson.getName());
    }

    @Test
    public void testCalculateDaysDifference() {
        long daysDifference = addressBookService.calculateDaysDifference(people, "Bill McKnight", "Paul Robinson");
        assertEquals(2862, daysDifference);
    }

    @Test
    public void testCalculateDaysDifferenceWithNonExistingPerson() {
        long daysDifference = addressBookService.calculateDaysDifference(people, "Bill McKnight", "Non Existing");
        assertEquals(0, daysDifference);
    }
}