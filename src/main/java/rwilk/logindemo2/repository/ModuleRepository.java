package rwilk.logindemo2.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import rwilk.logindemo2.model.Module;

@Repository
public interface ModuleRepository extends CrudRepository<Module, Long> {

  Module findByName(String name);

}
