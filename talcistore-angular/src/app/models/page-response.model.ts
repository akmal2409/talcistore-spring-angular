export class PageResponse<T> {
  content: T[];
  currentPage: number;
  totalItems: number;
  totalPages: number;
}
