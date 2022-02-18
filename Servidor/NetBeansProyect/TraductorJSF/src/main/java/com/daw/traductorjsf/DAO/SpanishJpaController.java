package com.daw.traductorjsf.DAO;

import com.daw.traductorjsf.DTO.Spanish;
import com.daw.traductorjsf.DAO.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class SpanishJpaController implements Serializable {

    public SpanishJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Spanish spanish) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(spanish);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Spanish spanish) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            spanish = em.merge(spanish);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = spanish.getCodPal();
                if (findSpanish(id) == null) {
                    throw new NonexistentEntityException("The spanish with id " + id + " no longer exists.");
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
            Spanish spanish;
            try {
                spanish = em.getReference(Spanish.class, id);
                spanish.getCodPal();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The spanish with id " + id + " no longer exists.", enfe);
            }
            em.remove(spanish);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Spanish> findSpanishEntities() {
        return findSpanishEntities(true, -1, -1);
    }

    public List<Spanish> findSpanishEntities(int maxResults, int firstResult) {
        return findSpanishEntities(false, maxResults, firstResult);
    }

    private List<Spanish> findSpanishEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Spanish.class));
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

    public Spanish findSpanish(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Spanish.class, id);
        } finally {
            em.close();
        }
    }

    public int getSpanishCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Spanish> rt = cq.from(Spanish.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
