package com.barbosa.tinybee.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.barbosa.tinybee.domain.link.Link;
import com.barbosa.tinybee.domain.link.LinkRequestDTO;
import com.barbosa.tinybee.domain.link.LinkResponseDTO;
import com.barbosa.tinybee.services.LinkService;

@RestController
@RequestMapping()
public class LinkController {

  @Autowired
  private LinkService linkService;

  @PostMapping("/link")
  public ResponseEntity<Link> create(@RequestBody LinkRequestDTO data) {

    Link link = this.linkService.createLink(data);

    return ResponseEntity.status(HttpStatus.CREATED).body(link);

  }

  @GetMapping("/{shortHash}")
  public ResponseEntity<Void> redirect(@PathVariable String shortHash) {

    String originalUrl = linkService.getOriginalUrl(shortHash);

    return ResponseEntity.status(HttpStatus.MOVED_PERMANENTLY)
        .location(URI.create(originalUrl))
        .build();
  }

  @GetMapping("/")
  public ResponseEntity<List<LinkResponseDTO>> fetchLinks(@RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "10") int size) {
    List<LinkResponseDTO> allLinks = this.linkService.fetchLinks(page, size);

    return ResponseEntity.ok(allLinks);
  }

}
