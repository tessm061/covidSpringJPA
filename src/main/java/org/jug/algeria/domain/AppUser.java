package org.jug.algeria.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import org.hibernate.envers.Audited;
import java.lang.*;

@Entity
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Data
@RequiredArgsConstructor
public class AppUser {

  @Id 
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Audited
  @NotNull
  private String username;

}
