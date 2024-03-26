package com.nagarro.miniassignment.payloads;

import java.util.List;

import com.nagarro.miniassignment.models.UserData;

public class UsersPageInfo {

	
	private List<UserData> userList;
    private PageInfo pageInfo;
	public List<UserData> getUserList() {
		return userList;
	}
	public void setUserList(List<UserData> userList) {
		this.userList = userList;
	}
	public PageInfo getPageInfo() {
		return pageInfo;
	}
	public void setPageInfo(PageInfo pageInfo) {
		this.pageInfo = pageInfo;
	}
    
}
