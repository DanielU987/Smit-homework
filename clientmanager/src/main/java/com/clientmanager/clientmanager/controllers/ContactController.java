package com.clientmanager.clientmanager.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.clientmanager.clientmanager.entities.Contact;
import com.clientmanager.clientmanager.repositories.ContactRepository;

import io.micrometer.common.util.StringUtils;

@RestController
@CrossOrigin(origins = "http://localhost:8081")
@RequestMapping("/api")
public class ContactController {

    @Autowired
    ContactRepository ContactRepository;

    @GetMapping("/users")
    public ResponseEntity<List<Contact>> getAllContacts(@RequestParam(required = false) String name,
            @RequestParam(required = false) String sort) {
        try {
            List<Contact> users = new ArrayList<Contact>();

            if (StringUtils.isEmpty(name)) {
                if (!StringUtils.isEmpty(sort)) {
                    Sort sortArray = Sort.by(Sort.Direction.fromString(sort), "name");
                    ContactRepository.findAll(sortArray).forEach(users::add);
                } else {
                    ContactRepository.findAll().forEach(users::add);
                }
            } else
                ContactRepository.findByName(name).forEach(users::add);

            if (users.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
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
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/users/{id}")
	public ResponseEntity<Contact> updateContact(@PathVariable("id") long id, @RequestBody Contact user) {
        Optional<Contact> userData = ContactRepository.findById(id);

		if (userData.isPresent()) {
			Contact _user = userData.get();
			_user.setName(user.getName());
			_user.setSecretCodeName(user.getSecretCodeName());
			_user.setPhoneNumber(user.getPhoneNumber());
			return new ResponseEntity<>(ContactRepository.save(_user), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

    @DeleteMapping("/users/{id}")
	public ResponseEntity<HttpStatus> deleteContact(@PathVariable("id") long id) {
        try {
			ContactRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
