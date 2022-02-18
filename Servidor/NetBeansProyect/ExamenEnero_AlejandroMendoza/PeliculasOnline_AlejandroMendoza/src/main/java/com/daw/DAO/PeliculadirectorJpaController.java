package com.daw.DAO;

import com.daw.DAO.exceptions.NonexistentEntityException;
import com.daw.DAO.exceptions.PreexistingEntityException;
import com.daw.DTO.Peliculadirector;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class PeliculadirectorJpaController implements Serializable {

    public PeliculadirectorJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Peliculadirector peliculadirector) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(peliculadirector);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPeliculadirector(peliculadirector.getId()) != null) {
                throw new PreexistingEntityException("Peliculadirector " + peliculadirector + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Peliculadirector peliculadirector) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            peliculadirector = em.merge(peliculadirector);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = peliculadirector.getId();
                if (findPeliculadirector(id) == null) {
                    throw new NonexistentEntityException("The peliculadirector with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Peliculadirector peliculadirector;
            try {
                peliculadirector = em.getReference(Peliculadirector.class, id);
                peliculadirector.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The peliculadirector with id " + id + " no longer exists.", enfe);
            }
            em.remove(peliculadirector);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Peliculadirector> findPeliculadirectorEntities() {
        return findPeliculadirectorEntities(true, -1, -1);
    }

    public List<Peliculadirector> findPeliculadirectorEntities(int maxResults, int firstResult) {
        return findPeliculadirectorEntities(false, maxResults, firstResult);
    }

    private List<Peliculadirector> findPeliculadirectorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Peliculadirector.class));
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

    public Peliculadirector findPeliculadirector(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Peliculadirector.class, id);
        } finally {
            em.close();
        }
    }

    public int getPeliculadirectorCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Peliculadirector> rt = cq.from(Peliculadirector.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
