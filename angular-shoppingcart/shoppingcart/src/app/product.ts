export class Product {
  id: number;
  name: string;
  desc: string;
  price: number;
  quantity: number;

  constructor(
    id: number,
    name: string,
    desc: string,
    price: number,
    quantity: number = 0
  ) {
    this.id = id;
    this.name = name;
    this.desc = desc;
    this.price = price;
    this.quantity = quantity;
  }
}
