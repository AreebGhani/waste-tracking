package org.aacounty.wastetrackingng.deprecating;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;

import org.springframework.transaction.annotation.Transactional;

public class CustomRepositoryImpl<T, ID> implements CustomRepository<T, ID> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void updateIntegerField(Class<T> entityClass, String fieldName, Integer value, String idName, ID id) {
        updateField(entityClass, fieldName, value, idName, id);
    }

    @Override
    @Transactional
    public void updateStringField(Class<T> entityClass, String fieldName, String value, String idName, ID id) {
        updateField(entityClass, fieldName, value, idName, id);
    }

    @Override
    @Transactional
    public void updateField(Class<T> entityClass, String fieldName, Object value, String idName, ID id) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaUpdate<T> update = cb.createCriteriaUpdate(entityClass);
        Root<T> root = update.from(entityClass);

        update.set(root.get(fieldName), value)
                .where(cb.equal(root.get(idName), id));

        entityManager.createQuery(update).executeUpdate();
    }
}
