import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ProductImageModel } from 'src/app/models/product-image.model';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root',
})
export class ImageService {
  constructor(private http: HttpClient) {}

  fetchImages(productId: number): Observable<ProductImageModel[]> {
    return this.http.get<ProductImageModel[]>(
      `${environment.apiUrl}/api/images/product/` + productId
    );
  }
}
