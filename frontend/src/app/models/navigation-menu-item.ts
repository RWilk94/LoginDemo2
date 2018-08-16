export interface NavigationMenuItem {

  displayName: string;
  disabled?: boolean;
  iconName: string;
  route?: string;
  children?: NavigationMenuItem[];

}
