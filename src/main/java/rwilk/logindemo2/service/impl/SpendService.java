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
  public List<Spend> getSpending(String username) {
    Optional<User> user = userRepository.findUserByUsername(username);
    return user.map(user1 -> spendRepository.findAllByUser(user1)).orElse(null);
  }

  @Override
  public Spend addSpend(String authorization, Spend spend) {
    Optional<Category> category = categoryRepository.findById(spend.getCategory().getId());
    Optional<User> user = userRepository.findUserByUsername(spend.getUser().getUsername());
    if (category.isPresent() && user.isPresent() && user.get().getUsername().equals(authorization)) {
      spend.setCategory(category.get());
      spend.setUser(user.get());
      return spendRepository.save(spend);
    }
    return null;
  }

  @Override
  public void deleteSpending(String authorization, Long id) {
    Spend spend = spendRepository.findById(id).get();
    if (spend.getUser() != null
        && spend.getUser().getUsername() != null
        && spend.getUser().getUsername().equals(authorization)) {
      spendRepository.delete(spend);
    } else {
      // TODO throw error unauthorized
      System.err.println("Unauthorized deleted Spending...");
    }
  }

  @Override
  public Spend updateSpending(String authorization, Spend spend) {
    Spend newSpend = spendRepository.findById(spend.getId()).get();
    if (newSpend.getUser() != null
        && spend.getUser().getUsername() != null
        && spend.getUser().getUsername().equals(authorization)) {

      newSpend.setName(spend.getName());
      newSpend.setValue(spend.getValue());
      newSpend.setDate(spend.getDate());
      return spendRepository.save(newSpend);
    }
    return null;
  }

  @Override
  public List<Spend> getSpending(User user) {

    return null;
    //spendRepository.findAllBy

  }
}
