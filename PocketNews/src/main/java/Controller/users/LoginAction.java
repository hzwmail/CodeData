package Controller.users;

import Controller.UserSession;
import Model.service.user.user.UserManager;
import Model.entity.SuperUser;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Admin
 * Date: 13-11-4
 * Time: 上午10:52
 * To change this template use File | Settings | File Templates.
 */
public class LoginAction extends ActionSupport {
    private int userId;
    private String supName;
    private String supPassword;
    private int roleId;
    private List<Map<String, Object>> roleIds = new ArrayList<Map<String, Object>>();

    @Override
    public String execute() throws Exception {

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");

        UserManager userManager = (UserManager) applicationContext.getBean("userManager");
        SuperUser superUser=null;
        if(roleId== SuperUser.USER_TYPE_SUPER_ADMIN){
            try {
                superUser=userManager.findAdminById(userId);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
//        }else if(userType == SuperUser.USER_TYPE__ADMIN ){
//            try {
//                superUser=userManager.findTeacherById(Integer.parseInt(userId));
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }else if(userType == SuperUser.USER_TYPE__AUDITOR){
//            try {
//                superUser=userManager.findStudentById(Integer.parseInt(userId));
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }

        //��֤�����Ƿ���ȷ�������ȷ����user����session��
        if(superUser!=null&&superUser.getSupPassword().equals(supPassword)){



            UserSession.addUser(superUser, ServletActionContext.getRequest().getSession());


            return SUCCESS;
        }else{

            addFieldError("loginError", "用户名或密码错误");

            return INPUT;
        }
    }
    public void validate() {
        String requestURI = ServletActionContext.getRequest().getRequestURI();

        if(requestURI.endsWith("init.action")){
            setFieldErrors(null);
        }

        initData();
    }
    public  String init() throws Exception {

        return INPUT;
    }
    private void initData(){
        HashMap<String, Object> item = new HashMap<String, Object>();

        item.put("name", "超级管理员");
        item.put("value", SuperUser.USER_TYPE_SUPER_ADMIN);
        roleIds.add(item);

        item = new HashMap<String, Object>();
        item.put("name", "用户");
        item.put("value", SuperUser.USER_TYPE__ADMIN);
        roleIds.add(item);

        item = new HashMap<String, Object>();
        item.put("name", "审核员");
        item.put("value",SuperUser.USER_TYPE__AUDITOR);

        roleIds.add(item);


    }

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

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public List<Map<String, Object>> getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(List<Map<String, Object>> roleIds) {
        this.roleIds = roleIds;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
