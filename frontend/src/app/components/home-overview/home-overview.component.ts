import {Component, OnInit} from '@angular/core';
import {SpendingService} from "../../services/spending.service";
import {Cookie} from "ng2-cookies/ng2-cookies";
import {Spending} from "../../models/spending";
import {Chart} from 'chart.js';

@Component({
  selector: 'app-home-overview',
  templateUrl: './home-overview.component.html',
  styleUrls: ['./home-overview.component.css']
})
export class HomeOverviewComponent implements OnInit {

  // private values = [];
  // private labels = [];

  public currentMonthChart: any;

  private spending: Spending[] = [];

  constructor(private spendingService: SpendingService) {
    this.spendingService.getAllSpending(Cookie.get('username')).subscribe(data => {
      this.spending = data;
      this.drawChartForCurrentMonth();
    });
  }

  ngOnInit() {

  }

  private colors = ['red', 'green', 'yellow', 'blue', 'orange', 'purple', 'cyan'];

  public drawChartForCurrentMonth() {
    // TODO get spending by date - current month; last month; next month; year by month
    console.log(JSON.stringify(this.spending));

    let values = [];
    let categories = [];

    this.spending.forEach(element => {

      if (categories.includes(element.category.name)) {
        values[categories.indexOf(element.category.name)] += element.value;
      } else {
        categories.push(element.category.name);
        values.push(element.value);
      }
    });

    while (this.colors.length < values.length) {
      this.colors.forEach(color => this.colors.push(color));
    }

    this.currentMonthChart = new Chart('canvas', {
      type: 'pie',
      data: {
        datasets: [{
          data: values,
          backgroundColor: this.colors,
          label: 'Current month'
        }],
        labels: categories
      },
      options: {
        responsive: true,
        legend: {
          display: true
        }
      }
    });

  }

}
