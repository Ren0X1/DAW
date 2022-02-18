package com.daw.peliculas.DAO;

import com.daw.peliculas.DAO.exceptions.IllegalOrphanException;
import com.daw.peliculas.DAO.exceptions.NonexistentEntityException;
import com.daw.peliculas.DTO.Genero;
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

public class GeneroJpaController implements Serializable {

    public GeneroJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Genero genero) {
        if (genero.getPeliculaList() == null) {
            genero.setPeliculaList(new ArrayList<Pelicula>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Pelicula> attachedPeliculaList = new ArrayList<Pelicula>();
            for (Pelicula peliculaListPeliculaToAttach : genero.getPeliculaList()) {
                peliculaListPeliculaToAttach = em.getReference(peliculaListPeliculaToAttach.getClass(), peliculaListPeliculaToAttach.getCodPelicula());
                attachedPeliculaList.add(peliculaListPeliculaToAttach);
            }
            genero.setPeliculaList(attachedPeliculaList);
            em.persist(genero);
            for (Pelicula peliculaListPelicula : genero.getPeliculaList()) {
                Genero oldCodGeneroOfPeliculaListPelicula = peliculaListPelicula.getCodGenero();
                peliculaListPelicula.setCodGenero(genero);
                peliculaListPelicula = em.merge(peliculaListPelicula);
                if (oldCodGeneroOfPeliculaListPelicula != null) {
                    oldCodGeneroOfPeliculaListPelicula.getPeliculaList().remove(peliculaListPelicula);
                    oldCodGeneroOfPeliculaListPelicula = em.merge(oldCodGeneroOfPeliculaListPelicula);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Genero genero) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Genero persistentGenero = em.find(Genero.class, genero.getCodGenero());
            List<Pelicula> peliculaListOld = persistentGenero.getPeliculaList();
            List<Pelicula> peliculaListNew = genero.getPeliculaList();
            List<String> illegalOrphanMessages = null;
            for (Pelicula peliculaListOldPelicula : peliculaListOld) {
                if (!peliculaListNew.contains(peliculaListOldPelicula)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Pelicula " + peliculaListOldPelicula + " since its codGenero field is not nullable.");
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
            genero.setPeliculaList(peliculaListNew);
            genero = em.merge(genero);
            for (Pelicula peliculaListNewPelicula : peliculaListNew) {
                if (!peliculaListOld.contains(peliculaListNewPelicula)) {
                    Genero oldCodGeneroOfPeliculaListNewPelicula = peliculaListNewPelicula.getCodGenero();
                    peliculaListNewPelicula.setCodGenero(genero);
                    peliculaListNewPelicula = em.merge(peliculaListNewPelicula);
                    if (oldCodGeneroOfPeliculaListNewPelicula != null && !oldCodGeneroOfPeliculaListNewPelicula.equals(genero)) {
                        oldCodGeneroOfPeliculaListNewPelicula.getPeliculaList().remove(peliculaListNewPelicula);
                        oldCodGeneroOfPeliculaListNewPelicula = em.merge(oldCodGeneroOfPeliculaListNewPelicula);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = genero.getCodGenero();
                if (findGenero(id) == null) {
                    throw new NonexistentEntityException("The genero with id " + id + " no longer exists.");
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
            Genero genero;
            try {
                genero = em.getReference(Genero.class, id);
                genero.getCodGenero();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The genero with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Pelicula> peliculaListOrphanCheck = genero.getPeliculaList();
            for (Pelicula peliculaListOrphanCheckPelicula : peliculaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Genero (" + genero + ") cannot be destroyed since the Pelicula " + peliculaListOrphanCheckPelicula + " in its peliculaList field has a non-nullable codGenero field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(genero);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Genero> findGeneroEntities() {
        return findGeneroEntities(true, -1, -1);
    }

    public List<Genero> findGeneroEntities(int maxResults, int firstResult) {
        return findGeneroEntities(false, maxResults, firstResult);
    }

    private List<Genero> findGeneroEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Genero.class));
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

    public Genero findGenero(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Genero.class, id);
        } finally {
            em.close();
        }
    }

    public int getGeneroCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Genero> rt = cq.from(Genero.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
