package rwilk.logindemo2.service;

import java.util.List;

import rwilk.logindemo2.model.Category;

public interface ICategoryService {

  List<Category> getCategories();

  Category getCategory(Long id);

  Category addCategory(Category category);

}
