package com.safewind.common.page;

import lombok.Data;

import java.util.Collections;
import java.util.List;

/**
 * @Author: Darven
 * @CreateTime: 2025-05-17  08:26
 * @Description: 分页器
 */
@Data
public class PageResult<T>{
    /**
     * 当前页码
     */
    private Long pageNum = 1L;
    /**
     * 当前页数
     */
    private Long pageSize = 10L;
    /**
     * 总数
     */
    private Long totalSize = 0L;
    /**
     * 总分页
     */
    private Long totalPages = 0L;
    /**
     * 数据
     * */
    private List<T> data= Collections.emptyList();

    public PageResult() {
    }

    public PageResult(Builder<T> builder){
        this.pageNum= builder.pageNum;
        this.pageSize=builder.pageSize;
        this.totalSize=builder.totalSize;
        this.totalPages=builder.totalPages;
        this.data=builder.data;
    }

    public static <T> Builder<T> builder() {
        return new Builder<>();
    }

    public static class Builder<T>{
        private Long pageNum ;
        private Long pageSize ;
        private Long totalSize ;
        private Long totalPages;
        private List<T> data;

        public Builder() {
            this.pageNum = 1L;
            this.pageSize = 10L;
            this.totalSize = 0L;
            this.totalPages=0L;
            this.data = Collections.emptyList();
        }

        public Builder<T> pageNum(Long pageNum) {
            if (pageNum == null || pageNum < 1) {
                throw new IllegalArgumentException("pageNum 必须大于等于 1");
            }
            this.pageNum = pageNum;
            return this;
        }

        public Builder<T> pageSize(Long pageSize) {
            if (pageSize == null || pageSize < 1) {
                throw new IllegalArgumentException("pageSize 必须大于等于 1");
            }
            this.pageSize = pageSize;
            return this;
        }

        public Builder<T> totalSize(Long totalSize){
            if (totalSize == null || totalSize<=0) {
                totalSize = 0L;
            }
            this.totalSize = totalSize;
            calculateTotalPages();
            return this;
        }

        public Builder<T> totalPages(Long totalPages){
            if (totalPages == null || totalPages<=0) {
                totalPages = 0L;
            }
            this.totalPages = totalPages;
            return this;
        }

        public Builder<T> data(List<T> data) {
            if (data != null) {
                this.data = data;
            }
            return this;
        }

        public PageResult<T> build(){
            // 确保所有必要字段都被正确设置
            if (pageNum == null) {
                pageNum = 1L;
            }
            if (pageSize == null) {
                pageSize = 10L;
            }
            if (totalSize == null) {
                totalSize = 0L;
            }
            // 重新计算总分页数，以防参数被单独修改
            calculateTotalPages();
            return new PageResult<>(this);
        }

        private void calculateTotalPages() {
            if (this.pageSize != null && this.pageSize > 0) {
                this.totalPages = (this.totalSize / this.pageSize) + (this.totalSize % this.pageSize != 0 ? 1 : 0);
            } else {
                this.totalPages = 0L;
            }
        }
    }
}
