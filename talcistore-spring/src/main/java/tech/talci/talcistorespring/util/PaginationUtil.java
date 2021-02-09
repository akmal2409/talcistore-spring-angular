package tech.talci.talcistorespring.util;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import tech.talci.talcistorespring.dto.PageResponse;

public final class PaginationUtil {

    public static <T>  PageResponse<T> convertToPageResponse(Page<T> page) {
        PageResponse<T> pageResponse =
                PageResponse.of(page.getContent(), page.getNumber(),
                        page.getTotalElements(), page.getTotalPages());

        return pageResponse;
    }

    public static PageRequest productPageRequest(Integer page, Integer size,
                                        boolean sortByPrice, boolean desc) {
        PageRequest pageRequest = PageRequest.of(page, size);

        if (sortByPrice) {
            pageRequest = PageRequest.of(page, size, Sort.by("pricePerUnit"));
        }

        if (desc) {
            pageRequest = PageRequest.of(page, size, Sort.by("pricePerUnit").descending());
        }

        return pageRequest;
    }

    public static PageRequest productPageRequestByRating(Integer page, Integer size) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by("rating").descending());

        return pageRequest;
    }
}
