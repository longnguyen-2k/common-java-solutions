
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
@Repository
public abstract class BaseDao<E, ID extends Serializable> {
    private static Logger logger = LoggerFactory.getLogger(BaseDao.class);
    protected Class<E> entityClass;
    public static String fieldCreatedBy = "setCreatedBy";
    public static String fieldCreatedDatetime = "setCreatedDatetime";
    public static String fieldUpdatedBy = "setUpdatedBy";
    public static String fieldUpdatedDatetime = "setUpdatedDatetime";
    public static String fieldCreatedByGetter = "getCreatedBy";
    public static String fieldCreatedDatetimeGetter = "getCreatedDatetime";
    protected HeaderObject headerObject;
    @PersistenceContext
    public EntityManager entityManager;

    public BaseDao() {
        try {
            ParameterizedType entityManager = (ParameterizedType)this.getClass().getGenericSuperclass();
            this.entityClass = (Class)entityManager.getActualTypeArguments()[0];
        } catch (Exception datetime) {
        }

    }

    public EntityManager getEntityManager() {
        return this.entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public abstract void generateIdAndPersist(E entityManager);

    public void generateIdAndPersist(List<E> entityManagerList) {
        Iterator entityManager = entityManagerList.iterator();

        while(entityManager.hasNext()) {
            Object next = entityManager.next();
            this.generateIdAndPersist((E)next);
        }

    }

    public abstract String generateId();

    public void persist(E entityManager, Date datetime) {
        Method createdBy;
        Method createdDatetime;
        try {
            createdBy = entityManager.getClass().getMethod(fieldCreatedBy, String.class);
            createdDatetime = entityManager.getClass().getMethod(fieldCreatedDatetime, Date.class);
            Method getCreatedBy = entityManager.getClass().getMethod(fieldCreatedByGetter);
            Method getCreatedDatetime = entityManager.getClass().getMethod(fieldCreatedDatetimeGetter);
            if (getCreatedBy.invoke(entityManager) == null) {
                createdBy.invoke(entityManager, headerObject.getUserId());
            }

            if (getCreatedDatetime.invoke(entityManager) == null) {
                createdDatetime.invoke(entityManager, datetime);
            }
        } catch (NoSuchMethodException ignored) {
        } catch (Exception  exception){
            throw new RuntimeException(exception);
        }

      

        this.entityManager.persist(entityManager);
    }

    public void persist(E entityManager) {
        this.persist(entityManager, new Date());
    }

    public void persist(List<E> entityManagerList) {
        Iterator entityManagerIterators = entityManagerList.iterator();

        while(entityManagerIterators.hasNext()) {
            Object nextEntityManager = entityManagerIterators.next();
            this.persist((E) nextEntityManager, new Date());
        }

    }

    public void remove(E entityManager) {
        this.entityManager.remove(this.entityManager.contains(entityManager) ? entityManager : this.entityManager.merge(entityManager));
    }

    public void remove(List<E> entityManagerList) {
        Iterator entityManagers = entityManagerList.iterator();

        while(entityManagers.hasNext()) {
            Object entityManager = entityManagers.next();
            this.entityManager.remove(this.entityManager.contains(entityManager) ? entityManager : this.entityManager.merge(entityManager));
        }

    }

    public E merge(E entityManager, Date datetime) {
        Method updatedBy;
        try {
            updatedBy = entityManager.getClass().getMethod(fieldUpdatedBy, String.class);
            Method  updatedDatetime= entityManager.getClass().getMethod(fieldUpdatedDatetime, Date.class);
            updatedBy.invoke(entityManager, headerObject.getUserId());
            updatedDatetime.invoke(entityManager, datetime);
        } catch (NoSuchMethodException noSuchMethodException) {
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        return this.entityManager.merge(entityManager);
    }

    public E merge(E entityManager) {
        return this.merge(entityManager, new Date());
    }

    public List<E> merge(List<E> entityManagerList) {
        ArrayList datetime = new ArrayList();
        Iterator eIterator = entityManagerList.iterator();

        while(eIterator.hasNext()) {
            Object object = eIterator.next();
            datetime.add(this.merge((E)object, new Date()));
        }

        return datetime;
    }

    public void refresh(E entityManager) {
        this.entityManager.refresh(entityManager);
    }

    public E findById(ID entityManager) {
        return this.entityManager.find(this.entityClass, entityManager);
    }

    public E findById(ID entityManager, LockModeType datetime) {
        return this.entityManager.find(this.entityClass, entityManager, datetime);
    }

    public E flush(E entityManager) {
        this.entityManager.flush();
        return entityManager;
    }

    public Integer removeAll() {
        Query entityManager = this.entityManager.createQuery("DELETE FROM " + this.entityClass.getSimpleName() + " a ");
        return entityManager.executeUpdate();
    }

    public List<E> findAll() {
        return this.find("SELECT a FROM " + this.entityClass.getSimpleName() + " a ");
    }

    public List<E> findAll(Integer entityManager, Integer datetime) {
        return this.find("SELECT a FROM " + this.entityClass.getSimpleName() + " a ", entityManager, datetime, (Object[])null);
    }

    public List<E> find(E entityManager) {
        return this.find("SELECT a FROM " + this.entityClass.getSimpleName() + " a ");
    }

    public List<E> find(E entityManager, String datetime) {
        datetime = StringUtil.hasValue(datetime) ? "order by " + datetime : "";
        return this.find("SELECT a FROM " + this.entityClass.getSimpleName() + " a " + datetime);
    }

    public List<E> find(E entityManager, Integer startIndex, Integer max) {
        return this.find("SELECT a FROM " + this.entityClass.getSimpleName() + " a ", startIndex, max);
    }

    public List<E> find(E entityManager, String datetime, Integer startIndex, Integer max) {
        datetime = StringUtil.hasValue(datetime) ? "order by " + datetime : "";
        return this.find("SELECT a FROM " + this.entityClass.getSimpleName() + " a " + datetime, startIndex, max);
    }

    public List<E> find(String queryString) {
        return this.find((String)queryString, (Integer)null, (Integer)null, (Object[])((Object[])null));
    }

    public List<E> find(String queryString, Object... params) {
        return this.find((String)queryString, (Integer)null, (Integer)null, (Object[])params);
    }

    public List<E> find(String queryString, LockModeType lockModeType, Object... params) {
        return this.find(queryString, (Integer)null, (Integer)null, lockModeType, params);
    }

    public List<E> find(String queryString, List<?> datetime) {
        return this.find((String)queryString, (Integer)null, (Integer)null, (Object[])datetime.toArray());
    }

    public List<E> find(String queryString, List<?> datetime, LockModeType lockModeType) {
        return this.find(queryString, (Integer)null, (Integer)null, lockModeType, datetime.toArray());
    }

    public List<E> find(String queryString, Integer startIndex, Integer max, Object... params) {
        Query query = this.A(queryString, startIndex, max, (LockModeType)null, params);
        return query.getResultList();
    }

    public List<E> find(String queryString, Integer startIndex, Integer max, LockModeType lockModeType, Object... params) {
        Query query = this.A(queryString, startIndex, max, lockModeType, params);
        return query.getResultList();
    }

    private Query A(String queryString, Integer startIndex, Integer max, LockModeType lockModeType, Object... params) {
        Query query = this.entityManager.createQuery(queryString);
        if (params != null) {
            for(int i = 0; i < params.length; ++i) {
                logger.debug("index = [" + (i + 1) + "] values = [" + params[i] + "]");
                query.setParameter(i + 1, params[i]);
            }
        }

        if (startIndex != null && max != null) {
            logger.debug("First Index = [" + startIndex + "], Max Results = [" + max + "]");
            query.setFirstResult(startIndex);
            query.setMaxResults(max);
        }

        if (lockModeType != null) {
            logger.debug("LockMode = [" + lockModeType + "]");
            query.setLockMode(lockModeType);
        }

        return query;
    }

    public Integer findCount() {
        Integer entityManager = this.findCount("SELECT count(a) FROM " + this.entityClass.getSimpleName() + " a ");
        return NumberUtil.hasValue(entityManager) ? entityManager.intValue() : 0;
    }

    public Integer findCount(E entityManager) {
        Integer count = this.findCount("SELECT count(a) FROM " + this.entityClass.getSimpleName() + " a ");
        return NumberUtil.hasValue(count) ? count.intValue() : 0;
    }

    public Integer findCount(String queryString) {
        Integer count = this.findCount(queryString, (Object[])null);
        return NumberUtil.hasValue(count) ? count.intValue() : 0;
    }

    public Integer findCount(String queryString, Object... params) {
        queryString = queryString.replaceAll("(?i)fetch", "");
        Query query = this.A(queryString, (Integer)null, (Integer)null, (LockModeType)null, params);
        Number count = (Number)query.getSingleResult();
        return NumberUtil.hasValue(count) ? count.intValue() : 0;
    }

    public Number findANumber(String queryString, Object... params) {
        Query query = this.A(queryString, (Integer)null, (Integer)null, (LockModeType)null, params);
        return (Number)query.getSingleResult();
    }

    public List<?> findNative(String queryString) {
        return this.findNative(queryString, (Integer)null, (Integer)null, (Class)null, (Object[])null);
    }

    public List<?> findNative(String queryString, Object... params) {
        return this.findNative(queryString, (Integer)null, (Integer)null, (Class)null, params);
    }

    public List<?> findNative(String queryString, Class<?> clazz) {
        return this.findNative(queryString, (Integer)null, (Integer)null, clazz, (Object[])null);
    }

    public List<?> findNative(String queryString, Class<?> clazz, Object... params ) {
        return this.findNative(queryString, (Integer)null, (Integer)null, clazz, params);
    }

    public List<?> findNative(String queryString, Integer startIndex, Integer max, Class<?> clazz, Object... params) {
        Query query = this.A(queryString, startIndex, max, clazz, params);
        return query.getResultList();
    }

    private Query A(String queryString, Integer startIndex, Integer max, Class<?> clazz, Object... params) {
        Query query;
        if (clazz == null) {
            query = this.entityManager.createNativeQuery(queryString);
        } else {
            query = this.entityManager.createNativeQuery(queryString, clazz);
        }

        if (params != null) {
            for(int i = 0; i < params.length; ++i) {
                logger.debug("index = [" + (i + 1) + "] values = [" + params[i] + "]");
                query.setParameter(i + 1, params[i]);
            }
        }

        if (startIndex != null && max != null) {
            logger.debug("First Index = [" + startIndex + "], Max Results = [" + max + "]");
            query.setFirstResult(startIndex);
            query.setMaxResults(max);
        }

        return query;
    }

    public Integer executeUpdate(String queryString) {
        return this.executeUpdate(queryString, (Object[])null);
    }

    public Integer executeUpdate(String queryString, List<?> params) {
        return this.executeUpdate(queryString, params.toArray());
    }

    public Integer executeUpdate(String queryString, Object... params) {
        Query query = this.A(queryString, (Integer)null, (Integer)null, (LockModeType)null, params);
        return query.executeUpdate();
    }
}

