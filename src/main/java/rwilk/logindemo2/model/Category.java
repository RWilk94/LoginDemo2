package rwilk.logindemo2.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "categories")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Category implements Serializable {

  @Id
  @SequenceGenerator(initialValue = 1, name = "categorySG", sequenceName = "categorySEQ")
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "categorySG")
  //@JsonIgnore
  private Long id;

  @NotNull
  @Size(min = 3, max = 255)
  private String name;

  //@JsonIgnore
  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
  @JoinColumn(name = "id_user", referencedColumnName = "userId")
  private User user;

  //@JsonIgnore
  @NotNull
  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
  @JoinColumn(name = "id_module", referencedColumnName = "id")
  private Module module;

  ////@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
  /*@JsonIgnore
  @OneToMany(mappedBy = "category", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private List<Spend> spending;*/

}
