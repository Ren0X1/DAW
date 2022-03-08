package com.daw.juegapalabrasjsf_alejandromendoza.DAO;

import com.daw.juegapalabrasjsf_alejandromendoza.DAO.exceptions.NonexistentEntityException;
import com.daw.juegapalabrasjsf_alejandromendoza.DAO.exceptions.PreexistingEntityException;
import com.daw.juegapalabrasjsf_alejandromendoza.DTO.Vistawords;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class VistawordsJpaController implements Serializable {

    public VistawordsJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Vistawords vistawords) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(vistawords);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findVistawords(vistawords.getUuid()) != null) {
                throw new PreexistingEntityException("Vistawords " + vistawords + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Vistawords vistawords) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            vistawords = em.merge(vistawords);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = vistawords.getUuid();
                if (findVistawords(id) == null) {
                    throw new NonexistentEntityException("The vistawords with id " + id + " no longer exists.");
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
            Vistawords vistawords;
            try {
                vistawords = em.getReference(Vistawords.class, id);
                vistawords.getUuid();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The vistawords with id " + id + " no longer exists.", enfe);
            }
            em.remove(vistawords);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Vistawords> findVistawordsEntities() {
        return findVistawordsEntities(true, -1, -1);
    }

    public List<Vistawords> findVistawordsEntities(int maxResults, int firstResult) {
        return findVistawordsEntities(false, maxResults, firstResult);
    }

    private List<Vistawords> findVistawordsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Vistawords.class));
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

    public Vistawords findVistawords(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Vistawords.class, id);
        } finally {
            em.close();
        }
    }

    public int getVistawordsCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Vistawords> rt = cq.from(Vistawords.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List findById_(int c){
        EntityManager em = getEntityManager();
        TypedQuery query=em.createNamedQuery("Vistawords.findByCodpalSp", Vistawords.class);
        query.setParameter("codpalSp", c);
        return query.getResultList();
    }
}
