package com.daw.peliculas.DAO;

import com.daw.peliculas.DAO.exceptions.IllegalOrphanException;
import com.daw.peliculas.DAO.exceptions.NonexistentEntityException;
import com.daw.peliculas.DTO.Director;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.daw.peliculas.DTO.Pelicula;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class DirectorJpaController implements Serializable {

    public DirectorJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Director director) {
        if (director.getPeliculaList() == null) {
            director.setPeliculaList(new ArrayList<Pelicula>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Pelicula> attachedPeliculaList = new ArrayList<Pelicula>();
            for (Pelicula peliculaListPeliculaToAttach : director.getPeliculaList()) {
                peliculaListPeliculaToAttach = em.getReference(peliculaListPeliculaToAttach.getClass(), peliculaListPeliculaToAttach.getCodPelicula());
                attachedPeliculaList.add(peliculaListPeliculaToAttach);
            }
            director.setPeliculaList(attachedPeliculaList);
            em.persist(director);
            for (Pelicula peliculaListPelicula : director.getPeliculaList()) {
                Director oldCodDirectorOfPeliculaListPelicula = peliculaListPelicula.getCodDirector();
                peliculaListPelicula.setCodDirector(director);
                peliculaListPelicula = em.merge(peliculaListPelicula);
                if (oldCodDirectorOfPeliculaListPelicula != null) {
                    oldCodDirectorOfPeliculaListPelicula.getPeliculaList().remove(peliculaListPelicula);
                    oldCodDirectorOfPeliculaListPelicula = em.merge(oldCodDirectorOfPeliculaListPelicula);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Director director) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Director persistentDirector = em.find(Director.class, director.getCodDIrector());
            List<Pelicula> peliculaListOld = persistentDirector.getPeliculaList();
            List<Pelicula> peliculaListNew = director.getPeliculaList();
            List<String> illegalOrphanMessages = null;
            for (Pelicula peliculaListOldPelicula : peliculaListOld) {
                if (!peliculaListNew.contains(peliculaListOldPelicula)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Pelicula " + peliculaListOldPelicula + " since its codDirector field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Pelicula> attachedPeliculaListNew = new ArrayList<Pelicula>();
            for (Pelicula peliculaListNewPeliculaToAttach : peliculaListNew) {
                peliculaListNewPeliculaToAttach = em.getReference(peliculaListNewPeliculaToAttach.getClass(), peliculaListNewPeliculaToAttach.getCodPelicula());
                attachedPeliculaListNew.add(peliculaListNewPeliculaToAttach);
            }
            peliculaListNew = attachedPeliculaListNew;
            director.setPeliculaList(peliculaListNew);
            director = em.merge(director);
            for (Pelicula peliculaListNewPelicula : peliculaListNew) {
                if (!peliculaListOld.contains(peliculaListNewPelicula)) {
                    Director oldCodDirectorOfPeliculaListNewPelicula = peliculaListNewPelicula.getCodDirector();
                    peliculaListNewPelicula.setCodDirector(director);
                    peliculaListNewPelicula = em.merge(peliculaListNewPelicula);
                    if (oldCodDirectorOfPeliculaListNewPelicula != null && !oldCodDirectorOfPeliculaListNewPelicula.equals(director)) {
                        oldCodDirectorOfPeliculaListNewPelicula.getPeliculaList().remove(peliculaListNewPelicula);
                        oldCodDirectorOfPeliculaListNewPelicula = em.merge(oldCodDirectorOfPeliculaListNewPelicula);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = director.getCodDIrector();
                if (findDirector(id) == null) {
                    throw new NonexistentEntityException("The director with id " + id + " no longer exists.");
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
            Director director;
            try {
                director = em.getReference(Director.class, id);
                director.getCodDIrector();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The director with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Pelicula> peliculaListOrphanCheck = director.getPeliculaList();
            for (Pelicula peliculaListOrphanCheckPelicula : peliculaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Director (" + director + ") cannot be destroyed since the Pelicula " + peliculaListOrphanCheckPelicula + " in its peliculaList field has a non-nullable codDirector field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(director);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Director> findDirectorEntities() {
        return findDirectorEntities(true, -1, -1);
    }

    public List<Director> findDirectorEntities(int maxResults, int firstResult) {
        return findDirectorEntities(false, maxResults, firstResult);
    }

    private List<Director> findDirectorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Director.class));
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

    public Director findDirector(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Director.class, id);
        } finally {
            em.close();
        }
    }

    public int getDirectorCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Director> rt = cq.from(Director.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
