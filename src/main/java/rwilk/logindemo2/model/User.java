package rwilk.logindemo2.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Date;

@Entity
@Data
public class User implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @JsonIgnore
  private Long userId;
  @Column(unique = true)
  private String username;
  @Column(unique = true)
  private String email;
  private String password;
  @Transient
  private String confirmPassword;
  @CreationTimestamp
  private Date created;
}
