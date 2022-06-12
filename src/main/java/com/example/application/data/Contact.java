package com.example.application.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
public class Contact extends AbstractEntity {
  @NotEmpty
  private String firstName = "";
  
  @NotEmpty
  private String lastName = "";
  
  @ManyToOne
  @JoinColumn(name = "company_id")
  @NotNull
  @JsonIgnoreProperties({"employees"})
  private Company company;
  
  @NotNull
  @ManyToOne
  private Status status;
  
  @Email
  private String email;
  
}
