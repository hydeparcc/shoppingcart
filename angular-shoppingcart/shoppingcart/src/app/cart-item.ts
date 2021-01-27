export class CartItem {
  productId: number;
  productName: string;
  desc: string;
  price: number;
  quantity: number;

  constructor(
    productId: number,
    productName: string,
    price: number,
    quantity: number,
    desc: string = ''
  ) {
    this.productId = productId;
    this.productName = productName;
    this.desc = desc;
    this.price = price;
    this.quantity = quantity;
  }
}
