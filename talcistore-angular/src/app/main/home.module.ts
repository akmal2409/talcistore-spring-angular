import { MatCarouselModule } from '@ngbmodule/material-carousel';
import { ReactiveFormsModule } from '@angular/forms';
import { NgRatingModule } from 'd-ng-rating';
import { SharedModule } from './../shared/shared.module';
import { ProductsComponent } from './products/products.component';
import { ProductComponent } from './products/product/product.component';
import { ProductListComponent } from './products/product-list/product-list.component';
import { LandingComponent } from './home/landing/landing.component';
import { HomeComponent } from './home/home.component';
import { NgModule } from '@angular/core';
import { HomeRoutingModule } from './home-routing.module';
import { UIModule } from '../shared/ui.module';

@NgModule({
  declarations: [
    HomeComponent,
    LandingComponent,
    ProductListComponent,
    ProductComponent,
    ProductsComponent,
  ],
  imports: [
    SharedModule,
    NgRatingModule,
    HomeRoutingModule,
    ReactiveFormsModule,
    MatCarouselModule.forRoot(),
    UIModule,
  ],
  exports: [],
})
export class HomeModule {}
