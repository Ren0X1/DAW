package com.daw.mantenimiento.DAO;

import com.daw.mantenimiento.DAO.exceptions.NonexistentEntityException;
import com.daw.mantenimiento.DTO.Peliculas;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class PeliculasJpaController implements Serializable {

    public PeliculasJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Peliculas peliculas) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(peliculas);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Peliculas peliculas) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            peliculas = em.merge(peliculas);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = peliculas.getCodigoPelicula();
                if (findPeliculas(id) == null) {
                    throw new NonexistentEntityException("The peliculas with id " + id + " no longer exists.");
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
            Peliculas peliculas;
            try {
                peliculas = em.getReference(Peliculas.class, id);
                peliculas.getCodigoPelicula();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The peliculas with id " + id + " no longer exists.", enfe);
            }
            em.remove(peliculas);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Peliculas> findPeliculasEntities() {
        return findPeliculasEntities(true, -1, -1);
    }

    public List<Peliculas> findPeliculasEntities(int maxResults, int firstResult) {
        return findPeliculasEntities(false, maxResults, firstResult);
    }

    private List<Peliculas> findPeliculasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Peliculas.class));
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

    public Peliculas findPeliculas(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Peliculas.class, id);
        } finally {
            em.close();
        }
    }

    public int getPeliculasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Peliculas> rt = cq.from(Peliculas.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public List obtenerCabecera() {
        EntityManager em = getEntityManager();
        //Query q = em.createNativeQuery("select COLUMN_NAME from INFORMATION_SCHEMA.COLUMNS where TABLE_SCHEMA = 'peliculas' and TABLE_NAME = 'peliculas'order by ORDINAL_POSITION");
        //Query q = em.createNativeQuery("SELECT `COLUMN_NAME` FROM `INFORMATION_SCHEMA`.`COLUMNS` WHERE `TABLE_NAME`='peliculas'");
        Query q = em.createNativeQuery("show columns from peliculas");
        return q.getResultList();
    }

}
