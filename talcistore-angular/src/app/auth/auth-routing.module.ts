import { VerificationComponent } from './verification/verification.component';
import { SignupComponent } from './signup/signup.component';
import { LogggedInGuard } from './guards/loggedin.guard';
import { LoginComponent } from './login/login.component';
import { Routes, RouterModule } from '@angular/router';
import { NgModule } from '@angular/core';

const routes: Routes = [
  {
    path: 'login',
    component: LoginComponent,
    canActivate: [LogggedInGuard],
  },
  {
    path: 'sign-up',
    component: SignupComponent,
    canActivate: [LogggedInGuard],
  },
  { path: 'verify-account/:token', component: VerificationComponent },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class AuthRoutingModule {}
