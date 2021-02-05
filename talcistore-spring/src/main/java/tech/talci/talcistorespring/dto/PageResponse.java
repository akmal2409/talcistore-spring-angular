package tech.talci.talcistorespring.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
public class PageResponse<T> {
    private List<T> content;
    private int currentPage;
    private long totalItems;
    private int totalPages;

    private PageResponse(List<T> content, int currentPage,
                         long totalItems, int totalPages ) {
        this.content = content;
        this.currentPage = currentPage;
        this.totalItems = totalItems;
        this.totalPages = totalPages;
    }

    public static <T> PageResponse<T> of(List<T> content,
                                         int currentPage, long totalItems,
                                         int totalPages) {
        return new PageResponse<T>(content, currentPage, totalItems, totalPages);
    }
}
