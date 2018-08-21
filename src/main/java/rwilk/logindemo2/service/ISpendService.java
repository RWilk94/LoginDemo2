package rwilk.logindemo2.service;

import java.util.List;

import rwilk.logindemo2.model.Spend;


public interface ISpendService {

  /** TODO edit to get spending for user */
  List<Spend> getSpending();

  /** looks ok */
  Spend addSpend(Spend spend);

  List<Spend> getSpending(Long userId);
}
