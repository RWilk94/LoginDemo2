import {Component, OnInit, ViewChild} from '@angular/core';
import {Category} from "../../models/category";
import {CategoryService} from "../../services/category.service";
import {Module} from "../../models/module";
import {ModuleService} from "../../services/module.service";
import {FormBuilder, FormControl, FormGroup} from "@angular/forms";
import {User} from "../../models/user";
import {Cookie} from "ng2-cookies/ng2-cookies";
import {MatSort, MatTableDataSource} from "@angular/material";

@Component({
  selector: 'app-categories',
  templateUrl: './categories.component.html',
  styleUrls: ['./categories.component.css']
})
export class CategoriesComponent implements OnInit {

  @ViewChild(MatSort) sort: MatSort;

  public categories: Category[] = [];
  public modules: Module[] = [];

  public category: Category;
  public form: FormGroup;

  displayedColumns: string[] = ['position', 'name', 'module', 'options'];
  dataSource = new MatTableDataSource<CategoryElement>(this.createCategoryElements());

  constructor(private moduleService: ModuleService, private categoryService: CategoryService, private formBuilder: FormBuilder) {
    this.category = new Category();

    moduleService.getModules().subscribe(modules => {
      this.modules = modules;
    });
    this.refresh();

  }

  ngOnInit() {
    this.dataSource.sort = this.sort;

    this.form = this.formBuilder.group({
      name: new FormControl(this.category.name),
      module: new FormControl(this.category.module)
    });
  }

  onSubmit() {
    this.category.name = this.form.get('name').value;
    this.category.module = this.form.controls['module'].value;

    if (this.category.name !== null && this.category.module !== null) {
      let username = Cookie.get('username');
      if (username !== null) {
        let user: User = new User();
        user.username = username;
        this.category.user = user;
        this.categoryService.addCategory(this.category).subscribe(
          data => {
            console.log(data);
            this.refresh();
          },
          error1 => console.log(error1));
      }
    }
  }

  // Refresh categories list after add or delete element
  refresh() {
    this.categoryService.getCategories(Cookie.get('username')).subscribe(categories => {
      console.log(categories);
      this.categories = categories;
      this.dataSource = new MatTableDataSource<CategoryElement>(this.createCategoryElements());
      this.dataSource.sort = this.sort;
    });
  }

  // Convert Category to CategoryElement in source data list
  private createCategoryElements() {
    let categoryElements: CategoryElement[] = [];

    for (let i = 0; i < this.categories.length; i++) {
      let element: CategoryElement = new CategoryElement();
      element.position = i + 1;
      element.name = this.categories[i].name;
      element.module = this.categories[i].module.name;
      element.isCustom = !(this.categories[i].user === null || this.categories[i].user === undefined);
      categoryElements.push(element);
    }
    return categoryElements;
  }

  deleteElement(element: CategoryElement) {
    let category = this.categories[element.position - 1];
    console.log('Category: ' + JSON.stringify(category));
    this.categoryService.deleteCategory(category).subscribe(data => this.refresh(), error1 => console.log(error1));
  }

  editElement(element: CategoryElement) {
    let category = this.categories[element.position - 1];
    category.name = element.name.trim();
    console.log('Edit category: ' + JSON.stringify(category));
    this.categoryService.updateCategory(category).subscribe(data => console.log(data), error1 => console.log(error1));
  }

  // Edit single element name in datasource
  editElementName(name: string, element: CategoryElement) {
    // Edit name
    this.dataSource.data[element.position - 1].name = name;
  }
}

export class CategoryElement {
  position: number;
  name: string;
  module: string;
  isCustom: boolean; //says if category is added by user or by system
}


