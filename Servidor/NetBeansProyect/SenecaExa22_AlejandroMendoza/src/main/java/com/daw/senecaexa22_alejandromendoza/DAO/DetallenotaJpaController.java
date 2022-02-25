package com.daw.senecaexa22_alejandromendoza.DAO;

import com.daw.senecaexa22_alejandromendoza.DAO.exceptions.NonexistentEntityException;
import com.daw.senecaexa22_alejandromendoza.DAO.exceptions.PreexistingEntityException;
import com.daw.senecaexa22_alejandromendoza.DTO.Detallenota;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class DetallenotaJpaController implements Serializable {

    public DetallenotaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Detallenota detallenota) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(detallenota);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findDetallenota(detallenota.getId()) != null) {
                throw new PreexistingEntityException("Detallenota " + detallenota + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Detallenota detallenota) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            detallenota = em.merge(detallenota);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = detallenota.getId();
                if (findDetallenota(id) == null) {
                    throw new NonexistentEntityException("The detallenota with id " + id + " no longer exists.");
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
            Detallenota detallenota;
            try {
                detallenota = em.getReference(Detallenota.class, id);
                detallenota.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The detallenota with id " + id + " no longer exists.", enfe);
            }
            em.remove(detallenota);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Detallenota> findDetallenotaEntities() {
        return findDetallenotaEntities(true, -1, -1);
    }

    public List<Detallenota> findDetallenotaEntities(int maxResults, int firstResult) {
        return findDetallenotaEntities(false, maxResults, firstResult);
    }

    private List<Detallenota> findDetallenotaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Detallenota.class));
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

    public Detallenota findDetallenota(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Detallenota.class, id);
        } finally {
            em.close();
        }
    }

    public int getDetallenotaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Detallenota> rt = cq.from(Detallenota.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
