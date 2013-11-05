package Model.entity;

public class UserRole {
	private SuperUser superUser;
	private Role role;

    public SuperUser getUser() {
        return superUser;
    }

    public void setUser(SuperUser superUser) {
        this.superUser = superUser;
    }

    public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
}
