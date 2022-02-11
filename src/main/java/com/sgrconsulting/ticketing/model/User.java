package com.sgrconsulting.ticketing.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(includeFieldNames = true)
@EqualsAndHashCode
@Table(name = "Users",
	uniqueConstraints = {
			@UniqueConstraint(columnNames = {"username"}),
			@UniqueConstraint(columnNames = {"email"}),
			@UniqueConstraint(columnNames = {"token"})
	})
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String name;
	
	private String lastname;
	
	private String email;
	
	private String username;
	
	private String password;
	
	private String token;
	
	public String getFullName() {
		return name + " " + lastname;
	}
	
}
