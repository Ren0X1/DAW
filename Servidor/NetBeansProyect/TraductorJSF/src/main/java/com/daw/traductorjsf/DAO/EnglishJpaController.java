package com.daw.traductorjsf.DAO;

import com.daw.traductorjsf.DTO.English;
import com.daw.traductorjsf.DAO.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class EnglishJpaController implements Serializable {

    public EnglishJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(English english) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(english);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(English english) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            english = em.merge(english);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = english.getCodPal();
                if (findEnglish(id) == null) {
                    throw new NonexistentEntityException("The english with id " + id + " no longer exists.");
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
            English english;
            try {
                english = em.getReference(English.class, id);
                english.getCodPal();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The english with id " + id + " no longer exists.", enfe);
            }
            em.remove(english);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<English> findEnglishEntities() {
        return findEnglishEntities(true, -1, -1);
    }

    public List<English> findEnglishEntities(int maxResults, int firstResult) {
        return findEnglishEntities(false, maxResults, firstResult);
    }

    private List<English> findEnglishEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(English.class));
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

    public English findEnglish(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(English.class, id);
        } finally {
            em.close();
        }
    }

    public int getEnglishCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<English> rt = cq.from(English.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
