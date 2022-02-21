package com.daw.floristeriajsf.DAO;

import com.daw.floristeriajsf.DAO.exceptions.NonexistentEntityException;
import com.daw.floristeriajsf.DTO.Flores;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class FloresJpaController implements Serializable {

    public FloresJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Flores flores) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(flores);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Flores flores) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            flores = em.merge(flores);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = flores.getCodigoFlor();
                if (findFlores(id) == null) {
                    throw new NonexistentEntityException("The flores with id " + id + " no longer exists.");
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
            Flores flores;
            try {
                flores = em.getReference(Flores.class, id);
                flores.getCodigoFlor();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The flores with id " + id + " no longer exists.", enfe);
            }
            em.remove(flores);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Flores> findFloresEntities() {
        return findFloresEntities(true, -1, -1);
    }

    public List<Flores> findFloresEntities(int maxResults, int firstResult) {
        return findFloresEntities(false, maxResults, firstResult);
    }

    private List<Flores> findFloresEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Flores.class));
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

    public Flores findFlores(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Flores.class, id);
        } finally {
            em.close();
        }
    }

    public int getFloresCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Flores> rt = cq.from(Flores.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
