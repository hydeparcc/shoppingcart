import { Product } from './product';

export class Products {
  products: Product[];
  constructor(products: Product[]) {
    this.products = products;
  }
}
