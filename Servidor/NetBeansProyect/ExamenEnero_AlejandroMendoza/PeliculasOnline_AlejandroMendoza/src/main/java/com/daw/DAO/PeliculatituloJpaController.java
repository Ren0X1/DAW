package com.daw.DAO;

import com.daw.DAO.exceptions.NonexistentEntityException;
import com.daw.DAO.exceptions.PreexistingEntityException;
import com.daw.DTO.Peliculatitulo;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class PeliculatituloJpaController implements Serializable {

    public PeliculatituloJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Peliculatitulo peliculatitulo) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(peliculatitulo);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPeliculatitulo(peliculatitulo.getId()) != null) {
                throw new PreexistingEntityException("Peliculatitulo " + peliculatitulo + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Peliculatitulo peliculatitulo) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            peliculatitulo = em.merge(peliculatitulo);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = peliculatitulo.getId();
                if (findPeliculatitulo(id) == null) {
                    throw new NonexistentEntityException("The peliculatitulo with id " + id + " no longer exists.");
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
            Peliculatitulo peliculatitulo;
            try {
                peliculatitulo = em.getReference(Peliculatitulo.class, id);
                peliculatitulo.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The peliculatitulo with id " + id + " no longer exists.", enfe);
            }
            em.remove(peliculatitulo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Peliculatitulo> findPeliculatituloEntities() {
        return findPeliculatituloEntities(true, -1, -1);
    }

    public List<Peliculatitulo> findPeliculatituloEntities(int maxResults, int firstResult) {
        return findPeliculatituloEntities(false, maxResults, firstResult);
    }

    private List<Peliculatitulo> findPeliculatituloEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Peliculatitulo.class));
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

    public Peliculatitulo findPeliculatitulo(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Peliculatitulo.class, id);
        } finally {
            em.close();
        }
    }

    public int getPeliculatituloCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Peliculatitulo> rt = cq.from(Peliculatitulo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
