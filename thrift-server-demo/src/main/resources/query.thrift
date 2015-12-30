namespace java com.harvest.demo.thrift

struct GroupMember {
		1:i64 id,
		2:i64 userId,
		3:string remarkName,
		4:i64 groupId,
		5:i32 type,
		6:i64 joinTime,
		7:i32 isLock,
		8:i64 lockDate
}
struct GroupMemberListResult {
		1:i32 err_code,
		2:string err_msg,
		3:list<GroupMember> groupMemeberList
}

service IGroupMemberQueryService {
	 	GroupMemberListResult getAllGroupMembers(1:i64 arg0, 2:i64 arg1)		        	
}

service IHelloService{
		void sayHello(1:string arg0)
}

