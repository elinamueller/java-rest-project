package com.app.project.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
@ToString
@Entity
@Table(name = "user")
public class User implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(
			name = "user_sequence",
			sequenceName = "user_sequence",
			allocationSize = 1
			)
	@GeneratedValue(
			generator = "user_sequence",
			strategy = GenerationType.SEQUENCE)
	private Long id;
	
	@NotBlank
	@Column(nullable = false, unique = true)
	@Size(min=10, message="Username must be at least 10 characters long.")
	private String username;
	
	@NotBlank
	@Column(nullable = false)
	@Size(min=8, message="Password must be at least 8 characters long.")
//	@Size(max=24, message="Password must be maximum 24 characters long.")
	private String password;
	
	@NotEmpty(message="First Name cannot be empty.")
	@NotBlank(message="First Name must contain at least one character.")
	@Pattern(regexp="^(?=.{1,40}$)[a-zA-Z]+(?:[-'\\\\s][a-zA-Z]+)*$", 
				message="First Name cannot contain any numbers or special characters.")
	@Size(max=256, message="First Name must be maximum 256 characters long.")
	private String firstname;
	
	@NotEmpty(message="Last Name cannot be empty.")
	@NotBlank(message="Last Name must contain at least one character.")
	@Pattern(regexp="^(?=.{1,40}$)[a-zA-Z]+(?:[-'\\\\s][a-zA-Z]+)*$", 
	message="Last Name cannot contain any numbers or special characters.")
	@Size(max=64, message="Last Name must be maximum 64 characters long.")
	private String lastname;
	
	@Email(message="Email must be a valid email.")
	@Column(nullable = false, unique = true)
	@NotEmpty(message="Email cannot be empty.")
	@Size(max=128, message="Email must be maximum 128 characters long.")
	private String email;
	
	@Size(max=128, message="Location must be maximum 128 characters long.")
	private String location;
	
	@Size(max=128, message="Occupation must be maximum 128 characters long.")
	private String occupation;
	
	@Size(max=256, message="Interests must be maximum 256 characters long.")
	private String interests;
	
	@Enumerated(EnumType.STRING)
	private Role role;
	
	public User(String username, String password, Role role) {
		this.username = username;
		this.password = password;
		this.role = role;
	}
	
	public User(String username, String password, String firstname, String lastname, String email) {
		super();
		this.username = username;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
	}

	public User(@Size(min = 10, message = "Username must be at least 10 characters long.") String username,
			@Size(min = 8, message = "Password must be at least 8 characters long.") String password,
			@NotEmpty(message = "First Name cannot be empty.") @NotBlank(message = "First Name must contain at least one character.") @Pattern(regexp = "^(?=.{1,40}$)[a-zA-Z]+(?:[-'\\\\s][a-zA-Z]+)*$", message = "First Name cannot contain any numbers or special characters.") @Size(max = 256, message = "First Name must be maximum 256 characters long.") String firstname,
			@NotEmpty(message = "Last Name cannot be empty.") @NotBlank(message = "Last Name must contain at least one character.") @Pattern(regexp = "^(?=.{1,40}$)[a-zA-Z]+(?:[-'\\\\s][a-zA-Z]+)*$", message = "Last Name cannot contain any numbers or special characters.") @Size(max = 64, message = "Last Name must be maximum 64 characters long.") String lastname,
			@Email(message = "Email must be a valid email.") @NotEmpty(message = "Email cannot be empty.") @Size(max = 128, message = "Email must be maximum 128 characters long.") String email,
			@Size(max = 128, message = "Location must be maximum 128 characters long.") String location,
			@Size(max = 128, message = "Occupation must be maximum 128 characters long.") String occupation,
			@Size(max = 256, message = "Interests must be maximum 256 characters long.") String interests) {
		super();
		this.username = username;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.location = location;
		this.occupation = occupation;
		this.interests = interests;
	}

	public User(
			@NotBlank @Size(min = 10, message = "Username must be at least 10 characters long.") String username,
			@NotBlank @Size(min = 8, message = "Password must be at least 8 characters long.")String password,
			@NotEmpty(message = "First Name cannot be empty.") @NotBlank(message = "First Name must contain at least one character.") @Pattern(regexp = "^(?=.{1,40}$)[a-zA-Z]+(?:[-'\\\\s][a-zA-Z]+)*$", message = "First Name cannot contain any numbers or special characters.") @Size(max = 256, message = "First Name must be maximum 256 characters long.") String firstname,
			@NotEmpty(message = "Last Name cannot be empty.") @NotBlank(message = "Last Name must contain at least one character.") @Pattern(regexp = "^(?=.{1,40}$)[a-zA-Z]+(?:[-'\\\\s][a-zA-Z]+)*$", message = "Last Name cannot contain any numbers or special characters.") @Size(max = 64, message = "Last Name must be maximum 64 characters long.") String lastname,
			@Email(message = "Email must be a valid email.") @NotEmpty(message = "Email cannot be empty.") @Size(max = 128, message = "Email must be maximum 128 characters long.") String email,
			@Size(max = 128, message = "Location must be maximum 128 characters long.") String location,
			@Size(max = 128, message = "Occupation must be maximum 128 characters long.") String occupation,
			@Size(max = 256, message = "Interests must be maximum 256 characters long.") String interests, Role role) {
		super();
		this.username = username;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.location = location;
		this.occupation = occupation;
		this.interests = interests;
		this.role = role;
	}	

}
