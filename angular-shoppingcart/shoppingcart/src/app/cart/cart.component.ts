import { Component, OnInit } from '@angular/core';
import { CartItem } from '../cart-item';
import { SubscribeService } from '../subscribe.service';
import { CartService } from '../cart.service';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css'],
})
export class CartComponent implements OnInit {
  cartItems: CartItem[] = [];
  cartTotal = 0;

  constructor(
    private cartService: CartService,
    private subscribeService: SubscribeService
  ) {}

  ngOnInit(): void {
    this.loadCartItems();
    this.cartSubscribe();
  }

  cartSubscribe() {
    this.subscribeService.getMessage().subscribe((any) => {
      this.loadCartItems();
    });
  }

  loadCartItems() {
    this.cartService.getCartItems().subscribe((resp) => {
      if (resp.status === 200 && resp.body !== null) {
        this.cartItems = resp.body.cartItems;
        this.calcCartTotal();
      } else {
        this.cartItems = [];
      }
    });
  }

  calcCartTotal() {
    this.cartTotal = 0;
    if (this.cartItems) {
      this.cartItems.forEach((item) => {
        this.cartTotal += item.quantity * item.price;
      });
    }
  }

  addCartItem(cartItem: CartItem) {
    this.cartService.addToCart(cartItem).subscribe((resp) => {});
    this.loadCartItems();
  }
}
