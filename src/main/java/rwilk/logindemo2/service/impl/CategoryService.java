package rwilk.logindemo2.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rwilk.logindemo2.model.Category;
import rwilk.logindemo2.repository.CategoryRepository;
import rwilk.logindemo2.service.ICategoryService;

@Service
public class CategoryService implements ICategoryService {

  @Autowired
  private CategoryRepository repository;

  @Override
  public List<Category> getCategories() {
    return (List<Category>) repository.findAll();
  }

  @Override
  public Category getCategory(Long id) {
    return repository.findById(id).orElse(null);
  }

  @Override
  public Category addCategory(Category category) {
    return null;
  }
}
