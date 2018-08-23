package rwilk.logindemo2.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import rwilk.logindemo2.model.Spend;
import rwilk.logindemo2.model.User;

@Repository
public interface SpendRepository extends CrudRepository<Spend, Long> {

  public List<Spend> findAllByUser(User user);

}
