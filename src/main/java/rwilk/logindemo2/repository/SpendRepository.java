package rwilk.logindemo2.repository;

import org.springframework.data.repository.CrudRepository;

import rwilk.logindemo2.model.Spend;

public interface SpendRepository extends CrudRepository<Spend, Long> {
}
