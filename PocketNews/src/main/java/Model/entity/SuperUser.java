package Model.entity;

/**
 * Created with IntelliJ IDEA.
 * User: Admin
 * Date: 13-10-31
 * Time: 下午2:28
 * To change this template use File | Settings | File Templates.
 */
public class SuperUser {
    public static final int USER_TYPE__AUDITOR = 3;
    public static final int USER_TYPE__ADMIN = 2;
    public static final int USER_TYPE_SUPER_ADMIN = 1;
    private int userId;
    private String supName;
    private String supPassword;
    private int role_Id;

    public String getSupName() {
        return supName;
    }

    public void setSupName(String supName) {
        this.supName = supName;
    }

    public String getSupPassword() {
        return supPassword;
    }

    public void setSupPassword(String supPassword) {
        this.supPassword = supPassword;
    }





    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getRole_Id() {
        return role_Id;
    }

    public void setRole_Id(int roleId) {
        this.role_Id = roleId;
    }
}
