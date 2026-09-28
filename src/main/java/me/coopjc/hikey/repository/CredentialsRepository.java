package me.coopjc.hikey.repository;

import me.coopjc.hikey.model.Credentials;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CredentialsRepository extends JpaRepository<Credentials, Long> {
    Optional<Credentials> findByUserId(long userId);
}