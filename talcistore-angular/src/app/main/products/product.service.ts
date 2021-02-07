import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { PageResponseModel } from 'src/app/models/page-response.model';
import { ProductModel } from 'src/app/models/product.model';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root',
})
export class ProductService {
  constructor(private http: HttpClient) {}

  fetchProducts(): Observable<any> {
    return this.http.get<PageResponseModel<ProductModel>>(
      `${environment.apiUrl}/api/products`
    );
  }
}
