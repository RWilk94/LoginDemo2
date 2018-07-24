package rwilk.logindemo2.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import rwilk.logindemo2.validator.Username;

@Data
@Entity
public class User implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @JsonIgnore
  private Long userId;

  @Username(message = "Username contains invalid characters.")
  @NotNull
  @Column(unique = true)
  private String username;

  @NotNull
  @Column(unique = true)
  private String email;

  @NotNull
  private String password;

  @Transient
  private String confirmPassword;

  @CreationTimestamp
  private Date created;
}
