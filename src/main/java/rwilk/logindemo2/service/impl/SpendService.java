package rwilk.logindemo2.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rwilk.logindemo2.model.Category;
import rwilk.logindemo2.model.Spend;
import rwilk.logindemo2.model.User;
import rwilk.logindemo2.repository.CategoryRepository;
import rwilk.logindemo2.repository.SpendRepository;
import rwilk.logindemo2.repository.UserRepository;
import rwilk.logindemo2.service.ISpendService;

@Service
public class SpendService implements ISpendService {

  @Autowired
  private SpendRepository spendRepository;

  @Autowired
  private CategoryRepository categoryRepository;

  @Autowired
  private UserRepository userRepository;

  @Override
  public List<Spend> getSpending() {
    return (List<Spend>) spendRepository.findAll();
  }

  @Override
  public Spend addSpend(Spend spend) {
    Optional<Category> category = categoryRepository.findById(spend.getCategory().getId());
    Optional<User> user = userRepository.findUserByUsername(spend.getUser().getUsername());
    if (category.isPresent() && user.isPresent()) {
      spend.setCategory(category.get());
      spend.setUser(user.get());
      return spendRepository.save(spend);
    }
    return null;
  }

  @Override
  public List<Spend> getSpending(User user) {

    return null;
    //spendRepository.findAllBy

  }
}
