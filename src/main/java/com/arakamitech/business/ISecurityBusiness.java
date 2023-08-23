package com.arakamitech.business;

public interface ISecurityBusiness {

	String getJWTToken(String username, String password);

}
