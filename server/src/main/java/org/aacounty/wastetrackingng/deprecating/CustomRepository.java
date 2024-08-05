package org.aacounty.wastetrackingng.deprecating;

public interface CustomRepository<T, ID> {
    void updateIntegerField(Class<T> entityClass, String fieldName, Integer value, String idName, ID id);

    void updateStringField(Class<T> entityClass, String fieldName, String value, String idName, ID id);

    void updateField(Class<T> entityClass, String fieldName, Object value, String idName, ID id);
}
