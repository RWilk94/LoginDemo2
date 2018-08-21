import { Component, OnInit } from '@angular/core';
import {Chart} from 'chart.js';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  public expenditure: number = 2500;
  public currency: string = '$';

  public chart: any;
  public chart1: any;
  public chart2: any;

  private label = ['home', 'car', 'wedding'];
  private data = [150, 250, 0, 450];
  private colors = ['red', 'green', 'blue', 'yellow'];
  private labels = ['red', 'green', 'blue', 'yellow'];
  //private labels = ['red']


  public generateChart() {

    this.chart = new Chart('canvas', {
      type: 'pie',
      data: {
        datasets: [{
          data: this.data,
          backgroundColor: this.colors,
          label: 'Home',
        }],
        labels: this.labels
      },
      options: {
        responsive: true,
        legend: {
          display: true
        }
      }
    });

  }

  /*public generateChart1() {

    this.chart = new Chart('canvas1', {
      type: 'pie',
      data: {
        datasets: [{
          data: [
            100,
            200,
            300
          ],
          backgroundColor: [
            'red',
            'blue',
            'green'
          ],
          label: 'Dataset1',
        }],
        labels: ['Red', 'Green', 'Blue']
      },
      options: {
        responsive: true,
        legend: {
          display: false
        }
      }
    });

  }

  public generateChart2() {

    this.chart = new Chart('canvas2', {
      type: 'pie',
      data: {
        datasets: [{
          data: [
            100,
            200,
            300
          ],
          backgroundColor: [
            'red',
            'blue',
            'green'
          ],
          label: 'Dataset1',
        }],
        labels: ['Red', 'Green', 'Blue']
      },
      options: {
        responsive: true,
        legend: {
          display: false
        }
      }
    });

  }*/

  ngOnInit(): void {
    this.generateChart();
    //this.generateChart1();
    //this.generateChart2();
  }

}
