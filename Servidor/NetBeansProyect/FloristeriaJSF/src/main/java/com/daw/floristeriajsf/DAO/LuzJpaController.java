package com.daw.floristeriajsf.DAO;

import com.daw.floristeriajsf.DAO.exceptions.NonexistentEntityException;
import com.daw.floristeriajsf.DTO.Luz;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class LuzJpaController implements Serializable {

    public LuzJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Luz luz) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(luz);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Luz luz) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            luz = em.merge(luz);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = luz.getCodigoLuz();
                if (findLuz(id) == null) {
                    throw new NonexistentEntityException("The luz with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Luz luz;
            try {
                luz = em.getReference(Luz.class, id);
                luz.getCodigoLuz();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The luz with id " + id + " no longer exists.", enfe);
            }
            em.remove(luz);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Luz> findLuzEntities() {
        return findLuzEntities(true, -1, -1);
    }

    public List<Luz> findLuzEntities(int maxResults, int firstResult) {
        return findLuzEntities(false, maxResults, firstResult);
    }

    private List<Luz> findLuzEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Luz.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Luz findLuz(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Luz.class, id);
        } finally {
            em.close();
        }
    }

    public int getLuzCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Luz> rt = cq.from(Luz.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
