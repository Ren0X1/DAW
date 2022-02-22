/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.daw.subidaarchivosjsf.DAO;

import com.daw.subidaarchivosjsf.DAO.exceptions.NonexistentEntityException;
import com.daw.subidaarchivosjsf.DAO.exceptions.PreexistingEntityException;
import com.daw.subidaarchivosjsf.DTO.Vistacarrito;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author Usuario
 */
public class VistacarritoJpaController implements Serializable {

    public VistacarritoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Vistacarrito vistacarrito) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(vistacarrito);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findVistacarrito(vistacarrito.getUid()) != null) {
                throw new PreexistingEntityException("Vistacarrito " + vistacarrito + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Vistacarrito vistacarrito) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            vistacarrito = em.merge(vistacarrito);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = vistacarrito.getUid();
                if (findVistacarrito(id) == null) {
                    throw new NonexistentEntityException("The vistacarrito with id " + id + " no longer exists.");
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
            Vistacarrito vistacarrito;
            try {
                vistacarrito = em.getReference(Vistacarrito.class, id);
                vistacarrito.getUid();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The vistacarrito with id " + id + " no longer exists.", enfe);
            }
            em.remove(vistacarrito);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Vistacarrito> findVistacarritoEntities() {
        return findVistacarritoEntities(true, -1, -1);
    }

    public List<Vistacarrito> findVistacarritoEntities(int maxResults, int firstResult) {
        return findVistacarritoEntities(false, maxResults, firstResult);
    }

    private List<Vistacarrito> findVistacarritoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Vistacarrito.class));
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

    public Vistacarrito findVistacarrito(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Vistacarrito.class, id);
        } finally {
            em.close();
        }
    }

    public int getVistacarritoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Vistacarrito> rt = cq.from(Vistacarrito.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List carritoUsuario(String dni) {
        EntityManager em = getEntityManager();
        TypedQuery q = em.createNamedQuery("Vistacarrito.findByDniusuario", Vistacarrito.class);
        q.setParameter("dniusuario", dni);
        return q.getResultList();
    }
}
