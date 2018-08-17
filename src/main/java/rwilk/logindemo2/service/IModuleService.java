package rwilk.logindemo2.service;

import java.util.List;

import rwilk.logindemo2.model.Module;

public interface IModuleService {

  List<Module> getModules();

  Module getModuleById(Long id);

  Module getModuleByName(String name);

}
