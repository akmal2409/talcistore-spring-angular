package tech.talci.talcistorespring.util;

import org.springframework.data.domain.Page;
import tech.talci.talcistorespring.dto.PageResponse;

public final class PaginationConverter {

    public static <T>  PageResponse<T> convertToPageResponse(Page<T> page) {
        PageResponse<T> pageResponse =
                PageResponse.of(page.getContent(), page.getNumber(),
                        page.getTotalElements(), page.getTotalPages());

        return pageResponse;
    }
}
