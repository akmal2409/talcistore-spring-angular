import { Component, Input, OnInit } from '@angular/core';
import { faShippingFast } from '@fortawesome/free-solid-svg-icons';
import { ProductModel } from 'src/app/models/product.model';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.scss'],
})
export class ProductComponent implements OnInit {
  @Input() product: ProductModel;
  shippingIcon = faShippingFast;
  constructor() {}

  ngOnInit(): void {}

  getShipping(): string {
    if (this.product.shippingCost === null || this.product.shippingCost < 1) {
      return 'Free Shipping';
    } else {
      return 'Shipping ' + this.product.shippingCost.toFixed(2);
    }
  }

  getShippingClass(): string {
    if (this.product.shippingCost === null || this.product.shippingCost < 1) {
      return 'text-indigo-600';
    } else {
      return 'text-red-600';
    }
  }

  getImage(): string {
    if (this.product.imgUrl === null || this.product.imgUrl.length < 1) {
      return 'https://www.uejecutivos.cl/img/nophoto.png';
    } else {
      return this.product.imgUrl;
    }
  }

  getRating(): number {
    if (this.product.rating !== null) {
      return Math.round(this.product.rating);
    } else {
      return 0;
    }
  }
}
