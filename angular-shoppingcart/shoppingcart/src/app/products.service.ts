import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { Products } from './products';

@Injectable({
  providedIn: 'root',
})
export class ProductsService {
  private productListUrl = 'http://localhost:8080/product/list';
  private guestUser: any;

  constructor(private httpClient: HttpClient) {}

  getProducts(): Observable<any> {
    if (localStorage.getItem('_guestuser')) {
      this.guestUser = localStorage.getItem('_guestuser');
    } else {
      this.guestUser = Math.random().toString(36).substr(2, 10);
      localStorage.setItem('_guestuser', this.guestUser);
    }

    const headers = new HttpHeaders({
      'X-Guest-User': this.guestUser,
    });

    return this.httpClient.get(this.productListUrl, {
      headers: headers,
      observe: 'response',
    });
  }
}
