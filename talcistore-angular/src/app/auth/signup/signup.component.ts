import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { faUserPlus } from '@fortawesome/free-solid-svg-icons';
import { ToastrService } from 'ngx-toastr';
import { Observable } from 'rxjs';
import { AuthService } from '../auth.service';
import { of } from 'rxjs';
import { debounceTime, map, switchMap } from 'rxjs/operators';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.scss'],
})
export class SignupComponent implements OnInit {
  signUpIcon = faUserPlus;
  signupForm: FormGroup;
  showPassword: boolean = false;
  error = null;
  showSpinner: boolean = false;

  constructor(
    private authService: AuthService,
    private router: Router,
    private toastr: ToastrService,
    private http: HttpClient
  ) {}

  ngOnInit(): void {
    this.initForm();
  }

  initForm(): void {
    this.signupForm = new FormGroup({
      username: new FormControl(
        '',
        [Validators.required],
        this.usernameAsyncValidator.bind(this)
      ),
      email: new FormControl(
        '',
        [Validators.required, Validators.email],
        this.emailAsyncValidator.bind(this)
      ),
      password: new FormControl('', [Validators.required]),
    });
  }

  onSignup(): void {
    if (this.signupForm.valid) {
      this.showSpinner = true;
      this.authService.singup(this.signupForm.value).subscribe(
        (response) => {
          this.showSpinner = false;
          this.toastr.success(response);
          this.router.navigate(['/']);
          this.toastr.success("Don't forget to activate your account");
        },
        (error) => {
          this.error = error.message;
          this.toastr.error('There was an error when creating your account!');
        }
      );
    } else {
      this.toastr.error('Please enter valid data');
    }
  }

  usernameAsyncValidator(
    control: FormControl
  ): Observable<{ usernameTaken: boolean } | null> {
    if (!control.value) {
      return of(null);
    }
    return of(control.value).pipe(
      debounceTime(500),
      switchMap((username) => {
        return this.authService.checkUsernameValidity(username);
      })
    );
  }

  emailAsyncValidator(
    control: FormControl
  ): Observable<{ emailTaken: boolean } | null> {
    if (!control.value) {
      return of(null);
    }
    return of(control.value).pipe(
      debounceTime(500),
      switchMap((email) => {
        return this.authService.checkEmailValidity(email);
      })
    );
  }
}
