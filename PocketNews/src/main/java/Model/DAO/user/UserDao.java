package Model.DAO.user;

import Model.entity.SuperUser;

import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: Admin
 * Date: 13-11-4
 * Time: 下午5:50
 * To change this template use File | Settings | File Templates.
 */
public interface UserDao {
    public SuperUser findById(int id) throws SQLException;
}
