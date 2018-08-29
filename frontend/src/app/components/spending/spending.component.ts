import {Component, OnInit, ViewChild} from '@angular/core';
import {FormBuilder, FormControl, FormGroup} from "@angular/forms";
import {Category} from "../../models/category";
import {Spending} from "../../models/spending";
import {CategoryService} from "../../services/category.service";
import {SpendingService} from "../../services/spending.service";
import {Cookie} from "ng2-cookies/ng2-cookies";
import {User} from "../../models/user";
import {MatSort, MatTableDataSource} from "@angular/material";
import {CategoryElement} from "../categories/categories.component";

@Component({
  selector: 'app-spending',
  templateUrl: './spending.component.html',
  styleUrls: ['./spending.component.css']
})
export class SpendingComponent implements OnInit {

  @ViewChild(MatSort) sort: MatSort;

  public form: FormGroup;

  public spend: Spending;
  public spending: Spending[] = [];
  public categories: Category[] = [];

  displayedColumns: string[] = ['position', 'name', 'value', 'category', 'date', 'options'];
  dataSource = new MatTableDataSource<SpendingElement>(this.createSpendingElements());

  constructor(private formBuilder: FormBuilder, private spendingService: SpendingService, private categoryService: CategoryService) {
    this.spend = new Spending();

    // TODO get home category only
    this.categoryService.getCategories(Cookie.get('username')).subscribe(data => {
      this.categories = data;
    });

    this.refresh();
  }

  ngOnInit() {
    this.dataSource.sort = this.sort;

    this.form = this.formBuilder.group({
      name: new FormControl(this.spend.name),
      value: new FormControl(this.spend.value),
      category: new FormControl(this.spend.category),
      date: new FormControl(this.spend.date)
    });
  }

  onSubmit() {
    this.spend = new Spending();
    this.spend.name = this.form.get('name').value;
    this.spend.value = this.form.get('value').value;
    this.spend.category = this.form.get('category').value;
    this.spend.date = this.form.get('date').value;

    if (this.spend.name !== null && this.spend.value != null
      && this.spend.category !== null && this.spend.date !== null) {

      let username = Cookie.get('username');
      if (username !== null) {
        let user: User = new User();
        user.username = username;
        this.spend.user = user;

        // convert date1
        let date = new Date();
        date.setDate(this.spend.date.getDate());
        date.setMonth(this.spend.date.getMonth());
        date.setFullYear(this.spend.date.getFullYear());
        this.spend.date = date;

        this.spendingService.addSpending(this.spend).subscribe(data => {
          console.log('addSpending ' + JSON.stringify(this.spending));
          this.refresh();
        }, error1 => console.log(error1));
      }
    }
  }

  private createSpendingElements() {
    let spendingElements: SpendingElement[] = [];

    for (let i = 0; i < this.spending.length; i++) {
      let element: SpendingElement = new SpendingElement();
      element.position = i + 1;
      element.name = this.spending[i].name;
      element.value = this.spending[i].value;
      element.category = this.spending[i].category.name;
      element.date1 = this.spending[i].date;
      //element.isEditing = false;
      spendingElements.push(element);
    }
    console.log('createSpendingElements ' +spendingElements);
    return spendingElements;
  }

  private refresh() {
    //TODO get home spending only
    this.spendingService.getAllSpending(Cookie.get('username')).subscribe(data => {
      console.log(data);
      this.spending = data;
      this.dataSource = new MatTableDataSource<SpendingElement>(this.createSpendingElements());
      this.dataSource.sort = this.sort;
    })
  }

  deleteElement(element: SpendingElement) {
    let spend = this.spending[element.position - 1];
    console.log('Spending: ' + JSON.stringify(spend));
    this.spendingService.deleteSpending(spend).subscribe(data => this.refresh());
  }


  editSpendingName(name: string, element: SpendingElement) {
    this.dataSource.data[element.position - 1].name = name;
  }

  editSpendingValue(value: number, element: SpendingElement) {

  }


  editElement(element: SpendingElement){
    element.isEditing = true;
    console.log(JSON.stringify(element));
    //this.refresh();
  }

  saveEditElement(element: SpendingElement) {

  }

  cancelEditElement(element: SpendingElement) {

  }


}

export class SpendingElement {
  position: number;
  name: string;
  value: number;
  category: string;
  date1: Date;
  isEditing: boolean;
}
