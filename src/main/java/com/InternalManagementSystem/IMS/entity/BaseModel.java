package com.InternalManagementSystem.IMS.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;


@MappedSuperclass
@Data
public abstract class BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long Id;    // primary key of an entity

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate    // only handle while object creation
    Date createdAt;


    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate   // only handle while object update
    Date updatedAt;
}