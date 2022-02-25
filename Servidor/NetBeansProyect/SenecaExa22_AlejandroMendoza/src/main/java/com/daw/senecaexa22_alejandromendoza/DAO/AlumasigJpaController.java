package com.daw.senecaexa22_alejandromendoza.DAO;

import com.daw.senecaexa22_alejandromendoza.DAO.exceptions.NonexistentEntityException;
import com.daw.senecaexa22_alejandromendoza.DAO.exceptions.PreexistingEntityException;
import com.daw.senecaexa22_alejandromendoza.DTO.Alumasig;
import com.daw.senecaexa22_alejandromendoza.DTO.AlumasigPK;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class AlumasigJpaController implements Serializable {

    public AlumasigJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Alumasig alumasig) throws PreexistingEntityException, Exception {
        if (alumasig.getAlumasigPK() == null) {
            alumasig.setAlumasigPK(new AlumasigPK());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(alumasig);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findAlumasig(alumasig.getAlumasigPK()) != null) {
                throw new PreexistingEntityException("Alumasig " + alumasig + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Alumasig alumasig) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            alumasig = em.merge(alumasig);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                AlumasigPK id = alumasig.getAlumasigPK();
                if (findAlumasig(id) == null) {
                    throw new NonexistentEntityException("The alumasig with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(AlumasigPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Alumasig alumasig;
            try {
                alumasig = em.getReference(Alumasig.class, id);
                alumasig.getAlumasigPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The alumasig with id " + id + " no longer exists.", enfe);
            }
            em.remove(alumasig);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Alumasig> findAlumasigEntities() {
        return findAlumasigEntities(true, -1, -1);
    }

    public List<Alumasig> findAlumasigEntities(int maxResults, int firstResult) {
        return findAlumasigEntities(false, maxResults, firstResult);
    }

    private List<Alumasig> findAlumasigEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Alumasig.class));
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

    public Alumasig findAlumasig(AlumasigPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Alumasig.class, id);
        } finally {
            em.close();
        }
    }

    public int getAlumasigCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Alumasig> rt = cq.from(Alumasig.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
