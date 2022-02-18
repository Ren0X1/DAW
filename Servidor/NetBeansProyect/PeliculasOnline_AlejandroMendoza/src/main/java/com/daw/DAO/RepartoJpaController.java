package com.daw.DAO;

import com.daw.DAO.exceptions.NonexistentEntityException;
import com.daw.DAO.exceptions.PreexistingEntityException;
import com.daw.DTO.Reparto;
import com.daw.DTO.RepartoPK;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class RepartoJpaController implements Serializable {

    public RepartoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Reparto reparto) throws PreexistingEntityException, Exception {
        if (reparto.getRepartoPK() == null) {
            reparto.setRepartoPK(new RepartoPK());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(reparto);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findReparto(reparto.getRepartoPK()) != null) {
                throw new PreexistingEntityException("Reparto " + reparto + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Reparto reparto) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            reparto = em.merge(reparto);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                RepartoPK id = reparto.getRepartoPK();
                if (findReparto(id) == null) {
                    throw new NonexistentEntityException("The reparto with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(RepartoPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Reparto reparto;
            try {
                reparto = em.getReference(Reparto.class, id);
                reparto.getRepartoPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The reparto with id " + id + " no longer exists.", enfe);
            }
            em.remove(reparto);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Reparto> findRepartoEntities() {
        return findRepartoEntities(true, -1, -1);
    }

    public List<Reparto> findRepartoEntities(int maxResults, int firstResult) {
        return findRepartoEntities(false, maxResults, firstResult);
    }

    private List<Reparto> findRepartoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Reparto.class));
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

    public Reparto findReparto(RepartoPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Reparto.class, id);
        } finally {
            em.close();
        }
    }

    public int getRepartoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Reparto> rt = cq.from(Reparto.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
