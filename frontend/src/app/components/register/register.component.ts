import {Component, OnInit} from '@angular/core';
import {User} from "../../models/user";
import {RegisterService} from "../../services/register.service";
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {zipcodeValidator} from "../../validator/validator";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  newUser: User;
  form: FormGroup;

  //registered: boolean = false;

  constructor(private registerService: RegisterService, private fb: FormBuilder) {
    this.newUser = new User();
  }

  onSubmit() {
    console.log(this.form.controls.rePassword);
    /*this.registerService.sendUser(this.newUser).subscribe(data => {
        console.log(data);
        this.registered = true;
        this.newUser = new User();
      },
      error => console.log(error));*/
  }

  ngOnInit(): void {
    this.form = this.fb.group({
      username: ['', Validators.required],
      email: '',
      password: '',
      rePassword: ['', zipcodeValidator]
    });

    this.form.controls.password.valueChanges.subscribe(
      x => this.form.controls.rePassword.updateValueAndValidity()
    )
  }

}
