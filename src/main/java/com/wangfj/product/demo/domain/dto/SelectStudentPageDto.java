package com.wangfj.product.demo.domain.dto;

import com.wangfj.core.framework.base.dto.BaseDto;
/**
 * 
 * @Class Name SelectStudentPageDto
 * @Author wuxiong
 * @Create In 2015年7月2日
 */
public class SelectStudentPageDto extends BaseDto {
		private String name;
		private Integer currentPage;
		private Integer pageSize;
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public Integer getCurrentPage() {
			return currentPage;
		}
		public void setCurrentPage(Integer currentPage) {
			this.currentPage = currentPage;
		}
		public Integer getPageSize() {
			return pageSize;
		}
		public void setPageSize(Integer pageSize) {
			this.pageSize = pageSize;
		}
		
		
}
