import {AbstractControl} from "@angular/forms";

/*export function zipcodeValidator(control: AbstractControl) {

  if (control && control.value !== null || control.value !== undefined) {
    const regex = new RegExp('^[0-9]{6}$');

    if (!regex.test(control.value)) {
      return {
        isError: true
      };
    }
  }
  return null;
}*/

export function usernameValidator(control: AbstractControl) {
  if (control && control.value !== null || control.value !== undefined) {

    const regex = new RegExp('^[a-zA-Z0-9]*$');

    if (!regex.test(control.value)) {
      return {
        isError: true
      };
    }
  }
  return null;
}

export function emailValidator(control: AbstractControl) {
  if (control && control.value !== null || control.value !== undefined) {

    const regex = new RegExp('^[a-z0-9._%+-]+[@][a-z0-9.-]+[.][a-z]{2,4}$');

    if (!regex.test(control.value)) {
      return {
        isError: true
      };
    }
  }
  return null;
}

/*export function passwordValidator(control: AbstractControl) {
  if (control && control.value != null || control.value != undefined) {

    const regex = new RegExp('^[]');

    if (!regex.test(control.value)) {
      return {
        isError: true
      };
    }
  }
  return null;
}*/

export function confirmPasswordValidator(control: AbstractControl) {
  if (control && control.value !== null || control.value !== undefined) {
    const confirmPasswordValue = control.value;

    const passControl = control.root.get('password');
    if (passControl) {
      const passwordValue = passControl.value;
      if (passwordValue !== confirmPasswordValue || passwordValue === '') {
        return {
          isError: true
        };
      }
    }
  }
  return null;
}
