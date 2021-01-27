import { CartItem } from './cart-item';

export class CartItems {
  cartItems: CartItem[];
  constructor(cartItems: CartItem[]) {
    this.cartItems = cartItems;
  }
}
