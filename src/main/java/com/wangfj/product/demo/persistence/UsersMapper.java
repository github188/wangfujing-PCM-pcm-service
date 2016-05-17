package com.wangfj.product.demo.persistence;

import com.wangfj.core.framework.base.page.Page;
import com.wangfj.core.framework.base.persistence.BaseMapper;
import com.wangfj.product.demo.domain.dto.GetUsersDto;
import com.wangfj.product.demo.domain.dto.SelectUserPageDto;
import com.wangfj.product.demo.domain.dto.UpdateUsersDto;
import com.wangfj.product.demo.domain.dto.UsersDto;
import com.wangfj.product.demo.domain.entity.Users;

public interface UsersMapper extends BaseMapper<Users>{
	
	/**
	 * 保存用户信息
	 * @Methods Name saveUser
	 * @Create In 2014年10月8日 By wangfei
	 * @param userDto
	 * @return void
	 */
	void saveUser(UsersDto userDto);
	
	/**
	 * 更新用户信息
	 * @Methods Name updateUser
	 * @Create In 2014年10月8日 By wangfei
	 * @param userDto
	 * @return void
	 */
	void updateUser(UpdateUsersDto userDto);
	
	/**
	 * 获取用户信息
	 * @Methods Name getUser
	 * @Create In 2014年10月8日 By wangfei
	 * @param id
	 * @return UsersDTO
	 */
	UsersDto getUser(GetUsersDto dto);
	
	/**
	 * 分页查询用户信息
	 * @Methods Name selectUserPage
	 * @Create In 2014年10月8日 By wangfei
	 * @param userDto
	 * @param page
	 * @return Page<UsersDTO>
	 */
	Page<UsersDto> selectUserPage(SelectUserPageDto userDto,Page<UsersDto> page);
}