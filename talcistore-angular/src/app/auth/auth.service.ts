import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { LocalStorageService } from 'ngx-webstorage';
import { Observable } from 'rxjs';
import { AuthResponse } from './login/auth-response';
import { LoginRequestPayload } from './login/login-request-payload';
import { SignupRequestPayload } from './signup/signupRequest.payload';
import { tap } from 'rxjs/operators';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  constructor(
    private http: HttpClient,
    private localStorage: LocalStorageService
  ) {}

  singup(signupRequest: SignupRequestPayload): Observable<any> {
    return this.http.post(
      `${environment.apiUrl}/api/auth/sign-up`,
      signupRequest,
      {
        responseType: 'text',
      }
    );
  }

  verifyAccount(token: string): Observable<any> {
    return this.http.get(
      `${environment.apiUrl}/api/auth/verify-account/` + token,
      {
        responseType: 'text',
      }
    );
  }

  login(loginRequest: LoginRequestPayload): Observable<any> {
    return this.http
      .post<AuthResponse>(`${environment.apiUrl}/api/auth/login`, loginRequest)
      .pipe(
        tap((data: AuthResponse) => {
          this.localStorage.store('expiresAt', data.expiresAt);
          this.localStorage.store('refreshToken', data.refreshToken);
          this.localStorage.store('token', data.token);
          this.localStorage.store('username', data.username);
        })
      );
  }

  refreshToken(): Observable<any> {
    const refreshRequest = {
      refreshToken: this.getRefreshToken(),
      username: this.getUsername(),
    };
    return this.http
      .post<AuthResponse>(
        `${environment.apiUrl}/api/auth/refresh/token`,
        refreshRequest
      )
      .pipe(
        tap((data: AuthResponse) => {
          this.localStorage.store('token', data.token);
        })
      );
  }

  getJwtToken(): string {
    return this.localStorage.retrieve('token');
  }

  getUsername(): string {
    return this.localStorage.retrieve('username');
  }

  getRefreshToken(): string {
    return this.localStorage.retrieve('refreshToken');
  }
}
