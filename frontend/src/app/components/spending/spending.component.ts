import {AfterViewInit, Component, OnInit, ViewChild} from '@angular/core';
import {FormBuilder, FormControl, FormGroup} from "@angular/forms";
import {Category} from "../../models/category";
import {Spending} from "../../models/spending";
import {CategoryService} from "../../services/category.service";
import {SpendingService} from "../../services/spending.service";
import {Cookie} from "ng2-cookies/ng2-cookies";
import {User} from "../../models/user";
import {MatDialog, MatSort, MatTableDataSource} from "@angular/material";
import {Toast, ToasterService} from "angular2-toaster";
import {DialogConfirmDeleteComponent} from "../dialog-confirm-delete/dialog-confirm-delete.component";
import {ToastBuilder} from "../../models/toast-builder";

@Component({
  selector: 'app-spending',
  templateUrl: './spending.component.html',
  styleUrls: ['./spending.component.css']
})
export class SpendingComponent implements OnInit, AfterViewInit {

  @ViewChild(MatSort) sort: MatSort;

  public form: FormGroup;

  public spend: Spending;
  public spending: Spending[] = [];
  public categories: Category[] = [];

  displayedColumns: string[] = ['position', 'name', 'value', 'category', 'date', 'options'];
  dataSource = new MatTableDataSource<SpendingElement>(this.createSpendingElements());

  constructor(private formBuilder: FormBuilder, private spendingService: SpendingService, private categoryService: CategoryService,
              /*private notifier: NotifierService*/
              private toasterService: ToasterService, private dialog: MatDialog) {
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

  ngAfterViewInit() {
    this.dataSource.sortingDataAccessor = (item, property) => {
      switch (property) {
        case 'category': {
          return item.category.name;
        }
        case 'date': {
          return new Date(item.date1);
        }
        default: {
          return item[property];
        }
      }
    };
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
          this.displayToast(ToastBuilder.successAddItem());
          document.getElementById('button-reset').click();
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
      element.category = this.spending[i].category;
      element.date1 = new Date(this.spending[i].date);
      // element.date1 = new FormControl(new Date(this.spending[i].date));
      spendingElements.push(element);
    }
    console.log('createSpendingElements ' + spendingElements);
    return spendingElements;
  }

  private refresh() {
    //TODO get home spending only
    this.spendingService.getAllSpending(Cookie.get('username')).subscribe(data => {
      console.log(data);
      this.spending = data;
      this.dataSource = new MatTableDataSource<SpendingElement>(this.createSpendingElements());
      this.dataSource.sort = this.sort;
      this.ngAfterViewInit();
    })
  }

  deleteElement(element: SpendingElement) {
    let spend = this.spending[element.position - 1];
    console.log('Spending: ' + JSON.stringify(spend));
    this.spendingService.deleteSpending(spend).subscribe(data => {
      this.displayToast(ToastBuilder.successDeleteItem());
      this.refresh()
    });
  }

  /** Edit name value after blur on input */
  editSpendingName(name: string, element: SpendingElement) {
    console.log('editSpendingName');
    this.dataSource.data[element.position - 1].name = name;
  }

  /** Edit value value after blur on input */
  editSpendingValue(value: number, element: SpendingElement) {
    console.log('editSpendingValue');
    this.dataSource.data[element.position - 1].value = value;
  }

  /** Edit date value after changed value in input datepicker */
  editDateValue(value: Date, element: SpendingElement) {
    element.date1 = value;
  }

  /** Enable editing mode */
  editElement(element: SpendingElement) {
    element.isEditing = true;
    console.log('category: ' + JSON.stringify(element.category));
  }

  /** Compare categories when edit spending record */
  compareCategories(o1: Category, o2: Category): boolean {
    // console.log(JSON.stringify(o1) + ' vs ' + JSON.stringify(o2));
    return o1.name === o2.name && o1.id === o2.id && o1.module.id === o2.module.id;
  }

  /** This method save the spending object after edit */
  saveEditElement(element: SpendingElement) {
    if (!this.checkElement(element)) {
      return;
    }
    let spend = this.spending[element.position - 1];
    spend.name = element.name;
    spend.value = element.value;
    spend.category = element.category;

    let date = new Date();
    date.setDate(element.date1.getDate());
    date.setMonth(element.date1.getMonth());
    date.setFullYear(element.date1.getFullYear());
    spend.date = date;

    console.log(spend);
    this.spendingService.updateSpending(spend).subscribe(data => {
      console.log(data);
      this.displayToast(ToastBuilder.successUpdateItem());
      element.isEditing = false;
    }, error1 => console.log(error1));
  }

  /** Cancel edit mode. Restore previous value and disable edit mode */
  cancelEditElement(element: SpendingElement) {
    let originalRow = this.spending[element.position - 1];
    this.dataSource.data[element.position - 1].name = originalRow.name;
    this.dataSource.data[element.position - 1].value = originalRow.value;
    this.dataSource.data[element.position - 1].date1 = new Date(originalRow.date);
    element.isEditing = false;
  }

  /** Show confirm dialog after click on delete element button */
  confirmDeleteDialog(element: SpendingElement): void {
    console.log('confirmDeleteDialog');
    const dialogRef = this.dialog.open(DialogConfirmDeleteComponent, {
      width: '250px'
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result !== undefined && result === true) {
        this.deleteElement(element);
      }
    });
  }

  /** Method used to display toast object on screen */
  private displayToast(toast: Toast): void {
    this.toasterService.pop(toast);
  }

  /** Method check if the SpendingElement from the table is a correct object. */
  private checkElement(element: SpendingElement): boolean {
    if (element.name === null || element.name === undefined || element.name.length === 0) {
      this.displayToast(ToastBuilder.errorEmptyName());
      return false;
    } else if (element.value === null || element.value === undefined || isNaN(element.value)) {
      this.displayToast(ToastBuilder.errorEmptyValue());
      return false;
    } else if (element.category === null || element.category === undefined) {
      this.displayToast(ToastBuilder.errorEmptyCategory());
      return false;
    } else if (element.date1 === null || element.date1 === undefined) {
      this.displayToast(ToastBuilder.errorEmptyDate());
      return false;
    }
    return true;
  }
}

export class SpendingElement {
  position: number;
  name: string;
  value: number;
  category: Category;
  date1: Date;
  //  date1: FormControl = new FormControl(new Date());
  isEditing: boolean;
}
