import {AbstractControl} from "@angular/forms";

export function zipcodeValidator(control: AbstractControl) {

  if (control && control.value !== null || control.value !== undefined) {
    const regex = new RegExp('^[0-9]{6}$');

    if (!regex.test(control.value)) {
      return {
        isError: true
      };
    }
  }
  return null;
}

export function passwordValidatior(control: AbstractControl) {
  if (control && control.value !== null || control.value !== undefined) {
    const confirmPasswordValue = control.value;

    const passControl = control.root.get('password');
    if (passControl) {
      const passwordValue = passControl.value;
      if (passwordValue !== confirmPasswordValue) {
        return {
          isError: true
        };
      }
    }
  }
  return null;
}
