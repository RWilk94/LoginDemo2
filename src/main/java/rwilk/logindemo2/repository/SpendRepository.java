package rwilk.logindemo2.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import rwilk.logindemo2.model.Spend;

public interface SpendRepository extends CrudRepository<Spend, Long> {

  public List<Spend> findAllByUserUserIdAnd(Long userId);

}
