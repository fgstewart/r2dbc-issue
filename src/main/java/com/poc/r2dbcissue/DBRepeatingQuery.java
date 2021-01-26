package com.poc.r2dbcissue;

import com.poc.r2dbcissue.repository.TestRepository;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@EnableScheduling
public class DBRepeatingQuery {

  private final TestRepository repository;

  public DBRepeatingQuery(TestRepository repository) {
    this.repository = repository;
  }

  @Scheduled(fixedRate = 10000)
  public void repeatingQuery() {
    System.out.println("Fired schedlued call...");
    repository.findAll()
        .doOnNext(test -> System.out.println("  -->" + test))
        .onErrorResume(throwable -> {
          System.out.println("error: " + throwable.getMessage());
          return Mono.error(throwable);
        })
        .subscribe();
  }

}
