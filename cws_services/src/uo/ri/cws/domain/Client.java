package uo.ri.cws.domain;

import javax.persistence.*;

import alb.util.assertion.Argument;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "TCLIENTS")
public class Client extends BaseEntity {

	@Column(unique = true)
	private String dni;
	private String name;
	private String surname;
	private String email;
	private String phone;

	@Embedded
	private Address address;

	@OneToMany(mappedBy = "client")
	private Set<Vehicle> vehicles = new HashSet<Vehicle>();
	@OneToMany(mappedBy = "client")
	private Set<PaymentMean> paymentMeans = new HashSet<PaymentMean>();

	/**
	 * Client's constructor
	 */
	Client() {
	}

	/**
	 * Client's constructor
	 * 
	 * @param dni, the client dni
	 */
	public Client(String dni) {
		super();
		Argument.isNotNull(dni);
		this.dni = dni;
	}

	/**
	 * Client's constructor
	 * 
	 * @param dni,     the client's dni
	 * @param name,    the client's name
	 * @param surname, the client's surname
	 */
	public Client(String dni, String name, String surname) {
		this(dni);
		this.name = name;
		this.surname = surname;
	}

	/**
	 * Method which returns the vehicles
	 * 
	 * @return safe return of the vehicles
	 */
	public Set<Vehicle> getVehicles() {
		return new HashSet<Vehicle>(vehicles);
	}

	/**
	 * Method which returns the vehicles
	 * 
	 * @return the vehicles
	 */
	Set<Vehicle> _getVehicles() {
		return vehicles;
	}

	/**
	 * Method which returns the payment means
	 * 
	 * @return safe return of the payment means
	 */
	public Set<PaymentMean> getPaymentMeans() {
		return new HashSet<PaymentMean>(paymentMeans);
	}

	/**
	 * Method which returns the payment means
	 * 
	 * @return the payment means
	 */
	Set<PaymentMean> _getPaymentMeans() {
		return paymentMeans;
	}

	/**
	 * Method which returns the dni
	 * 
	 * @return the dni
	 */
	public String getDni() {
		return dni;
	}

	/**
	 * Method which returns the name
	 * 
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Method which returns the surname
	 * 
	 * @return the surname
	 */
	public String getSurname() {
		return surname;
	}

	/**
	 * Method which returns the email
	 * 
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Method which returns the phone
	 * 
	 * @return
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * Method which returns the address
	 * 
	 * @return the address
	 */
	public Address getAddress() {
		return address;
	}

	/**
	 * Method which sets the new address
	 * 
	 * @param address, the new address
	 */
	public void setAddress(Address address) {
		this.address = address;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dni == null) ? 0 : dni.hashCode());
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
		Client other = (Client) obj;
		if (dni == null) {
			if (other.dni != null)
				return false;
		} else if (!dni.equals(other.dni))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Client [dni=" + dni + ", name=" + name + ", surname=" + surname + ", email=" + email + ", phone="
				+ phone + ", address=" + address + "]";
	}

}
