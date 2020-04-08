package org.jug.algeria.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.persistence.Version;

@Entity
@Data
@RequiredArgsConstructor
public class AppUser {

  @Id @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private Long personId;

  @NotNull
  private String username;
}
