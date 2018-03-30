package com.loan.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

public class BaseAction extends MethodDispatchAction implements SessionAware
{
	private Map<String, Object> session;
	
	public Map<String, Object> getSession()
	{
		return session;
	}
	
	public void setSession(Map<String, Object> session)
	{
		this.session = session;
	}
}
