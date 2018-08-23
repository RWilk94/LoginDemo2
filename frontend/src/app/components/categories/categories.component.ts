import {Component, OnInit} from '@angular/core';
import {Category} from "../../models/category";
import {CategoryService} from "../../services/category.service";
import {Module} from "../../models/module";
import {ModuleService} from "../../services/module.service";
import {FormBuilder, FormControl, FormGroup} from "@angular/forms";
import {User} from "../../models/user";
import {Cookie} from "ng2-cookies/ng2-cookies";
import {MatTableDataSource} from "@angular/material";

@Component({
  selector: 'app-categories',
  templateUrl: './categories.component.html',
  styleUrls: ['./categories.component.css']
})
export class CategoriesComponent implements OnInit {

  public categories: Category[] = [];
  public modules: Module[] = [];

  public category: Category;
  public form: FormGroup;

  displayedColumns: string[] = ['position', 'name', 'weight', 'symbol'];
  dataSource = new MatTableDataSource<PeriodicElement>(ELEMENT_DATA);

  constructor(private moduleService: ModuleService, private categoryService: CategoryService, private formBuilder: FormBuilder) {
    this.category = new Category();

    moduleService.getModules().subscribe(modules => {
      this.modules = modules;
    });

    categoryService.getCategories().subscribe(categories => {
      this.categories = categories;
    });
  }

  ngOnInit() {
    this.form = this.formBuilder.group({
      name: new FormControl(this.category.name),
      module: new FormControl(this.category.module)
    });
  }

  onSubmit() {
    this.category.name = this.form.get('name').value;
    this.category.module = this.form.controls['module'].value;

    if (this.category.name !== null && this.category.module !== null) {
      // TODO send request
      let username = Cookie.get('username');
      if (username !== null) {
        let user: User = new User();
        user.username = username;
        this.category.user = user;
        this.categoryService.addCategory(this.category).subscribe(
          data => console.log(data), error1 => console.log(error1));
      }
    }
  }


}

export interface PeriodicElement {
  name: string;
  position: number;
  weight: number;
  symbol: string;
}

const ELEMENT_DATA: PeriodicElement[] = [
  {position: 1, name: 'Hydrogen', weight: 1.0079, symbol: 'H'},
  {position: 2, name: 'Helium', weight: 4.0026, symbol: 'He'},
  {position: 3, name: 'Lithium', weight: 6.941, symbol: 'Li'},
  {position: 4, name: 'Beryllium', weight: 9.0122, symbol: 'Be'},
  {position: 5, name: 'Boron', weight: 10.811, symbol: 'B'},
  {position: 6, name: 'Carbon', weight: 12.0107, symbol: 'C'},
  {position: 7, name: 'Nitrogen', weight: 14.0067, symbol: 'N'},
  {position: 8, name: 'Oxygen', weight: 15.9994, symbol: 'O'},
  {position: 9, name: 'Fluorine', weight: 18.9984, symbol: 'F'},
  {position: 10, name: 'Neon', weight: 20.1797, symbol: 'Ne'},
  {position: 11, name: 'Sodium', weight: 22.9897, symbol: 'Na'},
  {position: 12, name: 'Magnesium', weight: 24.305, symbol: 'Mg'},
  {position: 13, name: 'Aluminum', weight: 26.9815, symbol: 'Al'},
  {position: 14, name: 'Silicon', weight: 28.0855, symbol: 'Si'},
  {position: 15, name: 'Phosphorus', weight: 30.9738, symbol: 'P'},
  {position: 16, name: 'Sulfur', weight: 32.065, symbol: 'S'},
  {position: 17, name: 'Chlorine', weight: 35.453, symbol: 'Cl'},
  {position: 18, name: 'Argon', weight: 39.948, symbol: 'Ar'},
  {position: 19, name: 'Potassium', weight: 39.0983, symbol: 'K'},
  {position: 20, name: 'Calcium', weight: 40.078, symbol: 'Ca'},
];
