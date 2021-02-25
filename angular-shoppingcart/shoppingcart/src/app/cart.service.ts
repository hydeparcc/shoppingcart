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
  private cartUrl = 'http://localhost:8080/cart';
  private guestUser: any;

  constructor(private httpClient: HttpClient) {}

  getCartItems(): Observable<HttpResponse<CartItems>> {
    if (localStorage.getItem('_guestuser')) {
      this.guestUser = localStorage.getItem('_guestuser');
    } else {
      this.guestUser = Math.random().toString(36).substr(2, 10);
      localStorage.setItem('_guestuser', this.guestUser);
    }

    const headers = new HttpHeaders({
      'X-Guest-User': this.guestUser,
    });

    return this.httpClient.get<CartItems>(`${this.cartUrl}/list`, {
      headers: headers,
      observe: 'response',
    });
  }

  addToCart(cartItem: CartItem): Observable<HttpResponse<any>> {
    if (localStorage.getItem('_guestuser')) {
      this.guestUser = localStorage.getItem('_guestuser');
    } else {
      this.guestUser = Math.random().toString(36).substr(2, 10);
      localStorage.setItem('_guestuser', this.guestUser);
    }

    const headers = new HttpHeaders({
      'X-Guest-User': this.guestUser,
    });

    return this.httpClient.post<any>(`${this.cartUrl}/add`, cartItem, {
      headers: headers,
      observe: 'response',
    });
  }

  deleteFromCart(cartItem: CartItem): Observable<HttpResponse<any>> {
    if (localStorage.getItem('_guestuser')) {
      this.guestUser = localStorage.getItem('_guestuser');
    } else {
      this.guestUser = Math.random().toString(36).substr(2, 10);
      localStorage.setItem('_guestuser', this.guestUser);
    }

    const headers = new HttpHeaders({
      'X-Guest-User': this.guestUser,
    });

    return this.httpClient.post<any>(`${this.cartUrl}/delete`, cartItem, {
      headers: headers,
      observe: 'response',
    });
  }
}
