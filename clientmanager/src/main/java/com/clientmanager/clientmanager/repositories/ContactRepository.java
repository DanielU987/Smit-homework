package com.clientmanager.clientmanager.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clientmanager.clientmanager.entities.Contact;

public interface ContactRepository extends JpaRepository<Contact,Long> {
    List<Contact> findByName(String name);
}
