import { Component, OnInit } from '@angular/core';
import { faShippingFast } from '@fortawesome/free-solid-svg-icons';
import { PageResponseModel } from 'src/app/models/page-response.model';
import { ProductModel } from 'src/app/models/product.model';
import { ProductService } from '../product.service';

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.scss'],
})
export class ProductListComponent implements OnInit {
  shippingIcon = faShippingFast;
  products: ProductModel[] = [];

  constructor(private productService: ProductService) {}

  ngOnInit(): void {
    this.fetchProducts();
  }

  fetchProducts(): void {
    this.productService
      .fetchProducts()
      .subscribe((page: PageResponseModel<ProductModel>) => {
        this.products = page.content;
        console.log(this.products);
      });
  }
}
