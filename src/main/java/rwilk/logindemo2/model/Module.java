package rwilk.logindemo2.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "modules")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Module implements Serializable {

  @Id
  @SequenceGenerator(initialValue = 1, name = "moduleSG", sequenceName = "moduleSEQ")
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "moduleSG")
  private Long id;

  @NotNull
  @Size(min = 3, max = 255)
  private String name;

  //@JsonIgnore
  /*@OneToMany(mappedBy = "module", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
  private List<Category> categories;
*/
}
