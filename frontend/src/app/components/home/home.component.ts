import {Component, OnInit} from '@angular/core';
import {TestService} from "../../services/test.service";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {

  newData;
  oldData;

  constructor(private testService: TestService) {
    this.newData = testService.getDataNew();
  }

  onSubmitNew() {
    this.testService.getDataNew().subscribe(
      (data: string) => {
        console.log('It works... ' + data);
      }
      ,
      error1 => console.log(error1)
    )
  }

  onSubmitOld() {
    this.testService.getDataOld().subscribe(
      data => console.log(data),
      error1 => console.log(error1)
    )
  }


}
