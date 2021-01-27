import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { HttpHeaders } from '@angular/common/http';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { Products } from './products';

@Injectable({
  providedIn: 'root',
})
export class ProductsService {
  private productListUrl = 'http://localhost:8081';
  private cartuser: any;

  constructor(private httpClient: HttpClient) {}

  getProducts(): Observable<HttpResponse<Products>> {
    if (localStorage.getItem('cartuser')) {
      this.cartuser = localStorage.getItem('cartuser');
    } else {
      this.cartuser = Math.random().toString(36).substr(2, 10);
      localStorage.setItem('cartuser', this.cartuser);
    }

    let headers = new HttpHeaders({
      'Content-Type': 'application/json; charset=utf-8',
      'Access-Control-Allow-Origin': 'http://localhost:4200',
      'Access-Control-Allow-Credentials': 'true',
      sessionid: this.cartuser,
    });

    return this.httpClient.get<Products>(this.productListUrl, {
      headers: headers,
      observe: 'response',
    });
  }
}
