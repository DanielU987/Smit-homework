package com.clientmanager.clientmanager.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "Name")
    private String name;

    @Column(name="Secret Code")
    private String secretCodeName;

    @Column(name = "Phone Number")
    private String phoneNumber;

	public Contact() {

    }
    public Contact(String name, String secretCodeName, String phoneNumber) {
		this.name = name;
		this.secretCodeName = secretCodeName;
		this.phoneNumber = phoneNumber;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSecretCodeName() {
		return secretCodeName;
	}

	public void setSecretCodeName(String secretCodeName) {
		this.secretCodeName = secretCodeName;
	}

	public String getPhoneNumber() {
		return secretCodeName;
	}

	public void setPhoneNumber(String secretCodeName) {
		this.secretCodeName = secretCodeName;
	}

	@Override
	public String toString() {
		return "Contact [id=" + id + ", name=" + name + ", desc=" + secretCodeName + ", published=" + phoneNumber + "]";
	}

}
