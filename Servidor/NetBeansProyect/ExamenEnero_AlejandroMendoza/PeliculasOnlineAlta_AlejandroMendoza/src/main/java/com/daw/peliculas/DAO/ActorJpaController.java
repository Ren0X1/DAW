package com.daw.peliculas.DAO;

import com.daw.peliculas.DAO.exceptions.IllegalOrphanException;
import com.daw.peliculas.DAO.exceptions.NonexistentEntityException;
import com.daw.peliculas.DAO.exceptions.PreexistingEntityException;
import com.daw.peliculas.DTO.Actor;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.daw.peliculas.DTO.Reparto;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class ActorJpaController implements Serializable {

    public ActorJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Actor actor) throws PreexistingEntityException, Exception {
        if (actor.getRepartoList() == null) {
            actor.setRepartoList(new ArrayList<Reparto>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Reparto> attachedRepartoList = new ArrayList<Reparto>();
            for (Reparto repartoListRepartoToAttach : actor.getRepartoList()) {
                repartoListRepartoToAttach = em.getReference(repartoListRepartoToAttach.getClass(), repartoListRepartoToAttach.getRepartoPK());
                attachedRepartoList.add(repartoListRepartoToAttach);
            }
            actor.setRepartoList(attachedRepartoList);
            em.persist(actor);
            for (Reparto repartoListReparto : actor.getRepartoList()) {
                Actor oldActorOfRepartoListReparto = repartoListReparto.getActor();
                repartoListReparto.setActor(actor);
                repartoListReparto = em.merge(repartoListReparto);
                if (oldActorOfRepartoListReparto != null) {
                    oldActorOfRepartoListReparto.getRepartoList().remove(repartoListReparto);
                    oldActorOfRepartoListReparto = em.merge(oldActorOfRepartoListReparto);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findActor(actor.getCodActor()) != null) {
                throw new PreexistingEntityException("Actor " + actor + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Actor actor) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Actor persistentActor = em.find(Actor.class, actor.getCodActor());
            List<Reparto> repartoListOld = persistentActor.getRepartoList();
            List<Reparto> repartoListNew = actor.getRepartoList();
            List<String> illegalOrphanMessages = null;
            for (Reparto repartoListOldReparto : repartoListOld) {
                if (!repartoListNew.contains(repartoListOldReparto)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Reparto " + repartoListOldReparto + " since its actor field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Reparto> attachedRepartoListNew = new ArrayList<Reparto>();
            for (Reparto repartoListNewRepartoToAttach : repartoListNew) {
                repartoListNewRepartoToAttach = em.getReference(repartoListNewRepartoToAttach.getClass(), repartoListNewRepartoToAttach.getRepartoPK());
                attachedRepartoListNew.add(repartoListNewRepartoToAttach);
            }
            repartoListNew = attachedRepartoListNew;
            actor.setRepartoList(repartoListNew);
            actor = em.merge(actor);
            for (Reparto repartoListNewReparto : repartoListNew) {
                if (!repartoListOld.contains(repartoListNewReparto)) {
                    Actor oldActorOfRepartoListNewReparto = repartoListNewReparto.getActor();
                    repartoListNewReparto.setActor(actor);
                    repartoListNewReparto = em.merge(repartoListNewReparto);
                    if (oldActorOfRepartoListNewReparto != null && !oldActorOfRepartoListNewReparto.equals(actor)) {
                        oldActorOfRepartoListNewReparto.getRepartoList().remove(repartoListNewReparto);
                        oldActorOfRepartoListNewReparto = em.merge(oldActorOfRepartoListNewReparto);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = actor.getCodActor();
                if (findActor(id) == null) {
                    throw new NonexistentEntityException("The actor with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Actor actor;
            try {
                actor = em.getReference(Actor.class, id);
                actor.getCodActor();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The actor with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Reparto> repartoListOrphanCheck = actor.getRepartoList();
            for (Reparto repartoListOrphanCheckReparto : repartoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Actor (" + actor + ") cannot be destroyed since the Reparto " + repartoListOrphanCheckReparto + " in its repartoList field has a non-nullable actor field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(actor);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Actor> findActorEntities() {
        return findActorEntities(true, -1, -1);
    }

    public List<Actor> findActorEntities(int maxResults, int firstResult) {
        return findActorEntities(false, maxResults, firstResult);
    }

    private List<Actor> findActorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Actor.class));
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

    public Actor findActor(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Actor.class, id);
        } finally {
            em.close();
        }
    }

    public int getActorCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Actor> rt = cq.from(Actor.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
