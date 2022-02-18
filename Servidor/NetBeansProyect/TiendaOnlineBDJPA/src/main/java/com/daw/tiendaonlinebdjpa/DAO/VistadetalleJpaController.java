/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.daw.tiendaonlinebdjpa.DAO;

import com.daw.tiendaonlinebdjpa.DTO.Vistadetalle;
import com.daw.tiendaonlinebdjpa.DAO.exceptions.NonexistentEntityException;
import com.daw.tiendaonlinebdjpa.DAO.exceptions.PreexistingEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author Lola
 */
public class VistadetalleJpaController implements Serializable {

    public VistadetalleJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Vistadetalle vistadetalle) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(vistadetalle);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findVistadetalle(vistadetalle.getUid()) != null) {
                throw new PreexistingEntityException("Vistadetalle " + vistadetalle + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Vistadetalle vistadetalle) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            vistadetalle = em.merge(vistadetalle);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = vistadetalle.getUid();
                if (findVistadetalle(id) == null) {
                    throw new NonexistentEntityException("The vistadetalle with id " + id + " no longer exists.");
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
            Vistadetalle vistadetalle;
            try {
                vistadetalle = em.getReference(Vistadetalle.class, id);
                vistadetalle.getUid();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The vistadetalle with id " + id + " no longer exists.", enfe);
            }
            em.remove(vistadetalle);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Vistadetalle> findVistadetalleEntities() {
        return findVistadetalleEntities(true, -1, -1);
    }

    public List<Vistadetalle> findVistadetalleEntities(int maxResults, int firstResult) {
        return findVistadetalleEntities(false, maxResults, firstResult);
    }

    private List<Vistadetalle> findVistadetalleEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Vistadetalle.class));
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

    public Vistadetalle findVistadetalle(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Vistadetalle.class, id);
        } finally {
            em.close();
        }
    }

    public int getVistadetalleCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Vistadetalle> rt = cq.from(Vistadetalle.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
