package com.productCriteria.Dtos;

import com.productCriteria.Entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaginationResponse {

    private List<Product> content;
    private boolean last;
    private Integer totalPage;
    private Long totalElement;
    private Integer pageSize;
    private Integer pageNumber;

    public static PaginationResponse get(Page<Product> page, List<Product> content){
        return PaginationResponse.builder()
                .last(page.isLast())
                .content(content)
                .totalPage(page.getTotalPages())
                .totalElement(page.getTotalElements())
                .pageSize(page.getSize())
                .pageNumber(page.getNumber())
                .build();
    }
}
