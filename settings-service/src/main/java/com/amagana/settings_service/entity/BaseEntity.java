package com.amagana.settings_service.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@EntityListeners(AuditingEntityListener.class)

@Getter
@Setter
@MappedSuperclass
public class BaseEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@CreatedDate
	protected LocalDateTime createdAt;
	@CreatedBy
	protected String createdBy;
	@LastModifiedBy
	protected String lastUpdatedBy;
	@LastModifiedDate
	protected LocalDateTime lastUpdateDate;

}
