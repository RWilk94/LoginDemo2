import {Component, OnInit} from '@angular/core';
import {User} from "../../models/user";
import {RegisterService} from "../../services/register.service";
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {confirmPasswordValidator, emailValidator, usernameValidator} from "../../validator/validator";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  user: User;
  form: FormGroup;

  //registered: boolean = false;

  constructor(private registerService: RegisterService, private formBuilder: FormBuilder) {
    this.user = new User();
  }

  onSubmit() {
    this.user.username = this.form.get('username').value;
    this.user.email = this.form.get('email').value;
    this.user.password = this.form.get('password').value;
    this.user.confirmPassword = this.form.get('confirmPassword').value;

    //console.log(this.form.get('username').value);
    console.log(this.user);
    //console.log(this.form.controls.confirmPassword);
    //this.form.markAsTouched();
    //console.log(this.user.username);
    this.registerService.sendUser(this.user).subscribe(data => {
        console.log(data);
        //this.registered = true;
        this.user = new User();
      },
      error => console.log(error));
  }

  ngOnInit(): void {
    this.form = this.formBuilder.group({
      username: new FormControl(this.user.username, [
        Validators.required,
        Validators.minLength(3),
        Validators.maxLength(20),
        usernameValidator]),
      email: new FormControl(this.user.email, [
        Validators.required,
        Validators.maxLength(256),
        emailValidator]),
      password: new FormControl(this.user.password, [
        Validators.required,
        Validators.minLength(6),
        Validators.maxLength(20)]),
      confirmPassword: new FormControl(this.user.confirmPassword, [
        Validators.required,
        confirmPasswordValidator])
    });
  }

}
