package com.daw.institutorelacional.DAO;

import com.daw.institutorelacional.DAO.exceptions.IllegalOrphanException;
import com.daw.institutorelacional.DAO.exceptions.NonexistentEntityException;
import com.daw.institutorelacional.DTO.Asignatura;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.daw.institutorelacional.DTO.Profesor;
import com.daw.institutorelacional.DTO.Matricula;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class AsignaturaJpaController implements Serializable {

    public AsignaturaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Asignatura asignatura) {
        if (asignatura.getMatriculaList() == null) {
            asignatura.setMatriculaList(new ArrayList<Matricula>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Profesor dniprofesor = asignatura.getDniprofesor();
            if (dniprofesor != null) {
                dniprofesor = em.getReference(dniprofesor.getClass(), dniprofesor.getDniprofesor());
                asignatura.setDniprofesor(dniprofesor);
            }
            List<Matricula> attachedMatriculaList = new ArrayList<Matricula>();
            for (Matricula matriculaListMatriculaToAttach : asignatura.getMatriculaList()) {
                matriculaListMatriculaToAttach = em.getReference(matriculaListMatriculaToAttach.getClass(), matriculaListMatriculaToAttach.getMatriculaPK());
                attachedMatriculaList.add(matriculaListMatriculaToAttach);
            }
            asignatura.setMatriculaList(attachedMatriculaList);
            em.persist(asignatura);
            if (dniprofesor != null) {
                dniprofesor.getAsignaturaList().add(asignatura);
                dniprofesor = em.merge(dniprofesor);
            }
            for (Matricula matriculaListMatricula : asignatura.getMatriculaList()) {
                Asignatura oldAsignaturaOfMatriculaListMatricula = matriculaListMatricula.getAsignatura();
                matriculaListMatricula.setAsignatura(asignatura);
                matriculaListMatricula = em.merge(matriculaListMatricula);
                if (oldAsignaturaOfMatriculaListMatricula != null) {
                    oldAsignaturaOfMatriculaListMatricula.getMatriculaList().remove(matriculaListMatricula);
                    oldAsignaturaOfMatriculaListMatricula = em.merge(oldAsignaturaOfMatriculaListMatricula);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Asignatura asignatura) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Asignatura persistentAsignatura = em.find(Asignatura.class, asignatura.getCodasignatura());
            Profesor dniprofesorOld = persistentAsignatura.getDniprofesor();
            Profesor dniprofesorNew = asignatura.getDniprofesor();
            List<Matricula> matriculaListOld = persistentAsignatura.getMatriculaList();
            List<Matricula> matriculaListNew = asignatura.getMatriculaList();
            List<String> illegalOrphanMessages = null;
            for (Matricula matriculaListOldMatricula : matriculaListOld) {
                if (!matriculaListNew.contains(matriculaListOldMatricula)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Matricula " + matriculaListOldMatricula + " since its asignatura field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (dniprofesorNew != null) {
                dniprofesorNew = em.getReference(dniprofesorNew.getClass(), dniprofesorNew.getDniprofesor());
                asignatura.setDniprofesor(dniprofesorNew);
            }
            List<Matricula> attachedMatriculaListNew = new ArrayList<Matricula>();
            for (Matricula matriculaListNewMatriculaToAttach : matriculaListNew) {
                matriculaListNewMatriculaToAttach = em.getReference(matriculaListNewMatriculaToAttach.getClass(), matriculaListNewMatriculaToAttach.getMatriculaPK());
                attachedMatriculaListNew.add(matriculaListNewMatriculaToAttach);
            }
            matriculaListNew = attachedMatriculaListNew;
            asignatura.setMatriculaList(matriculaListNew);
            asignatura = em.merge(asignatura);
            if (dniprofesorOld != null && !dniprofesorOld.equals(dniprofesorNew)) {
                dniprofesorOld.getAsignaturaList().remove(asignatura);
                dniprofesorOld = em.merge(dniprofesorOld);
            }
            if (dniprofesorNew != null && !dniprofesorNew.equals(dniprofesorOld)) {
                dniprofesorNew.getAsignaturaList().add(asignatura);
                dniprofesorNew = em.merge(dniprofesorNew);
            }
            for (Matricula matriculaListNewMatricula : matriculaListNew) {
                if (!matriculaListOld.contains(matriculaListNewMatricula)) {
                    Asignatura oldAsignaturaOfMatriculaListNewMatricula = matriculaListNewMatricula.getAsignatura();
                    matriculaListNewMatricula.setAsignatura(asignatura);
                    matriculaListNewMatricula = em.merge(matriculaListNewMatricula);
                    if (oldAsignaturaOfMatriculaListNewMatricula != null && !oldAsignaturaOfMatriculaListNewMatricula.equals(asignatura)) {
                        oldAsignaturaOfMatriculaListNewMatricula.getMatriculaList().remove(matriculaListNewMatricula);
                        oldAsignaturaOfMatriculaListNewMatricula = em.merge(oldAsignaturaOfMatriculaListNewMatricula);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = asignatura.getCodasignatura();
                if (findAsignatura(id) == null) {
                    throw new NonexistentEntityException("The asignatura with id " + id + " no longer exists.");
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
            Asignatura asignatura;
            try {
                asignatura = em.getReference(Asignatura.class, id);
                asignatura.getCodasignatura();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The asignatura with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Matricula> matriculaListOrphanCheck = asignatura.getMatriculaList();
            for (Matricula matriculaListOrphanCheckMatricula : matriculaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Asignatura (" + asignatura + ") cannot be destroyed since the Matricula " + matriculaListOrphanCheckMatricula + " in its matriculaList field has a non-nullable asignatura field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Profesor dniprofesor = asignatura.getDniprofesor();
            if (dniprofesor != null) {
                dniprofesor.getAsignaturaList().remove(asignatura);
                dniprofesor = em.merge(dniprofesor);
            }
            em.remove(asignatura);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Asignatura> findAsignaturaEntities() {
        return findAsignaturaEntities(true, -1, -1);
    }

    public List<Asignatura> findAsignaturaEntities(int maxResults, int firstResult) {
        return findAsignaturaEntities(false, maxResults, firstResult);
    }

    private List<Asignatura> findAsignaturaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Asignatura.class));
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

    public Asignatura findAsignatura(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Asignatura.class, id);
        } finally {
            em.close();
        }
    }

    public int getAsignaturaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Asignatura> rt = cq.from(Asignatura.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
