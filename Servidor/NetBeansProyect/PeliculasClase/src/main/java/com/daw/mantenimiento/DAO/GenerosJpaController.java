package com.daw.mantenimiento.DAO;

import com.daw.mantenimiento.DAO.exceptions.NonexistentEntityException;
import com.daw.mantenimiento.DTO.Generos;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class GenerosJpaController implements Serializable {

    public GenerosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Generos generos) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(generos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Generos generos) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            generos = em.merge(generos);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = generos.getId();
                if (findGeneros(id) == null) {
                    throw new NonexistentEntityException("The generos with id " + id + " no longer exists.");
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
            Generos generos;
            try {
                generos = em.getReference(Generos.class, id);
                generos.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The generos with id " + id + " no longer exists.", enfe);
            }
            em.remove(generos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Generos> findGenerosEntities() {
        return findGenerosEntities(true, -1, -1);
    }

    public List<Generos> findGenerosEntities(int maxResults, int firstResult) {
        return findGenerosEntities(false, maxResults, firstResult);
    }

    private List<Generos> findGenerosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Generos.class));
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

    public Generos findGeneros(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Generos.class, id);
        } finally {
            em.close();
        }
    }

    public int getGenerosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Generos> rt = cq.from(Generos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
