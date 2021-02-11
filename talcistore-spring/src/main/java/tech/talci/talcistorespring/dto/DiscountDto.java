package tech.talci.talcistorespring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class DiscountDto {

    private Long id;
    private Integer percent;
    @DateTimeFormat(pattern = "dd/MM/YYYY")
    private LocalDate from;
    @DateTimeFormat(pattern = "dd/MM/YYYY")
    private LocalDate till;
    private Long productId;
}
