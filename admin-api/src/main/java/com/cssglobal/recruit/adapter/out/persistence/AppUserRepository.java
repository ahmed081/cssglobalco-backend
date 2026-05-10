package com.cssglobal.recruit.adapter.out.persistence;

import com.cssglobal.recruit.domain.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;
import java.util.UUID;

public interface AppUserRepository extends JpaRepository<AppUser, UUID>, JpaSpecificationExecutor<AppUser> {
    Optional<AppUser> findByEmailIgnoreCase(String email);

    Optional<AppUser> findByKeycloakUserId(String keycloakUserId);
}
