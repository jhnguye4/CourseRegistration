package edu.ncsu.csc216.pack_scheduler.user;
/**
 * Abstract class for user that has setters/getters for a Students firstName, lastName, id, 
 * email, and password
 *  
 * @author jhnguye4
 *
 */
public abstract class User {

	/** Student first name. */
	private String firstName;
	/** Student last name. */
	private String lastName;
	/** Student id. */
	private String id;
	/** Student email. */
	private String email;
	/** Student password. */
	private String password;
	
	/**
	 * Constructor method that creates Student Object and contains all fields except maxCredits which is set to 18 
	 * @param firstName name of user
	 * @param lastName name of user 
	 * @param id is the id user will use to access account
	 * @param email of user
	 * @param password for user 
	 */
	public User(String firstName, String lastName, String id, String email, String password) {
		setFirstName(firstName);
		setLastName(lastName);
		setId(id);
		setEmail(email);
		setPassword(password);
	}
	
	/**
	 * Getter method for firstName
	 * @return firstName of user 
	 */
	public String getFirstName() {
		return firstName;
	}
	
	/**
	 * Setter method for firstName
	 * @param firstName is name that is going to be set
	 * @throws IllegalArgumentException if the firstName is null or empty
	 */
	public void setFirstName(String firstName) {
		if (firstName == null || firstName.equals("")) {
			throw new IllegalArgumentException("Invalid first name");
		}
		this.firstName = firstName;
	}
	
	/**
	 * Getter method for lastName
	 * @return lastName of user 
	 */
	public String getLastName() {
		return lastName;
	}
	
	/**
	 * Setter method for lastName
	 * @param lastName is name that is going to be set
	 * @throws IllegalArgumentException if the lastName is null or empty
	 */
	public void setLastName(String lastName) {
		if (lastName == null || lastName.equals("")) {
			throw new IllegalArgumentException("Invalid last name");
		}
		this.lastName = lastName;
	}
	/**
	 * Getter method for id
	 * @return id of user 
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * Setter method for id
	 * @param id is the id that user uses to access acount
	 * @throws IllegalArgumentException if the id is null or empty
	 */
	public void setId(String id) {
		if (id == null || id.equals("")) {
			throw new IllegalArgumentException("Invalid id");
		}
		this.id = id;
	}
	
	/**
	 * Getter method for email
	 * @return email of user 
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * Setter method for email
	 * @param email String of email
	 * @throws IllegalArgumentException if the email is null or empty, doesnt contain '@' or '.', or if '.' comes before '@'.
	 */
	public void setEmail(String email) {
		
		if (email == null || email.equals("")) {
			throw new IllegalArgumentException("Invalid email");
		}
		if(!email.contains("@") || !email.contains(".")) {
			throw new IllegalArgumentException("Invalid email");
		}
		int firstIndex = email.lastIndexOf(".");
		int secondIndex = email.indexOf("@");
		if(firstIndex < secondIndex) {
			throw new IllegalArgumentException("Invalid email");
		}
		this.email = email;
	}
	
	/**
	 * Getter method for password
	 * @return password of user
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * Setter method for password
	 * @param password is password user uses to access accoutn
	 * @throws IllegalArgumentException if the password is null or empty
	 */
	public void setPassword(String password) {
		if (password == null || password.equals("")) {
			throw new IllegalArgumentException("Invalid password");
		}
		this.password = password;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		return true;
	}

}