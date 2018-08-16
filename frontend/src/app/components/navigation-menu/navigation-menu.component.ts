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
      displayName: 'FirstItem',
      iconName: 'menu',
      children: [
        {
          displayName: 'Children1',
          iconName: 'menu'
        },
        {
          displayName: 'Children2',
          iconName: 'menu'
        }
      ]
    },
    {
      displayName: 'SecondItem',
      iconName: 'home'
    }
  ];

  ngOnInit() {
  }

  ngAfterViewInit() {
    this.navService.appDrawer = this.appDrawer;
  }

}
