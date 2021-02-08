import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LogggedInGuard } from './auth/guards/loggedin.guard';
import { LoginComponent } from './auth/login/login.component';
import { SignupComponent } from './auth/signup/signup.component';
import { VerificationComponent } from './auth/verification/verification.component';
import { HomeComponent } from './main/home/home.component';
import { LandingComponent } from './main/home/landing/landing.component';

const routes: Routes = [
  {
    path: '',
    component: HomeComponent,
    children: [
      { path: '', component: LandingComponent },
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
    ],
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
