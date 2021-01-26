package com.poc.r2dbcissue.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Table("test_table")
public class Test {

  @Id
  @Column("id")
  private UUID id;

  @Column("name")
  private String name;

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return "Test{" +
        "id=" + id +
        ", name='" + name + '\'' +
        '}';
  }
}
