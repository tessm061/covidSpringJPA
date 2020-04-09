package org.jug.algeria.repository;

import org.jug.algeria.domain.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import java.util.*;

public interface UserRepository extends JpaRepository<AppUser, Long> {


}
