package rwilk.logindemo2.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rwilk.logindemo2.model.Category;
import rwilk.logindemo2.model.Module;
import rwilk.logindemo2.model.User;
import rwilk.logindemo2.repository.CategoryRepository;
import rwilk.logindemo2.repository.ModuleRepository;
import rwilk.logindemo2.repository.UserRepository;
import rwilk.logindemo2.service.ICategoryService;

@Service
public class CategoryService implements ICategoryService {

  @Autowired
  private CategoryRepository categoryRepository;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  ModuleRepository moduleRepository;

  @Override
  public List<Category> getCategories() {
    return (List<Category>) categoryRepository.findAll();
  }

  @Override
  public Category getCategory(Long id) {
    return categoryRepository.findById(id).orElse(null);
  }

  @Override
  public Category addCategory(Category category) {
    Optional<User> user = userRepository.findUserByUsername(category.getUser().getUsername());
    Module module = moduleRepository.findByName(category.getModule().getName());
    if (user.isPresent() && module != null) {
      category.setUser(user.get());
      category.setModule(module);
      return categoryRepository.save(category);
    }
    return null;
  }
}
