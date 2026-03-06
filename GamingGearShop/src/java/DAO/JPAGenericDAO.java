package DAO;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import utils.JPAUtils;

public abstract class JPAGenericDAO<T> {

    private static final Logger LOGGER = Logger.getLogger(JPAGenericDAO.class.getName());

    private final Class<T> entityClass;

    public JPAGenericDAO(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public T findById(Object id) {
        EntityManager em = null;
        try {
            em = JPAUtils.getEntityManager();
            return em.find(entityClass, id);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Lỗi findById ở class: " + entityClass.getSimpleName(), e);
            return null;
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    public List<T> query(String jpql, Object... params) {
        EntityManager em = null;

        try {
            em = JPAUtils.getEntityManager();
            TypedQuery<T> q = em.createQuery(jpql, entityClass);

            if (params != null) {
                for (int i = 0; i < params.length; i++) {
                    q.setParameter(i + 1, params[i]);
                }
            }
            return q.getResultList();
        } catch (IllegalArgumentException e) {
            LOGGER.log(Level.SEVERE, "Cú pháp JPQL hoặc tham số không hợp lệ ở class: " + entityClass.getSimpleName(), e);
            return null;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Lỗi query ở class: " + entityClass.getSimpleName(), e);
            return null;
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    public boolean insert(T entity) {
        EntityManager em = null;
        EntityTransaction trans = null;
        try {
            em = JPAUtils.getEntityManager();
            trans = em.getTransaction();

            trans.begin();
            em.persist(entity);
            trans.commit();

            return true;
        } catch (IllegalArgumentException e) {
            LOGGER.log(Level.SEVERE, "Dữ liệu truyền vào không hợp lệ ở class: " + entityClass.getSimpleName(), e);
            if (trans != null && trans.isActive()) {
                trans.rollback();
            }
            return false;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Lỗi hệ thống khi INSERT ở class: " + entityClass.getSimpleName(), e);
            if (trans != null && trans.isActive()) {
                trans.rollback();
            }
            return false;
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }
    
    public boolean update(T entity){
        EntityManager em = null;
        EntityTransaction trans = null;
        try{
            em = JPAUtils.getEntityManager();
            trans = em.getTransaction();
            
            trans.begin();
            em.merge(entity);
            trans.commit();
            
            return true;
        }catch(IllegalArgumentException e){
            LOGGER.log(Level.SEVERE, "Dữ liệu cập nhật không hợp lệ ở class: " + entityClass.getSimpleName(), e);
            if(trans != null && trans.isActive()){
                trans.rollback();
            }
            return false;
        }catch(Exception e){
            LOGGER.log(Level.SEVERE, "Lỗi hệ thống khi UPDATE ở class: " + entityClass.getSimpleName(), e);
            if(trans != null && trans.isActive()){
                trans.rollback();
            }
            return false;
        }finally{
            if(em != null && em.isOpen()){
                em.close();
            }
        }
    }
    
    public boolean remove(Object id){
        EntityManager em = null;
        EntityTransaction trans = null;
        try{
            em = JPAUtils.getEntityManager();
            trans = em.getTransaction();
            
            trans.begin();
            T entity = em.find(entityClass, id);
            
            if(entity != null){
                em.remove(entity);
                trans.commit();
                return true;
            }else{
                LOGGER.log(Level.WARNING, "Không tìm thấy ID: {0} để xóa trong class: {1}", new Object[]{id, entityClass.getSimpleName()});
                trans.rollback();
                return false;
            }
        }catch(IllegalArgumentException e){
            LOGGER.log(Level.SEVERE, "ID truyền vào không hợp lệ ở class: " + entityClass.getSimpleName(), e);
            if(trans != null && trans.isActive()){
                trans.rollback();
            }
            return false;
        }catch(Exception e){
            LOGGER.log(Level.SEVERE, "Lỗi hệ thống khi REMOVE ở class: " + entityClass.getSimpleName(), e);
            if(trans != null && trans.isActive()){
                trans.rollback();
            }
            return false;
        }finally{
            if(em != null && em.isOpen()){
                em.close();
            }
        }
    }
}
