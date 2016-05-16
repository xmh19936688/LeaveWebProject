package vo;

public class Ask {
	
	private String userid;//用户ID
	private String classid;//班级ID
	private String username;//用户名
	private String startdate;//起始日期
	private String enddate;//终止日期
	private String reason;//请假原因
	private String state;//审批状态
	
	public Ask(){		
	}
	
	public Ask(String userid2, String startdate2, String enddate2,String reason2, String state2) {
		userid=userid2;
		startdate=startdate2;
		enddate=enddate2;
		reason=reason2;
		state=state2;
	}
	
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getClassid() {
		return classid;
	}
	public void setClassid(String classid) {
		this.classid = classid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getStartdate() {
		return startdate;
	}
	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getEnddate() {
		return enddate;
	}
	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
}