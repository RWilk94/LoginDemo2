import {Component, OnInit, ViewChild} from '@angular/core';
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
export class SpendingComponent implements OnInit {

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
      element.category = this.spending[i].category.name;
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
  }

  saveEditElement(element: SpendingElement) {
    // element.date1 = this.form.get('date1').value;
    // console.log(element);
    // console.log(JSON.stringify(element));

    let spend = this.spending[element.position - 1];
    spend.name = element.name;
    spend.value = element.value;
    // spend.date = element.date1;

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
    //this.notifier.notify('success', 'message');
    /*this.toasterService.pop('warning', 'Args Title', 'Args Body');
    this.toasterService.pop('error', 'Args Title', 'Args Body');
    this.toasterService.pop('success', 'Args Title', 'Args Body');
    this.toasterService.pop('info', 'Args Title', 'Args Body');

    var toast: ToastBuilder = {
      type: 'success',
      title: 'close cutton',
      body: 'body',
      showCloseButton: true,
      timeout: 0
    };

    this.toasterService.pop(toast);*/

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

  private displayToast(toast: Toast): void {
    this.toasterService.pop(toast);
  }
}

export class SpendingElement {
  position: number;
  name: string;
  value: number;
  category: string;
  date1: Date;
  //  date1: FormControl = new FormControl(new Date());
  isEditing: boolean;
}
