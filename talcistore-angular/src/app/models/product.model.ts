export class ProductModel {
  id?: number;
  addedOn: Date;
  categoryId?: number;
  countryofOrigin: string;
  description: string;
  lastUpdated?: Date;
  leftInStock: number;
  orderCount?: number;
  pricePerUnit: number;
  producer: string;
  productName: string;
  rating?: number;
  sellerId?: number;
  imgUrl: string;
  shippingCost: number;
}
