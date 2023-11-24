import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { GeneralService } from '../../services/general.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent {
  constructor(private generalService: GeneralService, private router: Router) {}

  loginForm = new FormGroup({
    email: new FormControl('', [Validators.email, Validators.required]),
    password: new FormControl('', [
      Validators.required,
      Validators.pattern(
        `^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$`
      ),
      Validators.minLength(8),
    ]),
  });

  public get f() {
    return this.loginForm.controls;
  }

  login() {
    let email = this.f.email.value;
    let password = this.f.password.value;
    if (email != null && password != null) {
      // throws error if invalid credentials
      this.generalService.generateToken(email, password).subscribe({
        next: (token) => {
          // sets token to localstorage
          this.generalService.setToken(token);
          if (email != null)
            // get user details by email
            this.generalService.getUser(email).subscribe({
              next: (user) => {
                // set user email and role to localstorage
                this.generalService.setEmail(user.email);
                this.generalService.setRole(user.role);
                // redirect based on role
                if (user.role == 'trainer') this.router.navigate(['/trainer/']);
                else this.router.navigate(['/learner/']);
              },
              error: (err) => {
                console.log(err);
              },
            });
        },
        error: (err) => {
          console.log(err);
        },
      });
    }
  }
}
