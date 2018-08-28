package rwilk.logindemo2.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Jwts;
import rwilk.logindemo2.model.Category;
import rwilk.logindemo2.service.ICategoryService;

import static rwilk.logindemo2.config.security.SecurityConstants.SECRET;
import static rwilk.logindemo2.config.security.SecurityConstants.TOKEN_PREFIX;

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

  // TODO change to PUT method
  @RequestMapping(value = "", method = RequestMethod.POST)
  public Category addCategory(@RequestBody Category category) {
    return service.addCategory(category);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  public void deleteCategory(@PathVariable Long id, @RequestHeader("Authorization") String authorization) {
    System.out.println("Authorization token: " + authorization);
//    checkUsername(authorization);
    service.deleteCategory(checkUsername(authorization), id);
  }

  private String checkUsername(String authorization) {
    String username = Jwts.parser().setSigningKey(SECRET)
        .parseClaimsJws(authorization.replace(TOKEN_PREFIX, ""))//remove Bearer
        .getBody()
        .getSubject();
    // System.err.println(username);
    return username;
  }

  @RequestMapping(value = "", method = RequestMethod.PATCH)
  public Category updateCategory(@RequestBody Category category, @RequestHeader("Authorization") String authorization) {
    return service.updateCategory(checkUsername(authorization), category);
  }

}
