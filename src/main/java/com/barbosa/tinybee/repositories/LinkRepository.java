package com.barbosa.tinybee.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.barbosa.tinybee.domain.link.Link;

public interface LinkRepository extends JpaRepository<Link, UUID> {
  Optional<Link> findByShortenedUrl(String shortenedUrl);

  boolean existsByShortenedUrl(String shortenedUrl);
}
