package com.daw.peliculas.DAO;

import com.daw.peliculas.DAO.exceptions.NonexistentEntityException;
import com.daw.peliculas.DAO.exceptions.PreexistingEntityException;
import com.daw.peliculas.DTO.Peliculasdetalle;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class PeliculasdetalleJpaController implements Serializable {

    public PeliculasdetalleJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Peliculasdetalle peliculasdetalle) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(peliculasdetalle);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPeliculasdetalle(peliculasdetalle.getId()) != null) {
                throw new PreexistingEntityException("Peliculasdetalle " + peliculasdetalle + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Peliculasdetalle peliculasdetalle) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            peliculasdetalle = em.merge(peliculasdetalle);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = peliculasdetalle.getId();
                if (findPeliculasdetalle(id) == null) {
                    throw new NonexistentEntityException("The peliculasdetalle with id " + id + " no longer exists.");
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
            Peliculasdetalle peliculasdetalle;
            try {
                peliculasdetalle = em.getReference(Peliculasdetalle.class, id);
                peliculasdetalle.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The peliculasdetalle with id " + id + " no longer exists.", enfe);
            }
            em.remove(peliculasdetalle);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Peliculasdetalle> findPeliculasdetalleEntities() {
        return findPeliculasdetalleEntities(true, -1, -1);
    }

    public List<Peliculasdetalle> findPeliculasdetalleEntities(int maxResults, int firstResult) {
        return findPeliculasdetalleEntities(false, maxResults, firstResult);
    }

    private List<Peliculasdetalle> findPeliculasdetalleEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Peliculasdetalle.class));
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

    public Peliculasdetalle findPeliculasdetalle(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Peliculasdetalle.class, id);
        } finally {
            em.close();
        }
    }

    public int getPeliculasdetalleCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Peliculasdetalle> rt = cq.from(Peliculasdetalle.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
