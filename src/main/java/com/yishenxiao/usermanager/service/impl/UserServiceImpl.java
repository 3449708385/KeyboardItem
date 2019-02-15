package com.yishenxiao.usermanager.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.yishenxiao.usermanager.beans.User;
import com.yishenxiao.usermanager.dao.UserMapper;
import com.yishenxiao.usermanager.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired(required = false)
	@Qualifier("userMapper")
	private UserMapper userMapper;
	
	@Override
	public List<User> queryByPhone(String phone) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("phone", phone);
		return userMapper.queryByPhone(map);
	}

	@Override
	public int updateUser(User user) {
		return userMapper.updateByPrimaryKeySelective(user);
	}

	@Override
	public List<User> queryByPhoneAndPasswd(String phone) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("phone", phone);
		return userMapper.queryByPhoneAndPasswd(map);
	}

	@Override
	public List<User> queryByaccount(String userStrId) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userStrId", userStrId);
		return userMapper.queryByaccount(map);
	}

	@Override
	public int insertOneData(User user) {
		return userMapper.insert(user);
	}

	@Override
	public int updateUserInvitationCodeCount(Long userId) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userId", userId);
		return userMapper.updateUserInvitationCodeCount(map);
	}

	@Override
	public List<User> queryByUserId(Long userId) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userId", userId);
		return userMapper.queryByUserId(map);
	}

	@Override
	public int userUpdateNickName(String nickname, Long id) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("nickname", nickname);
		map.put("id", id);
		return userMapper.userUpdateNickName(map);
	}

	@Override
	public int updateLoginstate(Long userId, int state) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("state", state);
		map.put("userId", userId);
		return userMapper.updateLoginstate(map);
	}

}
