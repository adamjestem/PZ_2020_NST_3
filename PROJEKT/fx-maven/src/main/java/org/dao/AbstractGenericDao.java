package org.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;


@SuppressWarnings("unchecked")
public abstract class AbstractGenericDao<E> implements GenericDao<E> {

    private final Class<E> entityClass;

    public AbstractGenericDao() {
        this.entityClass = (Class<E>) ((ParameterizedType) this.getClass().getGenericSuperclass())
                .getActualTypeArguments()[0];
    }


    private SessionFactory sessionFactory;

    protected Session getSession() {
        return this.sessionFactory.getCurrentSession();
    }

    @Override
    public E findById(final Serializable id) {
        return (E) getSession().get(this.entityClass, id);
    }

    @Override
    public Serializable save(E entity) {
        return getSession().save(entity);
    }

    @Override
    public void saveOrUpdate(E entity) {
        getSession().saveOrUpdate(entity);
    }

    @Override
    public void delete(E entity) {
        getSession().delete(entity);
    }

    @Override
    public void deleteAll() {
        List<E> entities = findAll();
        for (E entity : entities) {
            getSession().delete(entity);
        }
    }

    @Override
    public List<E> findAll() {
        CriteriaBuilder cb = getSession().getCriteriaBuilder();
        CriteriaQuery<E> cr = cb.createQuery(this.entityClass);
        return (List<E>) cr.getGroupList();

    }

    @Override
    public List<E> findAllByExample(E entity) {
        CriteriaBuilder cb = getSession().getCriteriaBuilder();
        CriteriaQuery<E> cr = cb.createQuery(this.entityClass);

        Example example = Example.create(entity).ignoreCase().enableLike().excludeZeroes();
        return (List<E>) cr.where((Expression<Boolean>) example).getGroupList();
      //.  return getSession().createCriteria(this.entityClass).add(example).list();
    }

    @Override
    public void clear() {
        getSession().clear();

    }

    @Override
    public void flush() {
        getSession().flush();

    }

}