package rwilk.logindemo2.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import rwilk.logindemo2.model.Category;
import rwilk.logindemo2.service.ICategoryService;

@RestController
@RequestMapping("/categories")
public class CategoryController {

  @Autowired
  private ICategoryService service;

  @RequestMapping(value = "", method = RequestMethod.GET)
  public List<Category> getAllCategories() {
    return service.getCategories();
  }

  /*@RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public Category getCategory(@PathVariable Long id) {
    return service.getCategory(id);
  }*/

  @RequestMapping(value = "/{username}", method = RequestMethod.GET)
  public List<Category> getCategory(@PathVariable String username) {
    return service.getCategories(username);
  }

  @RequestMapping(value = "", method = RequestMethod.POST)
  public Category addCategory(@RequestBody Category category) {
    return service.addCategory(category);
  }

}
