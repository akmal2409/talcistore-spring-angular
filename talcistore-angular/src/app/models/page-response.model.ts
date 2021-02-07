export class PageResponseModel<T> {
  content: T[];
  currentPage: number;
  totalItems: number;
  totalPages: number;
}
