import { Component, OnInit } from '@angular/core';
import { CartService } from '../cart.service';

import { CartItem } from '../cart-item';

@Component({
  selector: 'app-confirmation',
  templateUrl: './confirmation.component.html',
  styleUrls: ['./confirmation.component.css'],
})
export class ConfirmationComponent implements OnInit {
  cartItems: CartItem[] = [];
  cartTotal: number = 0;

  constructor(private cartService: CartService) {}

  ngOnInit(): void {
    this.cartService.getCartItems().subscribe((resp) => {
      console.log(resp.body);
      if (resp.status === 200 && resp.body !== null) {
        this.cartItems = resp.body.cartItems;
        this.calcCartTotal();
      }
    });
  }

  calcCartTotal() {
    if (this.cartItems) {
      this.cartItems.forEach((item) => {
        this.cartTotal += item.quantity * item.price;
      });
    }
  }
}
