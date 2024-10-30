package com.amagana.settings_service.entity;


import jakarta.persistence.Entity;
import lombok.*;

import java.util.Objects;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Address extends BaseEntity {

	private String addressName;
	private String addressCity;
	private int addressNumber;
	private String addressEmail;
	private String addressPhone;
	private String addressProfessionalPhone;
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(addressCity, addressEmail, addressName, addressNumber, addressPhone,
				addressProfessionalPhone);
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Address other = (Address) obj;
		return Objects.equals(addressCity, other.addressCity) && Objects.equals(addressEmail, other.addressEmail)
				&& Objects.equals(addressName, other.addressName) && addressNumber == other.addressNumber
				&& Objects.equals(addressPhone, other.addressPhone)
				&& Objects.equals(addressProfessionalPhone, other.addressProfessionalPhone);
	}
	
	


	
}
