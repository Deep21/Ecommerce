package dw.fdb.com.fdbapp.db;

import android.database.sqlite.SQLiteDatabase;

import java.util.Map;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.AbstractDaoSession;
import de.greenrobot.dao.identityscope.IdentityScopeType;
import de.greenrobot.dao.internal.DaoConfig;



// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see de.greenrobot.dao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig cartDaoConfig;

    private final CartDao cartDao;

    public DaoSession(SQLiteDatabase db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        cartDaoConfig = daoConfigMap.get(CartDao.class).clone();
        cartDaoConfig.initIdentityScope(type);

        cartDao = new CartDao(cartDaoConfig, this);

        registerDao(Cart.class, cartDao);
    }
    
    public void clear() {
        cartDaoConfig.getIdentityScope().clear();
    }

    public CartDao getCartDao() {
        return cartDao;
    }

}