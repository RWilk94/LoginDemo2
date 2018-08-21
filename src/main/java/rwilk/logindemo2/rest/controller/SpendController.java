package rwilk.logindemo2.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import rwilk.logindemo2.model.Spend;
import rwilk.logindemo2.service.ISpendService;

@RestController
@RequestMapping(value = "/spending")
public class SpendController {

  @Autowired
  private ISpendService spendService;

  @RequestMapping(value = "", method = RequestMethod.GET)
  public List<Spend> getAllSpends() {
    return spendService.getSpending();
  }

  @RequestMapping(value = "", method = RequestMethod.POST)
  public Spend addSpend(@RequestBody Spend spend) {
    System.out.println("Spending");
    return spendService.addSpend(spend);
  }


}
