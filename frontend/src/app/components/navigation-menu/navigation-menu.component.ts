import {AfterViewInit, Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {NavigationMenuService} from "../../services/navigation-menu.service";
import {NavigationMenuItem} from "../../models/navigation-menu-item";

@Component({
  selector: 'app-side-nav',
  templateUrl: './navigation-menu.component.html',
  styleUrls: ['./navigation-menu.component.css']
})
export class NavigationMenuComponent implements OnInit, AfterViewInit {

  @ViewChild('appDrawer') appDrawer: ElementRef;

  constructor(private navService: NavigationMenuService) { }

  navItems: NavigationMenuItem[] = [
    {
      displayName: 'Home',
      iconName: 'explore',
      route: 'home'
    },
    {
      displayName: 'Home budget',
      iconName: 'attach_money',
      children: [
        {
          displayName: 'Bills',
          iconName: 'money',
          route: 'home'
        },
        {
          displayName: 'Food',
          iconName: 'restaurant',
          route: 'profile'
        }
      ]
    },
    {
      displayName: 'Car',
      iconName: 'drive_eta',
      children: [
        {
          displayName: 'Fuel',
          iconName: 'local_gas_station'
        }
      ]
    },
    {
      displayName: 'Wedding',
      iconName: 'favorite',
      children: [

      ]
    },
    {
      displayName: 'Profile',
      iconName: 'person'
    }
  ];

  ngOnInit() {
  }

  ngAfterViewInit() {
    this.navService.appDrawer = this.appDrawer;
  }

}
