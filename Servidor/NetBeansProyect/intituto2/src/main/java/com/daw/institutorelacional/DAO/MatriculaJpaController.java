/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.daw.institutorelacional.DAO;

import com.daw.institutorelacional.DAO.exceptions.NonexistentEntityException;
import com.daw.institutorelacional.DAO.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.daw.institutorelacional.DTO.Asignatura;
import com.daw.institutorelacional.DTO.Alumno;
import com.daw.institutorelacional.DTO.Matricula;
import com.daw.institutorelacional.DTO.MatriculaPK;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Christian
 */
public class MatriculaJpaController implements Serializable {

    public MatriculaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Matricula matricula) throws PreexistingEntityException, Exception {
        if (matricula.getMatriculaPK() == null) {
            matricula.setMatriculaPK(new MatriculaPK());
        }
        matricula.getMatriculaPK().setCodasignatura(matricula.getAsignatura().getCodasignatura());
        matricula.getMatriculaPK().setNmatriculaAlum(matricula.getAlumno().getNmatricula());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Asignatura asignatura = matricula.getAsignatura();
            if (asignatura != null) {
                asignatura = em.getReference(asignatura.getClass(), asignatura.getCodasignatura());
                matricula.setAsignatura(asignatura);
            }
            Alumno alumno = matricula.getAlumno();
            if (alumno != null) {
                alumno = em.getReference(alumno.getClass(), alumno.getNmatricula());
                matricula.setAlumno(alumno);
            }
            em.persist(matricula);
            if (asignatura != null) {
                asignatura.getMatriculaList().add(matricula);
                asignatura = em.merge(asignatura);
            }
            if (alumno != null) {
                alumno.getMatriculaList().add(matricula);
                alumno = em.merge(alumno);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findMatricula(matricula.getMatriculaPK()) != null) {
                throw new PreexistingEntityException("Matricula " + matricula + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Matricula matricula) throws NonexistentEntityException, Exception {
        matricula.getMatriculaPK().setCodasignatura(matricula.getAsignatura().getCodasignatura());
        matricula.getMatriculaPK().setNmatriculaAlum(matricula.getAlumno().getNmatricula());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Matricula persistentMatricula = em.find(Matricula.class, matricula.getMatriculaPK());
            Asignatura asignaturaOld = persistentMatricula.getAsignatura();
            Asignatura asignaturaNew = matricula.getAsignatura();
            Alumno alumnoOld = persistentMatricula.getAlumno();
            Alumno alumnoNew = matricula.getAlumno();
            if (asignaturaNew != null) {
                asignaturaNew = em.getReference(asignaturaNew.getClass(), asignaturaNew.getCodasignatura());
                matricula.setAsignatura(asignaturaNew);
            }
            if (alumnoNew != null) {
                alumnoNew = em.getReference(alumnoNew.getClass(), alumnoNew.getNmatricula());
                matricula.setAlumno(alumnoNew);
            }
            matricula = em.merge(matricula);
            if (asignaturaOld != null && !asignaturaOld.equals(asignaturaNew)) {
                asignaturaOld.getMatriculaList().remove(matricula);
                asignaturaOld = em.merge(asignaturaOld);
            }
            if (asignaturaNew != null && !asignaturaNew.equals(asignaturaOld)) {
                asignaturaNew.getMatriculaList().add(matricula);
                asignaturaNew = em.merge(asignaturaNew);
            }
            if (alumnoOld != null && !alumnoOld.equals(alumnoNew)) {
                alumnoOld.getMatriculaList().remove(matricula);
                alumnoOld = em.merge(alumnoOld);
            }
            if (alumnoNew != null && !alumnoNew.equals(alumnoOld)) {
                alumnoNew.getMatriculaList().add(matricula);
                alumnoNew = em.merge(alumnoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                MatriculaPK id = matricula.getMatriculaPK();
                if (findMatricula(id) == null) {
                    throw new NonexistentEntityException("The matricula with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(MatriculaPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Matricula matricula;
            try {
                matricula = em.getReference(Matricula.class, id);
                matricula.getMatriculaPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The matricula with id " + id + " no longer exists.", enfe);
            }
            Asignatura asignatura = matricula.getAsignatura();
            if (asignatura != null) {
                asignatura.getMatriculaList().remove(matricula);
                asignatura = em.merge(asignatura);
            }
            Alumno alumno = matricula.getAlumno();
            if (alumno != null) {
                alumno.getMatriculaList().remove(matricula);
                alumno = em.merge(alumno);
            }
            em.remove(matricula);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Matricula> findMatriculaEntities() {
        return findMatriculaEntities(true, -1, -1);
    }

    public List<Matricula> findMatriculaEntities(int maxResults, int firstResult) {
        return findMatriculaEntities(false, maxResults, firstResult);
    }

    private List<Matricula> findMatriculaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Matricula.class));
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

    public Matricula findMatricula(MatriculaPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Matricula.class, id);
        } finally {
            em.close();
        }
    }

    public int getMatriculaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Matricula> rt = cq.from(Matricula.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
