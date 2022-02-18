package com.daw.DAO;

import com.daw.DAO.exceptions.NonexistentEntityException;
import com.daw.DAO.exceptions.PreexistingEntityException;
import com.daw.DTO.Peliculagenero;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class PeliculageneroJpaController implements Serializable {

    public PeliculageneroJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Peliculagenero peliculagenero) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(peliculagenero);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPeliculagenero(peliculagenero.getId()) != null) {
                throw new PreexistingEntityException("Peliculagenero " + peliculagenero + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Peliculagenero peliculagenero) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            peliculagenero = em.merge(peliculagenero);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = peliculagenero.getId();
                if (findPeliculagenero(id) == null) {
                    throw new NonexistentEntityException("The peliculagenero with id " + id + " no longer exists.");
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
            Peliculagenero peliculagenero;
            try {
                peliculagenero = em.getReference(Peliculagenero.class, id);
                peliculagenero.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The peliculagenero with id " + id + " no longer exists.", enfe);
            }
            em.remove(peliculagenero);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Peliculagenero> findPeliculageneroEntities() {
        return findPeliculageneroEntities(true, -1, -1);
    }

    public List<Peliculagenero> findPeliculageneroEntities(int maxResults, int firstResult) {
        return findPeliculageneroEntities(false, maxResults, firstResult);
    }

    private List<Peliculagenero> findPeliculageneroEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Peliculagenero.class));
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

    public Peliculagenero findPeliculagenero(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Peliculagenero.class, id);
        } finally {
            em.close();
        }
    }

    public int getPeliculageneroCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Peliculagenero> rt = cq.from(Peliculagenero.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
