package com.poc.r2dbcissue.repository;

import com.poc.r2dbcissue.model.Test;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestRepository extends ReactiveCrudRepository<Test, String> {}
