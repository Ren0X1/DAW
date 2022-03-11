package com.daw.crudtable_primefaces_alejandromendoza_lauramedina.DAO;

import com.daw.crudtable_primefaces_alejandromendoza_lauramedina.DAO.exceptions.NonexistentEntityException;
import com.daw.crudtable_primefaces_alejandromendoza_lauramedina.DAO.exceptions.PreexistingEntityException;
import com.daw.crudtable_primefaces_alejandromendoza_lauramedina.DTO.Detallepedidos;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class DetallepedidosJpaController implements Serializable {

    public DetallepedidosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Detallepedidos detallepedidos) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(detallepedidos);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findDetallepedidos(detallepedidos.getUid()) != null) {
                throw new PreexistingEntityException("Detallepedidos " + detallepedidos + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Detallepedidos detallepedidos) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            detallepedidos = em.merge(detallepedidos);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = detallepedidos.getUid();
                if (findDetallepedidos(id) == null) {
                    throw new NonexistentEntityException("The detallepedidos with id " + id + " no longer exists.");
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
            Detallepedidos detallepedidos;
            try {
                detallepedidos = em.getReference(Detallepedidos.class, id);
                detallepedidos.getUid();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The detallepedidos with id " + id + " no longer exists.", enfe);
            }
            em.remove(detallepedidos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Detallepedidos> findDetallepedidosEntities() {
        return findDetallepedidosEntities(true, -1, -1);
    }

    public List<Detallepedidos> findDetallepedidosEntities(int maxResults, int firstResult) {
        return findDetallepedidosEntities(false, maxResults, firstResult);
    }

    private List<Detallepedidos> findDetallepedidosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Detallepedidos.class));
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

    public Detallepedidos findDetallepedidos(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Detallepedidos.class, id);
        } finally {
            em.close();
        }
    }

    public int getDetallepedidosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Detallepedidos> rt = cq.from(Detallepedidos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
