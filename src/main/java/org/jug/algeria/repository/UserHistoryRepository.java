package org.jug.algeria.repository;

import org.jug.algeria.domain.AppUserHistory;
import org.springframework.data.repository.CrudRepository;
import java.util.*;

public interface UserHistoryRepository extends CrudRepository<AppUserHistory, Long> {

  List<AppUserHistory> findByUserIdOrderByIdDesc(Long id);
}
