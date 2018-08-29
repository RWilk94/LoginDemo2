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
import rwilk.logindemo2.model.Spend;
import rwilk.logindemo2.service.ISpendService;

import static rwilk.logindemo2.config.security.SecurityConstants.SECRET;
import static rwilk.logindemo2.config.security.SecurityConstants.TOKEN_PREFIX;

@RestController
@RequestMapping(value = "/spending")
public class SpendController {

  @Autowired
  private ISpendService spendService;

  @RequestMapping(value = "/{username}", method = RequestMethod.GET)
  public List<Spend> getSpending(@PathVariable String username, @RequestHeader("Authorization") String authorization) {
    if (checkUsername(authorization).equals(username)) {
      return spendService.getSpending(username);
    }
    return null;
  }

  @RequestMapping(value = "", method = RequestMethod.PUT)
  public Spend addSpend(@RequestBody Spend spend, @RequestHeader("Authorization") String authorization) {
    return spendService.addSpend(checkUsername(authorization), spend);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  public void deleteSpending(@PathVariable Long id, @RequestHeader("Authorization") String authorization) {
    spendService.deleteSpending(checkUsername(authorization), id);
  }

  @RequestMapping(value = "", method = RequestMethod.PATCH)
  public Spend updateSpending(@RequestBody Spend spend, @RequestHeader("Authorization") String authorization) {
    return spendService.updateSpending(checkUsername(authorization), spend);
  }

  private String checkUsername(String authorization) {
    return Jwts.parser().setSigningKey(SECRET)
        .parseClaimsJws(authorization.replace(TOKEN_PREFIX, ""))
        .getBody()
        .getSubject();
  }


}
