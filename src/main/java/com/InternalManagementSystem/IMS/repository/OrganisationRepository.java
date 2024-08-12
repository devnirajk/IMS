package com.InternalManagementSystem.IMS.repository;

import com.InternalManagementSystem.IMS.entity.Organisation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrganisationRepository extends JpaRepository<Organisation, Long> {
    Optional<Organisation> findByAdminEmail(String adminEmail);
}