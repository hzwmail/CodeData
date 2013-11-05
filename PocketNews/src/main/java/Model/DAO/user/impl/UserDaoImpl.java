package Model.DAO.user.impl;

import Model.DAO.user.UserDao;
import Model.service.user.user.UserManager;
import Model.entity.SuperUser;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: Admin
 * Date: 13-11-4
 * Time: 下午1:35
 * To change this template use File | Settings | File Templates.
 */
public class UserDaoImpl extends HibernateDaoSupport implements UserDao {


    @Override
    public SuperUser findById(int id) throws SQLException {
        SuperUser superUser = getHibernateTemplate().get(SuperUser.class,id);
        return superUser;
    }
}
