package cn.ssm.right.http;


public class Results {
	private Integer code;
	private String message;
	private Object data;	

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

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
	public static Results success() {
		Results r = new Results();
		r.setCode(ResultsCode.SUCCESS.getCode());
		r.setMessage(ResultsCode.SUCCESS.getMessage());
		return r;				
	}
	
	public static Results success(String message) {
		Results r = new Results();
		r.setCode(ResultsCode.SUCCESS.getCode());
		r.setMessage(message);		
		return r;
	}
	
	public static Results success(String message, Object data) {
		Results r = new Results();
		r.setCode(ResultsCode.SUCCESS.getCode());
		r.setMessage(message);
		r.setData(data);
		return r;
	}
	
	public static Results fail() {
		Results r = new Results();
		r.setCode(ResultsCode.FAIL.getCode());
		r.setMessage(ResultsCode.FAIL.getMessage());
		return r;
	}
	
	public static Results fail(String message) {
		Results r = new Results();
		r.setCode(ResultsCode.FAIL.getCode());
		r.setMessage(message);
		return r;
	}
}
