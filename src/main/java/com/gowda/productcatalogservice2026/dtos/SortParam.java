package com.gowda.productcatalogservice2026.dtos;

public class SortParam {
        private String paramName;
        private SortType sortType;

        public String getParamName() {
            return paramName;
        }

        public void setParamName(String paramName) {
            this.paramName = paramName;
        }

        public SortType getSortType() {
            return sortType;
        }

        public void setSortType(SortType sortType) {
            this.sortType = sortType;
        }
    }

