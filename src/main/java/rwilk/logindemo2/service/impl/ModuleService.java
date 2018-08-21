package rwilk.logindemo2.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rwilk.logindemo2.model.Module;
import rwilk.logindemo2.repository.ModuleRepository;
import rwilk.logindemo2.service.IModuleService;

@Service
public class ModuleService implements IModuleService {

  @Autowired
  private ModuleRepository repository;

  @Override
  public List<Module> getModules() {
    return (List<Module>) repository.findAll();
  }

  @Override
  public Module getModuleById(Long id) {
    return repository.findById(id).orElse(null);
  }

  /*@Override
  public Module getModuleByName(String name) {
    return repository.findByName(name);
  }*/

  /*@Override
  public Module addModule(Module module) {
    return repository.save(module);
  }*/

}
