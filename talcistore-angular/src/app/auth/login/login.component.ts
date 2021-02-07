import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
})
export class LoginComponent implements OnInit {
  loginForm: FormGroup;
  showPassword: boolean = false;
  showSpinner: boolean = false;

  constructor(
    private router: Router,
    private authService: AuthService,
    private toastr: ToastrService
  ) {}

  ngOnInit(): void {
    this.initForm();
  }

  initForm(): void {
    this.loginForm = new FormGroup({
      username: new FormControl('', Validators.required),
      password: new FormControl('', [Validators.required]),
    });
  }

  onLogin(): void {
    if (this.loginForm.valid) {
      this.showSpinner = true;
      this.authService.login(this.loginForm.value).subscribe(
        () => {
          this.showSpinner = false;
          this.router.navigate(['/']);
          this.toastr.success('You have successfully logged in');
        },
        () => {
          this.showSpinner = false;
          this.toastr.error('Login failed. Try again');
        }
      );
    } else {
      this.toastr.error('Please enter valid credentials');
    }
  }
}
