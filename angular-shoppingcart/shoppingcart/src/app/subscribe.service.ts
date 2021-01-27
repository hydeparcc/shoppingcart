import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';
import { CartItem } from './cart-item';

@Injectable({
  providedIn: 'root',
})
export class SubscribeService {
  subject = new Subject();
  constructor() {}

  getMessage() {
    return this.subject.asObservable();
  }

  sendMessage(cartItem: CartItem) {
    this.subject.next(cartItem);
  }
}
