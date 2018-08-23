import {Module} from "./module";
import {User} from "./user";

export class Category {

  public id?: number;
  public name: string;
  public module: Module;
  public user?: User;
}
