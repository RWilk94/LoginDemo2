package rwilk.logindemo2.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import rwilk.logindemo2.model.Module;
import rwilk.logindemo2.service.IModuleService;

@RestController
@RequestMapping("/modules")
public class ModuleController {

  @Autowired
  private IModuleService moduleService;

  @RequestMapping(value = "", method = RequestMethod.GET)
  public List<Module> getAllModules() {
    return moduleService.getModules();
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public Module getModuleById(@PathVariable Long id) {
    return moduleService.getModuleById(id);
  }

  /*@RequestMapping(value = "", method = RequestMethod.POST)
  public Module addModule()*/
}
