package com.harvest.demo.server.impl;

import org.apache.log4j.Logger;
import org.apache.thrift.TException;

import com.harvest.demo.thrift.GroupMemberListResult;
import com.harvest.demo.thrift.IGroupMemberQueryService.Iface;

public class GroupMemberQueryServiceImpl implements Iface {

	private static final Logger log = Logger
			.getLogger(GroupMemberQueryServiceImpl.class);

	public GroupMemberListResult getAllGroupMembers(long arg0, long arg1)
			throws TException {
		GroupMemberListResult result = new GroupMemberListResult();
		result.setErr_code(0000);
		result.setErr_msg("服务器启动");
		log.info("服务器启动");
		return result;
	}
}
