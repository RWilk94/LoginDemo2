package rwilk.logindemo2.repository;

import org.springframework.data.repository.CrudRepository;

import rwilk.logindemo2.model.Category;

public interface CategoryRepository extends CrudRepository<Category, Long> {
}
