package tech.talci.talcistorespring.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class DiscountDto {

    private Long id;
    private Integer percent;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate validFrom;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate validUntil;
    private Long productId;
}
