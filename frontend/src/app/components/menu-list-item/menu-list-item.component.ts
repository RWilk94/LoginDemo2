import {Component, Input, OnInit} from '@angular/core';
import {NavigationMenuItem} from "../../models/navigation-menu-item";
import {animate, state, style, transition, trigger} from "@angular/animations";
import {Router} from "@angular/router";

@Component({
  selector: 'app-menu-list-item',
  templateUrl: './menu-list-item.component.html',
  styleUrls: ['./menu-list-item.component.css'],
  animations: [
    trigger('indicatorRotate', [
      state('collapsed', style({transform: 'rotate(0deg)'})),
      state('expanded', style({transform: 'rotate(180deg)'})),
      transition('expanded <=> collapsed',
        animate('225ms cubic-bezier(0.4,0.0,0.2,1)')
      ),
    ])
  ]
})
/**
 * MenuListItemComponent class represent elements from left-side menu list.
 * item: NavigationMenuItem - current menu item with all children.
 * depth: number - current left margin for children elements (depth*12px)
 * expanded: boolean - flag says if menu is expanded or hidden
 */
export class MenuListItemComponent implements OnInit {

  @Input() item: NavigationMenuItem;
  @Input() depth: number;
  expanded: boolean;

  constructor(private router: Router) {
    this.expanded = false;
    this.depth = 0;
  }

  ngOnInit() {
  }

  onItemSelected(item: NavigationMenuItem) {
    if (!item.children || !item.children.length) {
      this.router.navigate([item.route]);
    }
    if (item.children && item.children.length) {
      this.expanded = !this.expanded;
    }
  }

}
