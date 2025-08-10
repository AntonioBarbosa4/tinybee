package com.barbosa.tinybee.domain.link;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "link")
@Entity
public class Link {

  @Id
  @GeneratedValue
  private UUID id;

  @Column(name = "original_url", nullable = false, length = 2048)
  private String url;

  @Column(name = "shortened_url", nullable = false, unique = true, length = 100)
  private String shortenedUrl;

  @Column(name = "created_at", nullable = false)
  private LocalDateTime createdAt;

  @Column(name = "is_active", nullable = false)
  private boolean isActive;
}
