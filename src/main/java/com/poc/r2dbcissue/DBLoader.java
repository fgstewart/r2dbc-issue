package com.poc.r2dbcissue;

import com.poc.r2dbcissue.model.Test;
import com.poc.r2dbcissue.repository.TestRepository;
import org.springframework.context.annotation.DependsOn;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
@DependsOn("preload")
public class DBLoader {

  private final TestRepository repository;

  public DBLoader(TestRepository repository) {
    this.repository = repository;

    Flux.fromArray(new String[]{ "a", "b", "c", "d", "e", "f", "g", "h", "i", "j" })
        .map(name -> {
          Test t = new Test();
          t.setName(name);
          return t;
        })
        .flatMap(test -> repository.save(test))
        .subscribe();
  }
}
