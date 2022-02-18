package com.daw.DAO;

import com.daw.DAO.exceptions.NonexistentEntityException;
import com.daw.DTO.Opinion;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class OpinionJpaController implements Serializable {

    public OpinionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Opinion opinion) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(opinion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Opinion opinion) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            opinion = em.merge(opinion);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = opinion.getCodComen();
                if (findOpinion(id) == null) {
                    throw new NonexistentEntityException("The opinion with id " + id + " no longer exists.");
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
            Opinion opinion;
            try {
                opinion = em.getReference(Opinion.class, id);
                opinion.getCodComen();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The opinion with id " + id + " no longer exists.", enfe);
            }
            em.remove(opinion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Opinion> findOpinionEntities() {
        return findOpinionEntities(true, -1, -1);
    }

    public List<Opinion> findOpinionEntities(int maxResults, int firstResult) {
        return findOpinionEntities(false, maxResults, firstResult);
    }

    private List<Opinion> findOpinionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Opinion.class));
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

    public Opinion findOpinion(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Opinion.class, id);
        } finally {
            em.close();
        }
    }

    public int getOpinionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Opinion> rt = cq.from(Opinion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
