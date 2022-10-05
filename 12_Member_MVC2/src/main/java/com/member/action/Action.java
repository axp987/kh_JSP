package com.member.action;

import java.io.IOException;
import java.sql.Connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

public interface Action {
	
	public String execute(HttpServletRequest request, 
			HttpServletResponse response) throws IOException;

}
