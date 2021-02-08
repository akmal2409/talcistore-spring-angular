import { BrowserModule, HammerModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { NgxWebstorageModule } from 'ngx-webstorage';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { HeaderComponent } from './main/header/header.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MaterialModule } from './material.module';
import { HomeComponent } from './main/home/home.component';
import { LandingComponent } from './main/home/landing/landing.component';
import { SignupComponent } from './auth/signup/signup.component';
import { LoginComponent } from './auth/login/login.component';
import { VerificationComponent } from './auth/verification/verification.component';
import { ToastrModule } from 'ngx-toastr';
import { CapitalizePipe } from './shared/capitalize.pipe';
import { ProductListComponent } from './main/products/product-list/product-list.component';
import { NgRatingModule } from 'd-ng-rating';
import { MatCarouselModule } from '@ngbmodule/material-carousel';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    HomeComponent,
    LandingComponent,
    SignupComponent,
    LoginComponent,
    VerificationComponent,
    CapitalizePipe,
    ProductListComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    FormsModule,
    HttpClientModule,
    NgxWebstorageModule.forRoot(),
    FontAwesomeModule,
    BrowserAnimationsModule,
    MaterialModule,
    ToastrModule.forRoot({
      positionClass: 'toast-bottom-right',
    }),
    HammerModule,
    NgRatingModule,
    MatCarouselModule.forRoot(),
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
