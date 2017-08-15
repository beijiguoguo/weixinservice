package com.guoguo.mvc.service.mpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.guoguo.mvc.dao.UserTMapper;
import com.guoguo.mvc.model.UserT;
import com.guoguo.mvc.service.UserService;
@Service("userService") 
public class UserServiceImpl implements UserService {

	@Resource  
    private UserTMapper userDao;  
	
	public UserT getUserById(int userId) {
		 return this.userDao.selectByPrimaryKey(userId); 
	}

}
