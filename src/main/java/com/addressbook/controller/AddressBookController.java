package com.addressbook.controller;

import com.addressbook.dto.AnswerDTO;
import com.addressbook.dto.PersonDTO;
import com.addressbook.service.AddressBookService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

@RestController
@RequestMapping("/api/addressbook")
public class AddressBookController {

    private final AddressBookService addressBookService;

    public AddressBookController(AddressBookService addressBookService) {
        this.addressBookService = addressBookService;
    }

    @PostMapping("/import")
    public ResponseEntity<List<AnswerDTO>> importFile(@RequestParam("file") MultipartFile file) throws IOException {
        var peopleData = parseFile(file);

        long malesQtty = addressBookService.countMales(peopleData);
        PersonDTO oldestPerson = addressBookService.findOldestPerson(peopleData);
        long daysDifference = addressBookService.calculateDaysDifference(peopleData, "Bill McKnight", "Paul Robinson");

        List<AnswerDTO> answers = List.of(
                new AnswerDTO("Number of males", String.valueOf(malesQtty)),
                new AnswerDTO("Oldest person", oldestPerson.getName()),
                new AnswerDTO("Days Bill is older than Paul", String.valueOf(daysDifference))
        );

        return ResponseEntity.ok(answers);
    }

    private List<PersonDTO> parseFile(MultipartFile file) throws IOException {
        List<PersonDTO> people = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(", ");
                if (parts.length == 3) {
                    PersonDTO person = new PersonDTO();
                    person.setName(parts[0]);
                    person.setGender(parts[1]);
                    person.setBirthDate(parts[2]);
                    people.add(person);
                }
            }
        }

        return people;
    }
}
