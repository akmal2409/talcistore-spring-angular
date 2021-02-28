import { ImageEditComponent } from './products/image-edit/image-edit.component';
import { ProductEditComponent } from './products/product-edit/product-edit.component';
import { AdminComponent } from './admin.component';
import { AdminGuard } from './../auth/guards/admin.guard';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  {
    path: '',
    component: AdminComponent,
    canActivate: [AdminGuard],
    canActivateChild: [AdminGuard],
    children: [
      { path: 'product-new', component: ProductEditComponent },
      { path: 'product-edit/:id', component: ProductEditComponent },
      { path: 'image-new/:productId', component: ImageEditComponent },
      { path: 'image-edit/:productId', component: ImageEditComponent },
    ],
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class AdminRoutingModule {}
