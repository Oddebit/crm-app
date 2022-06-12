package com.example.application.data;

import lombok.Getter;
import lombok.Setter;

import javax.annotation.Nullable;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import java.util.LinkedList;
import java.util.List;

@Entity
@Getter
@Setter
public class Company extends AbstractEntity {
  @NotBlank
  private String name;
  
  @OneToMany(mappedBy = "company")
  @Nullable
  private List<Contact> employees = new LinkedList<>();
}
