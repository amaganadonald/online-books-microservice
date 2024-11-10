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
public class Category extends BaseEntity {

	private String categoryName;
	private String categoryDescription;
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(categoryDescription, categoryName);
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
		Category other = (Category) obj;
		return Objects.equals(categoryDescription, other.categoryDescription)
				&& Objects.equals(categoryName, other.categoryName);
	}
	
	
}
