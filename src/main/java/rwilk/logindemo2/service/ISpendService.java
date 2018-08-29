package rwilk.logindemo2.service;

import java.util.List;

import rwilk.logindemo2.model.Spend;
import rwilk.logindemo2.model.User;

public interface ISpendService {

  /** TODO edit to get spending for user */
  List<Spend> getSpending(String username);

  /** looks ok */
  Spend addSpend(String authorization, Spend spend);

  List<Spend> getSpending(User user);

  void deleteSpending(String authorization, Long id);

  Spend updateSpending(String authorization, Spend spend);
}
