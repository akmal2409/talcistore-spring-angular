import { HttpClient, HttpParams } from '@angular/common/http';
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

  fetchProducts(pageIndex: number, pageSize: number): Observable<any> {
    const params = this.getPaginationParams(pageIndex, pageSize);
    return this.http.get<PageResponseModel<ProductModel>>(
      `${environment.apiUrl}/api/products`,
      {
        params: params,
      }
    );
  }

  fetchProductsByCategory(
    pageIndex: number,
    pageSize: number,
    categoryId: number,
    filter: string
  ): Observable<any> {
    let params = this.getPaginationParams(pageIndex, pageSize);

    if (filter !== null) {
      params = params.append('filter', filter);
    }

    return this.http.get<PageResponseModel<ProductModel>>(
      `${environment.apiUrl}/api/products/by-category/` + categoryId,
      {
        params: params,
      }
    );
  }

  fetchTopRated(pageIndex: number, pageSize: number): Observable<any> {
    let params = this.getPaginationParams(pageIndex, pageSize);
    params = params.append('filter', 'rating:desc');

    return this.http.get<ProductModel[]>(
      `${environment.apiUrl}/api/products/`,
      {
        params: params,
      }
    );
  }

  getPaginationParams(pageIndex: number, pageSize: number): HttpParams {
    let params = new HttpParams();
    params = params.append('page', pageIndex.toString());
    params = params.append('size', pageSize.toString());
    return params;
  }

  searchValue(value: string): Observable<any> {
    return this.http.get<{ options: [] }>(
      `${environment.apiUrl}/api/products/get-search-options`,
      {
        params: new HttpParams().set('text', value),
      }
    );
  }
}
