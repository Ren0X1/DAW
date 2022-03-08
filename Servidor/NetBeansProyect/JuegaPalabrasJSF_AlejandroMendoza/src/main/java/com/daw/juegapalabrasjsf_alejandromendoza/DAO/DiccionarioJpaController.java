package com.daw.juegapalabrasjsf_alejandromendoza.DAO;

import com.daw.juegapalabrasjsf_alejandromendoza.DAO.exceptions.NonexistentEntityException;
import com.daw.juegapalabrasjsf_alejandromendoza.DAO.exceptions.PreexistingEntityException;
import com.daw.juegapalabrasjsf_alejandromendoza.DTO.Diccionario;
import com.daw.juegapalabrasjsf_alejandromendoza.DTO.DiccionarioPK;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class DiccionarioJpaController implements Serializable {

    public DiccionarioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Diccionario diccionario) throws PreexistingEntityException, Exception {
        if (diccionario.getDiccionarioPK() == null) {
            diccionario.setDiccionarioPK(new DiccionarioPK());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(diccionario);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findDiccionario(diccionario.getDiccionarioPK()) != null) {
                throw new PreexistingEntityException("Diccionario " + diccionario + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Diccionario diccionario) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            diccionario = em.merge(diccionario);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                DiccionarioPK id = diccionario.getDiccionarioPK();
                if (findDiccionario(id) == null) {
                    throw new NonexistentEntityException("The diccionario with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(DiccionarioPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Diccionario diccionario;
            try {
                diccionario = em.getReference(Diccionario.class, id);
                diccionario.getDiccionarioPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The diccionario with id " + id + " no longer exists.", enfe);
            }
            em.remove(diccionario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Diccionario> findDiccionarioEntities() {
        return findDiccionarioEntities(true, -1, -1);
    }

    public List<Diccionario> findDiccionarioEntities(int maxResults, int firstResult) {
        return findDiccionarioEntities(false, maxResults, firstResult);
    }

    private List<Diccionario> findDiccionarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Diccionario.class));
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

    public Diccionario findDiccionario(DiccionarioPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Diccionario.class, id);
        } finally {
            em.close();
        }
    }

    public int getDiccionarioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Diccionario> rt = cq.from(Diccionario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
