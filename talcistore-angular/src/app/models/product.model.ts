import { DiscountModel } from './discount.model';

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
  shippingCost: number;
  discount?: DiscountModel;
}
