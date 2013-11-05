package Model.service.user.user.impl;

import Model.DAO.user.UserDao;
import Model.DAO.user.impl.UserDaoImpl;
import Model.entity.SuperUser;
import Model.service.user.user.UserManager;

import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: Admin
 * Date: 13-11-4
 * Time: 下午5:56
 * To change this template use File | Settings | File Templates.
 */
public class UserManagerImpl implements UserManager {

    private UserManagerImpl userManager;
    private UserDao userDao;

    @Override
    public SuperUser findAdminById(int id) throws SQLException {
        SuperUser superUser =userDao.findById(id);
        return superUser;
    }

    public void setUserManager(UserManagerImpl userManager) {
        this.userManager = userManager;
    }

    public void setUserDao(UserDaoImpl userDao) {
        this.userDao = userDao;
    }
}
