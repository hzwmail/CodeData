package Model.entity;

import java.util.ArrayList;
import java.util.List;

public class Role {
	private int roleId;
	private String roleName;
	private int roleStatue;
	private List<UserRole> userRoles=new ArrayList<UserRole>();
	private List<RoleFunction> roleFunctions=new ArrayList<RoleFunction>();
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public int getRoleStatue() {
		return roleStatue;
	}
	public void setRoleStatue(int roleStatue) {
		this.roleStatue = roleStatue;
	}
	public List<UserRole> getUserRoles() {
		return userRoles;
	}
	public void setUserRoles(List<UserRole> userRoles) {
		this.userRoles = userRoles;
	}
	public List<RoleFunction> getRoleFunctions() {
		return roleFunctions;
	}
	public void setRoleFunctions(List<RoleFunction> roleFunctions) {
		this.roleFunctions = roleFunctions;
	}
	
	
}
