package com.wangfj.product.demo.persistence;

import java.util.List;

import com.wangfj.core.framework.base.page.Page;
import com.wangfj.core.framework.base.persistence.BaseMapper;
import com.wangfj.product.demo.domain.dto.GetStudentsDto;
import com.wangfj.product.demo.domain.dto.SelectStudentPageDto;
import com.wangfj.product.demo.domain.dto.StudentsDto;
import com.wangfj.product.demo.domain.entity.Students;


public interface StudentsMapper extends BaseMapper<Students>{
	/**
	 * 更新学生信息
	 * @Methods Name updateUser
	 * @Create In 2014年10月8日 By wangfei
	 * @param userDto
	 * @return void
	 */
	void updateStudent(StudentsDto studentDto);
	/**
	 * 保存学生信息
	 * @Methods Name saveStudent
	 * @Create In 2015年7月1日 By wuxiong
	 * @param studentDto void
	 */
	void saveStudent(StudentsDto studentDto);
	/**
	 * 
	 * @Methods Name getStudent
	 * @Create In 2015年7月2日 By wuxiong
	 * @param dto
	 * @return StudentsDto
	 */
	StudentsDto getStudent(GetStudentsDto dto);
	/**
	 * 
	 * @Methods Name selectStudentPage
	 * @Create In 2015年7月2日 By wuxiong
	 * @param studentDto
	 * @param page
	 * @return Page<StudentsDto>
	 */
	Page<StudentsDto> selectStudentPage(SelectStudentPageDto studentDto,Page<StudentsDto> page);
	
	List<Students> selectListByParam();
}
