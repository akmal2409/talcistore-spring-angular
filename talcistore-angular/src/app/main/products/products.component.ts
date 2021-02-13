import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params } from '@angular/router';
import { CategoryModel } from 'src/app/models/category.model';
import { ProductModel } from 'src/app/models/product.model';
import { CategoryService } from 'src/app/services/category.service';
import { ProductService } from './product.service';

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.scss'],
})
export class ProductsComponent implements OnInit {
  categoryMode: boolean;
  searchMode: boolean;
  category?: CategoryModel;
  products: ProductModel[];

  constructor(
    private productService: ProductService,
    private categoryService: CategoryService,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.fetchProducts();
  }

  fetchProducts(): void {
    this.route.params.subscribe((params: Params) => {
      this.categoryMode = params['id'] !== null;
      const categoryId = +params['id'];

      this.categoryService
        .fetchCategoryById(categoryId)
        .subscribe((category: CategoryModel) => {
          this.category = category;
        });
    });

    this.route.queryParams.subscribe((params: Params) => {
      this.searchMode = params['searchQuery'] !== null;
    });
  }
}
