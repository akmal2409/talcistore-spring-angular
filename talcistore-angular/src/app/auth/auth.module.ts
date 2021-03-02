import { UIModule } from './../shared/ui.module';
import { AuthRoutingModule } from './auth-routing.module';
import { VerificationComponent } from './verification/verification.component';
import { ReactiveFormsModule } from '@angular/forms';
import { SharedModule } from './../shared/shared.module';
import { LoginComponent } from './login/login.component';
import { SignupComponent } from './signup/signup.component';
import { NgModule } from '@angular/core';

@NgModule({
  declarations: [SignupComponent, LoginComponent, VerificationComponent],
  imports: [SharedModule, ReactiveFormsModule, AuthRoutingModule, UIModule],
  exports: [],
})
export class AuthModule {}
