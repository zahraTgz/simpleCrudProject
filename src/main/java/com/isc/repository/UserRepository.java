package com.isc.repository;

import com.isc.entity.AppUser;
import com.isc.entity.NamesOnly;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<AppUser, Long> {

    Optional<AppUser> findByUsername(String username);

    List<NamesOnly> findByLastName(String lastName);
}
