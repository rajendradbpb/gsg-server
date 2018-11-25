package com.gsg.mongo.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import org.apache.commons.lang.StringUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.gsg.mongo.model.master.SchemeData;

@Document(collection = "appuser")
@TypeAlias("AppUser")
@JsonInclude(Include.NON_NULL)
@Getter
@Setter
@ToString
public class AppUser extends Auditable {

	@Id
	@JsonIgnore
	private String id;
	private String userId;
	private String email;
	private String firstName;
	private String middleName;
	private String lastName;
	private String password;
	private String gender;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate dob;
	private String maritalStatus;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate anniversaryDate;
	private String contactNbr;
	private String altenateContactNbr;
	private String deviceId;
	private List<String> roles = new ArrayList<>();
	private boolean isActive;
	private String userAcceptance = "N";
	
	private List<AddressBook> address = new ArrayList<>();
	private List<UserVehicle> userVehicles = new ArrayList<>();

	private double[] userLocation;
	
	private String referralCode;
	// @DBRef(lazy = false)
	private List<SchemeData> schemes = new ArrayList<>();

	private ServiceArea serviceArea = new ServiceArea();

	public AppUser(String email, String contactNbr, String password) {
		this.email = email;
		this.password = password;
		this.contactNbr = contactNbr;
	}

	public AppUser() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public LocalDate getAnniversaryDate() {
		return anniversaryDate;
	}

	public void setAnniversaryDate(LocalDate anniversaryDate) {
		this.anniversaryDate = anniversaryDate;
	}

	public String getContactNbr() {
		return contactNbr;
	}

	public void setContactNbr(String contactNbr) {
		this.contactNbr = contactNbr;
	}

	public String getAltenateContactNbr() {
		return altenateContactNbr;
	}

	public void setAltenateContactNbr(String altenateContactNbr) {
		this.altenateContactNbr = altenateContactNbr;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public String getUserAcceptance() {
		return userAcceptance;
	}

	public void setUserAcceptance(String userAcceptance) {
		this.userAcceptance = userAcceptance;
	}

	public List<AddressBook> getAddress() {
		return address;
	}

	public void setAddress(List<AddressBook> address) {
		this.address = address;
	}

	public List<UserVehicle> getUserVehicles() {
		return userVehicles;
	}

	public void setUserVehicles(List<UserVehicle> userVehicles) {
		this.userVehicles = userVehicles;
	}

	public double[] getUserLocation() {
		return userLocation;
	}

	public void setUserLocation(double[] userLocation) {
		this.userLocation = userLocation;
	}

	public String getReferralCode() {
		return referralCode;
	}

	public void setReferralCode(String referralCode) {
		this.referralCode = referralCode;
	}

	public List<SchemeData> getSchemes() {
		return schemes;
	}

	public void setSchemes(List<SchemeData> schemes) {
		this.schemes = schemes;
	}

	public ServiceArea getServiceArea() {
		return serviceArea;
	}

	public void setServiceArea(ServiceArea serviceArea) {
		this.serviceArea = serviceArea;
	}

	@JsonInclude(Include.NON_EMPTY)
	@Getter
	@Setter
	@ToString
	public static class AddressBook {

		private String houseNbr;
		private String apartmentName;
		private String street;
		private String locality;
		private String city;
		private String district;
		private String po_ps_name;
		private String zip;
		private String state;
		private String stateCd;
		private String country;
		private MapLocation location;

		public AddressBook() {
			//
		}
		
		public String getHouseNbr() {
			return houseNbr;
		}

		public void setHouseNbr(String houseNbr) {
			this.houseNbr = houseNbr;
		}

		public String getApartmentName() {
			return apartmentName;
		}

		public void setApartmentName(String apartmentName) {
			this.apartmentName = apartmentName;
		}

		public String getStreet() {
			return street;
		}

		public void setStreet(String street) {
			this.street = street;
		}

		public String getLocality() {
			return locality;
		}

		public void setLocality(String locality) {
			this.locality = locality;
		}

		public String getCity() {
			return city;
		}

		public void setCity(String city) {
			this.city = city;
		}

		public String getDistrict() {
			return district;
		}

		public void setDistrict(String district) {
			this.district = district;
		}

		public String getPo_ps_name() {
			return po_ps_name;
		}

		public void setPo_ps_name(String po_ps_name) {
			this.po_ps_name = po_ps_name;
		}

		public String getZip() {
			return zip;
		}

		public void setZip(String zip) {
			this.zip = zip;
		}

		public String getState() {
			return state;
		}

		public void setState(String state) {
			this.state = state;
		}

		public String getStateCd() {
			return stateCd;
		}

		public void setStateCd(String stateCd) {
			this.stateCd = stateCd;
		}

		public String getCountry() {
			return country;
		}

		public void setCountry(String country) {
			this.country = country;
		}

		public MapLocation getLocation() {
			return location;
		}

		public void setLocation(MapLocation location) {
			this.location = location;
		}

		@JsonIgnore
		public String getAddressFull() {
			StringBuilder add = new StringBuilder();
			add.append(StringUtils.trimToEmpty(houseNbr).isEmpty()?"":StringUtils.trimToEmpty(houseNbr)+", ")
			.append(StringUtils.trimToEmpty(apartmentName).isEmpty()?"":StringUtils.trimToEmpty(apartmentName)+", ")
			.append(StringUtils.trimToEmpty(street).isEmpty()?"":StringUtils.trimToEmpty(street)+", ")
			.append(StringUtils.trimToEmpty(locality).isEmpty()?"":StringUtils.trimToEmpty(locality)+", ")
			.append(StringUtils.trimToEmpty(po_ps_name).isEmpty()?"":StringUtils.trimToEmpty(po_ps_name)+", ")
			.append(StringUtils.trimToEmpty(city).isEmpty()?"":StringUtils.trimToEmpty(city)+", ")
			.append(StringUtils.trimToEmpty(state).isEmpty()?"":StringUtils.trimToEmpty(state)+", ")
			.append(StringUtils.trimToEmpty(country).isEmpty()?"":StringUtils.trimToEmpty(country));
			return add.toString();
		}

	}

	@JsonInclude(Include.NON_EMPTY)
	@Getter
	@Setter
	@ToString
	public static class ServiceArea {
		private String state;
		private String district;
		private String location;
		private String subLocation;
		private MapLocation mapLocation;

		public ServiceArea() {
		}
	}

	@Getter
	@Setter
	public static class MapLocation {
		
		private double lng = 0;
		private double lat = 0;

		public boolean empty() {
			if (this.lat == 0 && this.lng == 0) 
				return true;
			return false;
		}

		public double getLng() {
			return lng;
		}

		public void setLng(double lng) {
			this.lng = lng;
		}

		public double getLat() {
			return lat;
		}

		public void setLat(double lat) {
			this.lat = lat;
		}

		@Override
		@JsonIgnore
		public String toString() {
			return this.lat + "," + this.lng;
		}

	}

}
