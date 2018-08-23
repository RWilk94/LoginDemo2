package rwilk.logindemo2.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import rwilk.logindemo2.model.Category;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {
}
