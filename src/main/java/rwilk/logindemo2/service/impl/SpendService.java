package rwilk.logindemo2.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rwilk.logindemo2.repository.SpendRepository;
import rwilk.logindemo2.service.ISpendService;

@Service
public class SpendService implements ISpendService {

  @Autowired
  private SpendRepository repository;

}
