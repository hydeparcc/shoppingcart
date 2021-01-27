import { Component, OnInit, Input } from '@angular/core';
import { ProductsService } from '../products.service';
import { SubscribeService } from '../subscribe.service';
import { Product } from '../product';
import { CartItem } from '../cart-item';
import { CartService } from '../cart.service';

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.css'],
})
export class ProductsComponent implements OnInit {
  @Input() productList: Product[] = [];

  constructor(
    private productsService: ProductsService,
    private subscribeService: SubscribeService,
    private cartService: CartService
  ) {}

  ngOnInit(): void {
    this.productsService.getProducts().subscribe((resp: any) => {
      if (resp.status === 200 && resp.body !== null) {
        this.productList = resp.body.products;
      }
    });
  }

  addProduct(product: Product) {
    var cartItem: CartItem = new CartItem(
      product.id,
      product.name,
      product.price,
      product.quantity
    );

    if (product.quantity > 0) {
      this.cartService.addToCart(cartItem).subscribe((resp) => {
        if (resp.status === 200 && resp.body !== null) {
          this.subscribeService.sendMessage(resp.body);
        }
      });
    }
  }

  removeProduct(product: Product) {
    var cartItem: CartItem = new CartItem(
      product.id,
      product.name,
      product.price,
      product.quantity
    );
    if (product.quantity > 0) {
      this.cartService.deleteFromCart(cartItem).subscribe((resp) => {
        this.subscribeService.sendMessage(resp.body);
      });
    }
  }
}
