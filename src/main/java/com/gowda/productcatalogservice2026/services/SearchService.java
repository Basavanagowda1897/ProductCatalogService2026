package com.gowda.productcatalogservice2026.services;

import com.gowda.productcatalogservice2026.dtos.SortParam;
import com.gowda.productcatalogservice2026.dtos.SortType;
import com.gowda.productcatalogservice2026.models.Product;
import com.gowda.productcatalogservice2026.repositories.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchService implements ISearchService {

    @Autowired
    private ProductRepo productRepo;

    @Override
    public Page<Product> searchProducts(String query,
                                        int pageNo,
                                        int pageSize,
                                        List<SortParam> sortParams) {

        Sort sort = Sort.unsorted();

        if (sortParams != null && !sortParams.isEmpty()) {
            /*
            sortParams = [
                {
                    paramName: "price",
                    sortType: "asc"
                },
                {
                    paramName: "name",
                    sortType: "desc"
                }
            Sort sort = Sort.by("price").ascending().and(Sort.by("name").descending());
             */
            //There's atleast one parameter by which i need to sort
            if (sortParams.get(0).getSortType().equals(SortType.ASC)) {
                sort = Sort.by(sortParams.get(0).getParamName()).ascending();
            } else {
                sort = Sort.by(sortParams.get(0).getParamName()).descending();
            }

            for (int i = 1; i < sortParams.size(); i++) {
                if (sortParams.get(i).getSortType().equals(SortType.ASC)) {
                    sort = sort.and(Sort.by(sortParams.get(i).getParamName()).ascending());
                } else {
                    sort = sort.and(Sort.by(sortParams.get(i).getParamName()).descending());
                }
            }
        }


        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        return productRepo.findByName(query, pageable);
    }
}

