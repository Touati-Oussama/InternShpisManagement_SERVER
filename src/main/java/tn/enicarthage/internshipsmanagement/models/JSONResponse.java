package tn.enicarthage.internshipsmanagement.models;

public class JSONResponse {

	private  boolean success;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	@Override
	public String toString() {
		return "JSONResponse [success=" + success + "]";
	}

	public JSONResponse(boolean success) {
		super();
		this.success = success;
	}

	public JSONResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
