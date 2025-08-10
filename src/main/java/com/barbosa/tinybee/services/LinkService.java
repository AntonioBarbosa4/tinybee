package com.barbosa.tinybee.services;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.barbosa.tinybee.domain.link.Link;
import com.barbosa.tinybee.domain.link.LinkRequestDTO;
import com.barbosa.tinybee.domain.link.LinkResponseDTO;
import com.barbosa.tinybee.repositories.LinkRepository;
import com.barbosa.tinybee.services.exceptions.LinkInactiveException;
import com.barbosa.tinybee.services.exceptions.LinkNotFoundException;

@Service
public class LinkService {

  private static final String SAFE_CHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
  private static final SecureRandom random = new SecureRandom();

  @Autowired
  private LinkRepository linkRepository;

  public String getAll() {
    return "hello";
  }

  public Link createLink(LinkRequestDTO data) {

    Link link = new Link();

    link.setUrl(data.url());
    link.setActive(true);
    link.setShortenedUrl(this.generateUniqueHash());
    link.setCreatedAt(LocalDateTime.now());

    this.linkRepository.save(link);

    return link;
  }

  public String getOriginalUrl(String shortHash) {

    Optional<Link> linkOptional = this.linkRepository.findByShortenedUrl(shortHash);

    if (linkOptional.isEmpty()) {
      throw new LinkNotFoundException(shortHash);
    }

    Link link = linkOptional.get();

    if (!link.isActive()) {
      throw new LinkInactiveException(shortHash);

    }
    return link.getUrl();
  }

  public List<LinkResponseDTO> fetchLinks(int page, int size) {
    Pageable pageable = PageRequest.of(page, size);
    Page<Link> linkPages = this.linkRepository.findAll(pageable);

    return linkPages.map(link -> new LinkResponseDTO(
        link.getId(),
        link.getUrl(),
        link.getShortenedUrl(),
        link.getCreatedAt(),
        link.isActive())).stream().toList();
  }

  private String generateUniqueHash() {
    String hash;
    do {
      hash = generateShortHash(7);
    } while (linkRepository.existsByShortenedUrl(hash));
    return hash;
  }

  private String generateShortHash(int length) {
    StringBuilder hash = new StringBuilder();
    for (int i = 0; i < length; i++) {
      hash.append(SAFE_CHARS.charAt(random.nextInt(SAFE_CHARS.length())));
    }
    return hash.toString();
  }

}
