package br.com.mubook.mubook.utils;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;

public class PageUtils {

    public static <E, D> Page<D> mapPage(Page<E> page, List<D> content) {
        return new PageImpl<>(
                content,
                page.getPageable(),
                page.getTotalElements()
        );
    }
}
