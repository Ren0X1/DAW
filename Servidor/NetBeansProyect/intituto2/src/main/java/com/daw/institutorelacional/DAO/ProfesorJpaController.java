/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.daw.institutorelacional.DAO;

import com.daw.institutorelacional.DAO.exceptions.IllegalOrphanException;
import com.daw.institutorelacional.DAO.exceptions.NonexistentEntityException;
import com.daw.institutorelacional.DAO.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.daw.institutorelacional.DTO.Asignatura;
import com.daw.institutorelacional.DTO.Profesor;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Christian
 */
public class ProfesorJpaController implements Serializable {

    public ProfesorJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Profesor profesor) throws PreexistingEntityException, Exception {
        if (profesor.getAsignaturaList() == null) {
            profesor.setAsignaturaList(new ArrayList<Asignatura>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Asignatura> attachedAsignaturaList = new ArrayList<Asignatura>();
            for (Asignatura asignaturaListAsignaturaToAttach : profesor.getAsignaturaList()) {
                asignaturaListAsignaturaToAttach = em.getReference(asignaturaListAsignaturaToAttach.getClass(), asignaturaListAsignaturaToAttach.getCodasignatura());
                attachedAsignaturaList.add(asignaturaListAsignaturaToAttach);
            }
            profesor.setAsignaturaList(attachedAsignaturaList);
            em.persist(profesor);
            for (Asignatura asignaturaListAsignatura : profesor.getAsignaturaList()) {
                Profesor oldDniprofesorOfAsignaturaListAsignatura = asignaturaListAsignatura.getDniprofesor();
                asignaturaListAsignatura.setDniprofesor(profesor);
                asignaturaListAsignatura = em.merge(asignaturaListAsignatura);
                if (oldDniprofesorOfAsignaturaListAsignatura != null) {
                    oldDniprofesorOfAsignaturaListAsignatura.getAsignaturaList().remove(asignaturaListAsignatura);
                    oldDniprofesorOfAsignaturaListAsignatura = em.merge(oldDniprofesorOfAsignaturaListAsignatura);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findProfesor(profesor.getDniprofesor()) != null) {
                throw new PreexistingEntityException("Profesor " + profesor + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Profesor profesor) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Profesor persistentProfesor = em.find(Profesor.class, profesor.getDniprofesor());
            List<Asignatura> asignaturaListOld = persistentProfesor.getAsignaturaList();
            List<Asignatura> asignaturaListNew = profesor.getAsignaturaList();
            List<String> illegalOrphanMessages = null;
            for (Asignatura asignaturaListOldAsignatura : asignaturaListOld) {
                if (!asignaturaListNew.contains(asignaturaListOldAsignatura)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Asignatura " + asignaturaListOldAsignatura + " since its dniprofesor field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Asignatura> attachedAsignaturaListNew = new ArrayList<Asignatura>();
            for (Asignatura asignaturaListNewAsignaturaToAttach : asignaturaListNew) {
                asignaturaListNewAsignaturaToAttach = em.getReference(asignaturaListNewAsignaturaToAttach.getClass(), asignaturaListNewAsignaturaToAttach.getCodasignatura());
                attachedAsignaturaListNew.add(asignaturaListNewAsignaturaToAttach);
            }
            asignaturaListNew = attachedAsignaturaListNew;
            profesor.setAsignaturaList(asignaturaListNew);
            profesor = em.merge(profesor);
            for (Asignatura asignaturaListNewAsignatura : asignaturaListNew) {
                if (!asignaturaListOld.contains(asignaturaListNewAsignatura)) {
                    Profesor oldDniprofesorOfAsignaturaListNewAsignatura = asignaturaListNewAsignatura.getDniprofesor();
                    asignaturaListNewAsignatura.setDniprofesor(profesor);
                    asignaturaListNewAsignatura = em.merge(asignaturaListNewAsignatura);
                    if (oldDniprofesorOfAsignaturaListNewAsignatura != null && !oldDniprofesorOfAsignaturaListNewAsignatura.equals(profesor)) {
                        oldDniprofesorOfAsignaturaListNewAsignatura.getAsignaturaList().remove(asignaturaListNewAsignatura);
                        oldDniprofesorOfAsignaturaListNewAsignatura = em.merge(oldDniprofesorOfAsignaturaListNewAsignatura);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = profesor.getDniprofesor();
                if (findProfesor(id) == null) {
                    throw new NonexistentEntityException("The profesor with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Profesor profesor;
            try {
                profesor = em.getReference(Profesor.class, id);
                profesor.getDniprofesor();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The profesor with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Asignatura> asignaturaListOrphanCheck = profesor.getAsignaturaList();
            for (Asignatura asignaturaListOrphanCheckAsignatura : asignaturaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Profesor (" + profesor + ") cannot be destroyed since the Asignatura " + asignaturaListOrphanCheckAsignatura + " in its asignaturaList field has a non-nullable dniprofesor field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(profesor);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Profesor> findProfesorEntities() {
        return findProfesorEntities(true, -1, -1);
    }

    public List<Profesor> findProfesorEntities(int maxResults, int firstResult) {
        return findProfesorEntities(false, maxResults, firstResult);
    }

    private List<Profesor> findProfesorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Profesor.class));
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

    public Profesor findProfesor(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Profesor.class, id);
        } finally {
            em.close();
        }
    }

    public int getProfesorCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Profesor> rt = cq.from(Profesor.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
