package Model.service.user.user;

import Model.entity.SuperUser;

import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: Admin
 * Date: 13-11-4
 * Time: 下午1:25
 * To change this template use File | Settings | File Templates.
 */
public interface UserManager {
    public SuperUser findAdminById(int id) throws SQLException;

}
