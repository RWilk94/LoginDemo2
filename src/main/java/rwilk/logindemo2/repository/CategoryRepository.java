package rwilk.logindemo2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import rwilk.logindemo2.model.Category;
import rwilk.logindemo2.model.User;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {

  @Modifying(clearAutomatically = true)
  @Query(value = "SELECT c FROM Category c WHERE c.user =:user OR c.user = null")
  List<Category> getUserCategories(@Param("user")User user);

}
