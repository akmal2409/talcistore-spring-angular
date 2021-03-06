import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { CategoryModel } from '../models/category.model';

@Injectable({
  providedIn: 'root',
})
export class CategoryService {
  constructor(private http: HttpClient) {}

  fetchCategories(): Observable<any> {
    return this.http.get<CategoryModel[]>(
      `${environment.apiUrl}/api/categories`
    );
  }

  fetchCategoryById(id: number): Observable<CategoryModel> {
    return this.http.get<CategoryModel>(
      `${environment.apiUrl}/api/categories/` + id
    );
  }
}
