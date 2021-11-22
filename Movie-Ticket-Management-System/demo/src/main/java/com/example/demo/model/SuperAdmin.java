package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SuperAdmin {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private String adminName;
	private String password;
	private String email;
	@OneToOne
	@JoinColumn(name="role_id")
	private Roles roles;

}
