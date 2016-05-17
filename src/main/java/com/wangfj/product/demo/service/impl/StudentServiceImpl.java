package com.wangfj.product.demo.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wangfj.core.constants.ComErrorCodeConstants;
import com.wangfj.core.framework.base.page.Page;
import com.wangfj.core.framework.exception.BleException;
import com.wangfj.product.demo.domain.dto.GetStudentsDto;
import com.wangfj.product.demo.domain.dto.SelectStudentPageDto;
import com.wangfj.product.demo.domain.dto.StudentsDto;
import com.wangfj.product.demo.domain.dto.UpdateStudentsDto;
import com.wangfj.product.demo.domain.entity.Students;
import com.wangfj.product.demo.persistence.StudentsMapper;
import com.wangfj.product.demo.service.intf.StudentService;

@Service
public class StudentServiceImpl implements StudentService {
	 private static final Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);
	 
	 @Autowired
		private StudentsMapper studentsMapper;
	
	 /*
	  * (non-Javadoc)
	  * @see com.wangfj.product.demo.service.intf.StudentService#saveStudent(com.wangfj.product.demo.domain.dto.StudentsDto)
	  */
	public void saveStudent(StudentsDto studentDto) {
		logger.info("start saveStudent(),param:"+studentDto.toString());
		Students student=new Students();
		BeanUtils.copyProperties(studentDto, student);
		Integer result = studentsMapper.insert(student);
		if(result.intValue()!=1){
			logger.error("");//此处输出逻辑错误
			throw new BleException(ComErrorCodeConstants.ErrorCode.DATA_OPER_ERROR.getErrorCode(),
					ComErrorCodeConstants.ErrorCode.DATA_OPER_ERROR.getMemo());
		}
		logger.info("end saveUser()");
	}
	/*
	 * (non-Javadoc)
	 * @see com.wangfj.product.demo.service.intf.StudentService#updateStudent(com.wangfj.product.demo.domain.dto.UpdateStudentsDto)
	 */
	@Override
	public void updateStudent(UpdateStudentsDto studentsDto) {
		logger.info("start updateStudent(),param:"+studentsDto.toString());
		Students students=new Students();
		BeanUtils.copyProperties(studentsDto, students);
		int update=studentsMapper.update(students);
		if(update == 0){
			logger.error("修改学生信息错误");
			throw new BleException(ComErrorCodeConstants.ErrorCode.DATA_OPER_ERROR.getErrorCode(),
					ComErrorCodeConstants.ErrorCode.DATA_OPER_ERROR.getMemo());
		}
		logger.info("end updateStudent()");
	}
	/*
	 * (non-Javadoc)
	 * @see com.wangfj.product.demo.service.intf.StudentService#getStudent(com.wangfj.product.demo.domain.dto.GetStudentsDto)
	 */
	@Override
	public StudentsDto getStudent(GetStudentsDto dto) {
		logger.info("start getStudent(),param:"+dto.toString());
		Long sid = (long)dto.getSid();
		Students students = studentsMapper.get(sid);
		if (students==null) {
			logger.error("得到学生信息错误");
			throw new BleException(ComErrorCodeConstants.ErrorCode.DATA_EMPTY_ERROR.getErrorCode(),
					ComErrorCodeConstants.ErrorCode.DATA_EMPTY_ERROR.getMemo());
		}
		StudentsDto dto2=new StudentsDto();
		BeanUtils.copyProperties(students, dto2);
		logger.info("end getStudent()");
		return dto2;
	}
	/*
	 * (non-Javadoc)
	 * @see com.wangfj.product.demo.service.intf.StudentService#selectStudentPage(com.wangfj.product.demo.domain.dto.SelectStudentPageDto, com.wangfj.core.framework.base.page.Page)
	 */
	@Override
	public Page<StudentsDto> selectStudentPage(SelectStudentPageDto studentDto,
			Page<StudentsDto> page) {
		logger.info("start selectStudentPage(),param:"+studentDto.toString()+page.toString());
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("name", studentDto.getName());
		//查询总数量
		Integer count = studentsMapper.getCountByParam(paramMap);
		page.setCount(count);
		//查询记录
		paramMap.put("start", page.getStart());
		paramMap.put("limit", page.getLimit());
		List<Students> list = studentsMapper.selectPageListByParam(paramMap);
		if (!list.isEmpty()) {
			List<StudentsDto> studentList=new ArrayList<StudentsDto>();
			for (Students student : list) {
				StudentsDto dto= new StudentsDto();
				BeanUtils.copyProperties(student, dto);
				studentList.add(dto);
			}
			page.setList(studentList);
			return page;
		}else {
			throw new BleException(ComErrorCodeConstants.ErrorCode.DATA_EMPTY_ERROR.getErrorCode(),
					ComErrorCodeConstants.ErrorCode.DATA_EMPTY_ERROR.getMemo());
		}
		
	}
	@Override
	public List<StudentsDto> selectListByParam() {
		List<Students> list = studentsMapper.selectListByParam();
		if(!list.isEmpty()){
			List<StudentsDto> studentList=new ArrayList<StudentsDto>();
			for (Students student : list) {
				StudentsDto dto= new StudentsDto();
				BeanUtils.copyProperties(student, dto);
				studentList.add(dto);
			}
			return studentList;
		}else {
			throw new BleException(ComErrorCodeConstants.ErrorCode.DATA_EMPTY_ERROR.getErrorCode(),
					ComErrorCodeConstants.ErrorCode.DATA_EMPTY_ERROR.getMemo());
		
		
	}
	 
	
	}
	
}
