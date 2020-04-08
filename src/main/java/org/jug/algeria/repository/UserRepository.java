package org.jug.algeria.repository;

import org.jug.algeria.domain.AppUser;
import org.springframework.data.repository.CrudRepository;
import java.util.*;

public interface UserRepository extends CrudRepository<AppUser, Long> {

  List<AppUser> findByPersonIdOrderByIdDesc(Long id);
}
