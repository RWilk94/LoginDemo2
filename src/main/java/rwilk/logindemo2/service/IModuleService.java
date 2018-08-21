package rwilk.logindemo2.service;

import java.util.List;

import rwilk.logindemo2.model.Module;

/**
 * Module
 * getModules - return all modules - probability unused
 * getModuleById - return module by id
 * addModule - unused
 */
public interface IModuleService {

  List<Module> getModules();

  Module getModuleById(Long id);

  //Module getModuleByName(String name);

  //Module addModule(Module module);

}
