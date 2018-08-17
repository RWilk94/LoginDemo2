import {Injectable} from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class NavigationMenuService {

  public appDrawer: any;
  private opened: boolean = true;

  constructor() {
  }

  public closeNav() {
    this.opened = !this.opened;
    this.appDrawer.close();
  }

  public openNav() {
    this.opened = !this.opened;
    this.appDrawer.open();
  }

  public changeVisibilityOfSideNav() {
    if (this.opened) {
      this.closeNav();
    } else {
      this.openNav();
    }
  }

}
