package rwilk.logindemo2.service;

import java.util.List;

import rwilk.logindemo2.model.Category;

public interface ICategoryService {

  List<Category> getCategories();

  Category getCategory(Long id);

  List<Category> getCategories(String username);

  Category addCategory(Category category);

  void deleteCategory(String authorization, Long id);

  Category updateCategory(String authorization, Category category);

}
