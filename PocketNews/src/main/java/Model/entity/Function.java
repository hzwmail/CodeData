package Model.entity;

import java.util.ArrayList;
import java.util.List;

public class Function {
	private String functionCode;
	private String functionObject;
	private String functionName;
	private List<RoleFunction> rolefunctions=new ArrayList<RoleFunction>();
	public String getFunctionCode() {
		return functionCode;
	}
	public void setFunctionCode(String functionCode) {
		this.functionCode = functionCode;
	}
	public String getFunctionObject() {
		return functionObject;
	}
	public void setFunctionObject(String functionObject) {
		this.functionObject = functionObject;
	}
	public String getFunctionName() {
		return functionName;
	}
	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}
	public List<RoleFunction> getRolefunctions() {
		return rolefunctions;
	}
	public void setRolefunctions(List<RoleFunction> rolefunctions) {
		this.rolefunctions = rolefunctions;
	}
	
}
