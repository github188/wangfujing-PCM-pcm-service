package com.wangfj.product.demo.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.wangfj.core.cache.RedisCacheGet;
import com.wangfj.core.cache.RedisVo;
import com.wangfj.core.constants.ComErrorCodeConstants;
import com.wangfj.core.framework.base.page.Page;
import com.wangfj.core.framework.exception.BleException;
import com.wangfj.core.utils.CacheUtils;
import com.wangfj.core.utils.JsonUtil;
import com.wangfj.product.demo.domain.dto.GetUsersDto;
import com.wangfj.product.demo.domain.dto.SelectUserPageDto;
import com.wangfj.product.demo.domain.dto.UpdateUsersDto;
import com.wangfj.product.demo.domain.dto.UsersDto;
import com.wangfj.product.demo.domain.entity.Users;
import com.wangfj.product.demo.persistence.UsersMapper;
import com.wangfj.product.demo.service.intf.UserService;

@Service
public class UserServiceImpl  implements UserService{
    
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UsersMapper usersMapper;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.bailian.product.goods.service.intf.UserService#saveUser(com.bailian
	 * .product.goods.domain.vo.UsersDTO)
	 */
	public void saveUser(UsersDto userDto) {
		logger.info("start saveUser(),param:"+userDto.toString());
		Users user = new Users();
		BeanUtils.copyProperties(userDto, user);
		Integer result = usersMapper.insert(user);
		if(result.intValue()!=1){
			logger.error("");//此处输出逻辑错误
			throw new BleException(ComErrorCodeConstants.ErrorCode.DATA_OPER_ERROR.getErrorCode(),
					ComErrorCodeConstants.ErrorCode.DATA_OPER_ERROR.getMemo());
		}
		
		
		RedisVo vo = new RedisVo();
		vo.setValue(JsonUtil.getJSONString(user));
		vo.setKey("kong");
		vo.setField("pcm");
		vo.setType(CacheUtils.HSET);
		CacheUtils.setRedisData(vo);
		logger.info("end saveUser()");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.bailian.product.goods.service.intf.UserService#updateUser(com.bailian
	 * .product.goods.domain.vo.UsersDTO)
	 */
	@Cacheable(value = { "" })
	public void updateUser(UpdateUsersDto userDto){
		Users user = new Users();
		BeanUtils.copyProperties(userDto, user);
		int update = usersMapper.update(user);
		if (update == 0) {
			throw new BleException(ComErrorCodeConstants.ErrorCode.DATA_OPER_ERROR.getErrorCode(),
					ComErrorCodeConstants.ErrorCode.DATA_OPER_ERROR.getMemo());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bailian.product.goods.service.intf.UserService#getUser(long)
	 */
	@RedisCacheGet(redisName="pcm", returnObj="com.wangfj.product.demo.domain.dto.UsersDto")
	public UsersDto getUser(String key,GetUsersDto dto) {
		Long sid = (long)dto.getSid();
		Users user = usersMapper.get(sid);
		if (user == null) {
			throw new BleException(ComErrorCodeConstants.ErrorCode.DATA_EMPTY_ERROR.getErrorCode(),
					ComErrorCodeConstants.ErrorCode.DATA_EMPTY_ERROR.getMemo());
		}
		UsersDto userDto = new UsersDto();
		BeanUtils.copyProperties(user, userDto);
		return userDto;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.bailian.product.goods.service.intf.UserService#selectUserPage(com
	 * .bailian.product.goods.domain.vo.UsersDTO,
	 * com.bailian.core.framework.base.page.Page)
	 */
	public Page<UsersDto> selectUserPage(SelectUserPageDto pageDto,Page<UsersDto> page) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("name", pageDto.getName());
		//查询总数量
		Integer count = usersMapper.getCountByParam(paramMap);
		page.setCount(count);
		
		paramMap.put("start", page.getStart());
		paramMap.put("limit", page.getLimit());
		
		List<Users> list = usersMapper.selectPageListByParam(paramMap);
		if (!list.isEmpty()) {
			List<UsersDto> finalList = new ArrayList<UsersDto>();
			for (Users user:list) {
				UsersDto dto = new UsersDto();
				BeanUtils.copyProperties(user, dto);
				finalList.add(dto);
			}
			page.setList(finalList);
			return page;
		} else {
			throw new BleException(ComErrorCodeConstants.ErrorCode.DATA_EMPTY_ERROR.getErrorCode(),
					ComErrorCodeConstants.ErrorCode.DATA_EMPTY_ERROR.getMemo());
		}

	}
	
	public static void main(String[] args) {
	    logger.info("test");
    }
}
