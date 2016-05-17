package com.wangfj.product.demo.service.intf;

import java.util.List;

import com.wangfj.core.framework.base.page.Page;
import com.wangfj.product.demo.domain.dto.GetStudentsDto;
import com.wangfj.product.demo.domain.dto.SelectStudentPageDto;
import com.wangfj.product.demo.domain.dto.StudentsDto;
import com.wangfj.product.demo.domain.dto.UpdateStudentsDto;

public interface StudentService {
	/**
	 * 保存信息
	 * @Methods Name saveStudent
	 * @Create In 2015年7月1日 By wuxiong
	 * @param studentDto void
	 */
	void saveStudent(StudentsDto studentDto);
	/**
	 * 更改学生信息
	 * @Methods Name updateStudent
	 * @Create In 2015年7月1日 By wuxiong
	 * @param studentsDto void
	 */
	void updateStudent(UpdateStudentsDto studentsDto);
	/**
	 * 获取学生信息
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
	
	List<StudentsDto> selectListByParam();
}
