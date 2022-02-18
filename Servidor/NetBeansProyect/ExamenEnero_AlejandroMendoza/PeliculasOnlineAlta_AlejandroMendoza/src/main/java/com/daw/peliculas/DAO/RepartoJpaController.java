package com.daw.peliculas.DAO;

import com.daw.peliculas.DAO.exceptions.NonexistentEntityException;
import com.daw.peliculas.DAO.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.daw.peliculas.DTO.Pelicula;
import com.daw.peliculas.DTO.Actor;
import com.daw.peliculas.DTO.Reparto;
import com.daw.peliculas.DTO.RepartoPK;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

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
        reparto.getRepartoPK().setCodActor(reparto.getActor().getCodActor());
        reparto.getRepartoPK().setCodPelicula(reparto.getPelicula().getCodPelicula());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Pelicula pelicula = reparto.getPelicula();
            if (pelicula != null) {
                pelicula = em.getReference(pelicula.getClass(), pelicula.getCodPelicula());
                reparto.setPelicula(pelicula);
            }
            Actor actor = reparto.getActor();
            if (actor != null) {
                actor = em.getReference(actor.getClass(), actor.getCodActor());
                reparto.setActor(actor);
            }
            em.persist(reparto);
            if (pelicula != null) {
                pelicula.getRepartoList().add(reparto);
                pelicula = em.merge(pelicula);
            }
            if (actor != null) {
                actor.getRepartoList().add(reparto);
                actor = em.merge(actor);
            }
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
        reparto.getRepartoPK().setCodActor(reparto.getActor().getCodActor());
        reparto.getRepartoPK().setCodPelicula(reparto.getPelicula().getCodPelicula());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Reparto persistentReparto = em.find(Reparto.class, reparto.getRepartoPK());
            Pelicula peliculaOld = persistentReparto.getPelicula();
            Pelicula peliculaNew = reparto.getPelicula();
            Actor actorOld = persistentReparto.getActor();
            Actor actorNew = reparto.getActor();
            if (peliculaNew != null) {
                peliculaNew = em.getReference(peliculaNew.getClass(), peliculaNew.getCodPelicula());
                reparto.setPelicula(peliculaNew);
            }
            if (actorNew != null) {
                actorNew = em.getReference(actorNew.getClass(), actorNew.getCodActor());
                reparto.setActor(actorNew);
            }
            reparto = em.merge(reparto);
            if (peliculaOld != null && !peliculaOld.equals(peliculaNew)) {
                peliculaOld.getRepartoList().remove(reparto);
                peliculaOld = em.merge(peliculaOld);
            }
            if (peliculaNew != null && !peliculaNew.equals(peliculaOld)) {
                peliculaNew.getRepartoList().add(reparto);
                peliculaNew = em.merge(peliculaNew);
            }
            if (actorOld != null && !actorOld.equals(actorNew)) {
                actorOld.getRepartoList().remove(reparto);
                actorOld = em.merge(actorOld);
            }
            if (actorNew != null && !actorNew.equals(actorOld)) {
                actorNew.getRepartoList().add(reparto);
                actorNew = em.merge(actorNew);
            }
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
            Pelicula pelicula = reparto.getPelicula();
            if (pelicula != null) {
                pelicula.getRepartoList().remove(reparto);
                pelicula = em.merge(pelicula);
            }
            Actor actor = reparto.getActor();
            if (actor != null) {
                actor.getRepartoList().remove(reparto);
                actor = em.merge(actor);
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
