package rwilk.logindemo2.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import rwilk.logindemo2.validator.Email;
import rwilk.logindemo2.validator.Username;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class User implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @JsonIgnore
  private Long userId;

  @Size(min = 3, max = 20)
  @Username(message = "Username contains invalid characters.")
  @NotNull
  @Column(unique = true)
  private String username;

  @Size(max = 256)
  @Email(message = "The email address has an invalid format.")
  @NotNull
  @Column(unique = true)
  private String email;

  @NotNull
  @Size(min = 6, max = 256)
  private String password;

  @Transient
  private String confirmPassword;

  @Transient
  private String oldPassword;

  @CreationTimestamp
  private Date created;

  /*@JsonIgnore
  @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
  private List<Spend> spending;*/

  /*@OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
  private List<Category> categories;*/
}
