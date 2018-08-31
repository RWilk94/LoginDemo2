import {Category} from "./category";
import {User} from "./user";

export class Spending {

  public id?: number;
  public name: string;
  public category: Category;
  public date: Date;
  public value: number;
  public user: User;
}
