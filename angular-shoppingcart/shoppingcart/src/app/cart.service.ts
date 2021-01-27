import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { HttpHeaders } from '@angular/common/http';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { CartItems } from './cart-items';
import { CartItem } from './cart-item';

@Injectable({
  providedIn: 'root',
})
export class CartService {
  private cartUrl = 'http://localhost:8082';
  private cartuser: any;

  constructor(private httpClient: HttpClient) {}

  getCartItems(): Observable<HttpResponse<CartItems>> {
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

    return this.httpClient.get<CartItems>(this.cartUrl, {
      headers: headers,
      observe: 'response',
    });
  }

  addToCart(cartItem: CartItem): Observable<HttpResponse<any>> {
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

    return this.httpClient.post<any>(this.cartUrl, cartItem, {
      headers: headers,
      observe: 'response',
    });
  }

  deleteFromCart(cartItem: CartItem): Observable<HttpResponse<any>> {
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

    return this.httpClient.post<any>(`${this.cartUrl}/delete`, cartItem, {
      headers: headers,
      observe: 'response',
    });
  }
}
