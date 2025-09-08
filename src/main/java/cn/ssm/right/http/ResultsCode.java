package cn.ssm.right.http;

public enum ResultsCode {
	//枚举变量
	SUCCESS(2000, "成功"),
	FAIL(0, "失败"),
	ADD_FAIL(5001, "新增失败"),
	UPDATE_FAIL(5002, "修改失败"),
	DELETE_FAIL(5003, "删除失败");
	//构造方法
	ResultsCode(Integer code, String message){
		this.code = code;
		this.message = message;
	}
	//属性
	private Integer code;
	private String message;
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}	
}
