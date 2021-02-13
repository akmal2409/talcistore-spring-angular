package tech.talci.talcistorespring.util;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.util.MultiValueMap;
import tech.talci.talcistorespring.dto.PageResponse;
import tech.talci.talcistorespring.exceptions.InvalidQueryFilterException;
import tech.talci.talcistorespring.exceptions.TalciStoreException;

import java.util.ArrayList;
import java.util.List;

public final class PaginationUtil {

    public static <T>  PageResponse<T> convertToPageResponse(Page<T> page) {
        PageResponse<T> pageResponse =
                PageResponse.of(page.getContent(), page.getNumber(),
                        page.getTotalElements(), page.getTotalPages());

        return pageResponse;
    }

    public static PageRequest productPageRequest(Integer page, Integer size,
                                                 MultiValueMap<String, String> filters) {
        List<Sort.Order> orders = new ArrayList<Sort.Order>();

        if (filters.get("filter") == null) {
            return PageRequest.of(page, size);
        }

        filters.get("filter").forEach(filter -> {
            String[] query = filter.split(":");

            if (query.length == 2 && isValidFilter(query)) {
                Sort.Order order = new Sort.Order(getDirection(query[1]), query[0]);
                orders.add(order);
            }
        });

        if (orders.size() < 1) {
            return PageRequest.of(page, size);
        }

        return PageRequest.of(page, size, Sort.by(orders));
    }

    private static boolean isValidFilter(String[] query) {
        switch (query[0]) {
            case "pricePerUnit":
            case "rating":
            case "orderCount":
            case "addedOn":
            case "lastUpdated":
            case "shippingCost":
                return true;
            default:
                throw new InvalidQueryFilterException("Filter parameters are invalid");
        }
    }

    private static Sort.Direction getDirection(String value) {
        if (value.equalsIgnoreCase("desc")) {
            return Sort.Direction.DESC;
        } else if (value.equals("asc")) {
            return Sort.Direction.ASC;
        }

        throw new InvalidQueryFilterException("Unknown query filter " + value);
    }
}
