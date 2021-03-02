import { ProductsComponent } from './products/products.component';
import { LandingComponent } from './home/landing/landing.component';
import { HomeComponent } from './home/home.component';
import { RouterModule, Routes } from '@angular/router';
import { NgModule } from '@angular/core';

const routes: Routes = [
  {
    path: '',
    component: HomeComponent,
    children: [
      {
        path: 'auth',
        loadChildren: () =>
          import('../auth/auth.module').then((mod) => mod.AuthModule),
      },
      { path: '', component: LandingComponent },
      { path: 'by-category/:id/products', component: ProductsComponent },
    ],
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class HomeRoutingModule {}
