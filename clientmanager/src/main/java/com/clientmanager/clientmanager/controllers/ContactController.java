package com.clientmanager.clientmanager.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.clientmanager.clientmanager.entities.Contact;
import com.clientmanager.clientmanager.repositories.ContactRepository;


@RestController
@CrossOrigin(origins = "http://localhost:8081")
@RequestMapping("/api")
public class ContactController {
    
    @Autowired
    ContactRepository ContactRepository;

    @GetMapping("/users")
    public ResponseEntity<List<Contact>> getAllContacts(@RequestParam(required = false) String name) {
        try {
            List<Contact> users = new ArrayList<Contact>();

            if (name == null)
                ContactRepository.findAll().forEach(users::add);
            else
                ContactRepository.findByName(name).forEach(users::add);

            if (users.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<Contact> getContactById(@PathVariable("id") long id) {
        Optional<Contact> userData = ContactRepository.findById(id);

        if (userData.isPresent()) {
            return new ResponseEntity<>(userData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/users")
    public ResponseEntity<Contact> createContact(@RequestBody Contact Contact) {
        try {
            Contact _user = ContactRepository
                    .save(new Contact(Contact.getName(), Contact.getSecretCodeName(), Contact.getPhoneNumber()));
            return new ResponseEntity<>(_user, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
