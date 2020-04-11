package org.jug.algeria.controller;

import org.jug.algeria.domain.*;
import org.jug.algeria.repository.*;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import org.hibernate.envers.*;

import javax.persistence.*;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

@RestController
@RequestMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
public class HomeController {

  //Autowired for setup
  @Autowired
  private UserRepository userRepository;

  @Autowired
  private UserHistoryRepository userHistoryRepository;

  @PersistenceContext
  private EntityManager entityManager;

  @GetMapping
  public ModelAndView home() {
    return new ModelAndView("index");
  }

  @GetMapping(value = "/hello")
  public ResponseEntity<String> sayHello() {
    return ResponseEntity.ok().body("Hello there !");
  }

 private void saveHistory(AppUser user){
  AppUserHistory userHistory = new AppUserHistory();
  userHistory.setUsername(user.getUsername());
  userHistory.setUserId(user.getId());
  userHistory.setRevision(1);
  userHistoryRepository.save(userHistory);
 }

 @PostMapping(value = "/user/update/{id}/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<AppUser> update(@PathVariable String username, @PathVariable Long id) {
    AppUser appUser = new AppUser();
    appUser.setId(id);
    appUser.setUsername(username);
    AppUser saved = userRepository.save(appUser);
    saveHistory(saved);
    return ResponseEntity.ok().body(saved);
  }

  @PostMapping(value = "/user/create/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<AppUser> create(@PathVariable String username) {
    AppUser appUser = new AppUser();
    appUser.setUsername(username);
    AppUser saved = userRepository.save(appUser);
    saved.setUsername("Tyler");
    AppUser saved2 = userRepository.save(saved);
    saveHistory(saved);
    return ResponseEntity.ok().body(saved2);
  }

 @GetMapping(value = "/user/prod/history/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<AppUser> findHistoryByProdId( @PathVariable Long id) {
    AuditReader reader = AuditReaderFactory.get(entityManager);
    AppUser all = reader.find(AppUser.class, id, 1);
    return ResponseEntity.ok().body(all);
  }
 

 @GetMapping(value = "/user/prod/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<AppUser> findByProdId() {
    final Optional<AppUser> all = userRepository.findById(1L);
    return ResponseEntity.ok().body(all.get());
  }



  @GetMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<AppUser>> findAll() {
    final List<AppUser> resultList = new ArrayList<>();
    final Iterable<AppUser> all = userRepository.findAll();
    all.forEach(resultList::add);
    return ResponseEntity.ok().body(resultList);
  }

   @GetMapping(value = "/user/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<AppUserHistory>> findById() {
    final List<AppUserHistory> all = userHistoryRepository.findByUserIdOrderByIdDesc(1L);
    return ResponseEntity.ok().body(all);
  }


}
