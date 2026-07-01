package com.gowda.productcatalogservice2026.dtos;

import java.util.List;

public class SearchRequestDTO {
        private String query;
        private Integer pageNo;
        private Integer pageSize;
        private List<SortParam> sortParams;

        public String getQuery() {
            return query;
        }

        public void setQuery(String query) {
            this.query = query;
        }

        public Integer getPageNo() {
            return pageNo;
        }

        public void setPageNo(Integer pageNo) {
            this.pageNo = pageNo;
        }

        public Integer getPageSize() {
            return pageSize;
        }

        public void setPageSize(Integer pageSize) {
            this.pageSize = pageSize;
        }

        public List<SortParam> getSortParams() {
            return sortParams;
        }

        public void setSortParams(List<SortParam> sortParams) {
            this.sortParams = sortParams;
        }
    }

