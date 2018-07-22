package rwilk.logindemo2.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import rwilk.logindemo2.model.User;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    User findUserByUsername(String username);

    List<User> findAll();

}
