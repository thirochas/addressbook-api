package com.addressbook.service;

import com.addressbook.dto.PersonDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.List;

@Service
public class AddressBookService {
    private static final String MALE_GENDER = "Male";
    private static final String DATE_FORMAT = "dd/MM/yy";

    public long countMales(List<PersonDTO> people) {
        return people.stream().filter(person -> MALE_GENDER.equals(person.getGender())).count();
    }

    public PersonDTO findOldestPerson(List<PersonDTO> people) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
        return people.stream()
                .min(Comparator.comparing(person -> LocalDate.parse(person.getBirthDate(), formatter)))
                .orElse(null);
    }

    public long calculateDaysDifference(List<PersonDTO> people, String name1, String name2) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
        LocalDate date1 = people.stream()
                .filter(person -> name1.equals(person.getName()))
                .map(person -> LocalDate.parse(person.getBirthDate(), formatter))
                .findFirst()
                .orElse(null);

        LocalDate date2 = people.stream()
                .filter(person -> name2.equals(person.getName()))
                .map(person -> LocalDate.parse(person.getBirthDate(), formatter))
                .findFirst()
                .orElse(null);

        if (date1 != null && date2 != null) {
            return Math.abs(ChronoUnit.DAYS.between(date2, date1));
        } else {
            return 0;
        }
    }
}
