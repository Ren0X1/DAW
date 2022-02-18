package com.daw.peliculas.DAO;

import com.daw.peliculas.DAO.exceptions.IllegalOrphanException;
import com.daw.peliculas.DAO.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.daw.peliculas.DTO.Director;
import com.daw.peliculas.DTO.Genero;
import com.daw.peliculas.DTO.Reparto;
import java.util.ArrayList;
import java.util.List;
import com.daw.peliculas.DTO.Opinion;
import com.daw.peliculas.DTO.Pelicula;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class PeliculaJpaController implements Serializable {

    public PeliculaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Pelicula pelicula) {
        if (pelicula.getRepartoList() == null) {
            pelicula.setRepartoList(new ArrayList<Reparto>());
        }
        if (pelicula.getOpinionList() == null) {
            pelicula.setOpinionList(new ArrayList<Opinion>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Director codDirector = pelicula.getCodDirector();
            if (codDirector != null) {
                codDirector = em.getReference(codDirector.getClass(), codDirector.getCodDIrector());
                pelicula.setCodDirector(codDirector);
            }
            Genero codGenero = pelicula.getCodGenero();
            if (codGenero != null) {
                codGenero = em.getReference(codGenero.getClass(), codGenero.getCodGenero());
                pelicula.setCodGenero(codGenero);
            }
            List<Reparto> attachedRepartoList = new ArrayList<Reparto>();
            for (Reparto repartoListRepartoToAttach : pelicula.getRepartoList()) {
                repartoListRepartoToAttach = em.getReference(repartoListRepartoToAttach.getClass(), repartoListRepartoToAttach.getRepartoPK());
                attachedRepartoList.add(repartoListRepartoToAttach);
            }
            pelicula.setRepartoList(attachedRepartoList);
            List<Opinion> attachedOpinionList = new ArrayList<Opinion>();
            for (Opinion opinionListOpinionToAttach : pelicula.getOpinionList()) {
                opinionListOpinionToAttach = em.getReference(opinionListOpinionToAttach.getClass(), opinionListOpinionToAttach.getCodComen());
                attachedOpinionList.add(opinionListOpinionToAttach);
            }
            pelicula.setOpinionList(attachedOpinionList);
            em.persist(pelicula);
            if (codDirector != null) {
                codDirector.getPeliculaList().add(pelicula);
                codDirector = em.merge(codDirector);
            }
            if (codGenero != null) {
                codGenero.getPeliculaList().add(pelicula);
                codGenero = em.merge(codGenero);
            }
            for (Reparto repartoListReparto : pelicula.getRepartoList()) {
                Pelicula oldPeliculaOfRepartoListReparto = repartoListReparto.getPelicula();
                repartoListReparto.setPelicula(pelicula);
                repartoListReparto = em.merge(repartoListReparto);
                if (oldPeliculaOfRepartoListReparto != null) {
                    oldPeliculaOfRepartoListReparto.getRepartoList().remove(repartoListReparto);
                    oldPeliculaOfRepartoListReparto = em.merge(oldPeliculaOfRepartoListReparto);
                }
            }
            for (Opinion opinionListOpinion : pelicula.getOpinionList()) {
                Pelicula oldCodPeliculaOfOpinionListOpinion = opinionListOpinion.getCodPelicula();
                opinionListOpinion.setCodPelicula(pelicula);
                opinionListOpinion = em.merge(opinionListOpinion);
                if (oldCodPeliculaOfOpinionListOpinion != null) {
                    oldCodPeliculaOfOpinionListOpinion.getOpinionList().remove(opinionListOpinion);
                    oldCodPeliculaOfOpinionListOpinion = em.merge(oldCodPeliculaOfOpinionListOpinion);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Pelicula pelicula) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Pelicula persistentPelicula = em.find(Pelicula.class, pelicula.getCodPelicula());
            Director codDirectorOld = persistentPelicula.getCodDirector();
            Director codDirectorNew = pelicula.getCodDirector();
            Genero codGeneroOld = persistentPelicula.getCodGenero();
            Genero codGeneroNew = pelicula.getCodGenero();
            List<Reparto> repartoListOld = persistentPelicula.getRepartoList();
            List<Reparto> repartoListNew = pelicula.getRepartoList();
            List<Opinion> opinionListOld = persistentPelicula.getOpinionList();
            List<Opinion> opinionListNew = pelicula.getOpinionList();
            List<String> illegalOrphanMessages = null;
            for (Reparto repartoListOldReparto : repartoListOld) {
                if (!repartoListNew.contains(repartoListOldReparto)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Reparto " + repartoListOldReparto + " since its pelicula field is not nullable.");
                }
            }
            for (Opinion opinionListOldOpinion : opinionListOld) {
                if (!opinionListNew.contains(opinionListOldOpinion)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Opinion " + opinionListOldOpinion + " since its codPelicula field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (codDirectorNew != null) {
                codDirectorNew = em.getReference(codDirectorNew.getClass(), codDirectorNew.getCodDIrector());
                pelicula.setCodDirector(codDirectorNew);
            }
            if (codGeneroNew != null) {
                codGeneroNew = em.getReference(codGeneroNew.getClass(), codGeneroNew.getCodGenero());
                pelicula.setCodGenero(codGeneroNew);
            }
            List<Reparto> attachedRepartoListNew = new ArrayList<Reparto>();
            for (Reparto repartoListNewRepartoToAttach : repartoListNew) {
                repartoListNewRepartoToAttach = em.getReference(repartoListNewRepartoToAttach.getClass(), repartoListNewRepartoToAttach.getRepartoPK());
                attachedRepartoListNew.add(repartoListNewRepartoToAttach);
            }
            repartoListNew = attachedRepartoListNew;
            pelicula.setRepartoList(repartoListNew);
            List<Opinion> attachedOpinionListNew = new ArrayList<Opinion>();
            for (Opinion opinionListNewOpinionToAttach : opinionListNew) {
                opinionListNewOpinionToAttach = em.getReference(opinionListNewOpinionToAttach.getClass(), opinionListNewOpinionToAttach.getCodComen());
                attachedOpinionListNew.add(opinionListNewOpinionToAttach);
            }
            opinionListNew = attachedOpinionListNew;
            pelicula.setOpinionList(opinionListNew);
            pelicula = em.merge(pelicula);
            if (codDirectorOld != null && !codDirectorOld.equals(codDirectorNew)) {
                codDirectorOld.getPeliculaList().remove(pelicula);
                codDirectorOld = em.merge(codDirectorOld);
            }
            if (codDirectorNew != null && !codDirectorNew.equals(codDirectorOld)) {
                codDirectorNew.getPeliculaList().add(pelicula);
                codDirectorNew = em.merge(codDirectorNew);
            }
            if (codGeneroOld != null && !codGeneroOld.equals(codGeneroNew)) {
                codGeneroOld.getPeliculaList().remove(pelicula);
                codGeneroOld = em.merge(codGeneroOld);
            }
            if (codGeneroNew != null && !codGeneroNew.equals(codGeneroOld)) {
                codGeneroNew.getPeliculaList().add(pelicula);
                codGeneroNew = em.merge(codGeneroNew);
            }
            for (Reparto repartoListNewReparto : repartoListNew) {
                if (!repartoListOld.contains(repartoListNewReparto)) {
                    Pelicula oldPeliculaOfRepartoListNewReparto = repartoListNewReparto.getPelicula();
                    repartoListNewReparto.setPelicula(pelicula);
                    repartoListNewReparto = em.merge(repartoListNewReparto);
                    if (oldPeliculaOfRepartoListNewReparto != null && !oldPeliculaOfRepartoListNewReparto.equals(pelicula)) {
                        oldPeliculaOfRepartoListNewReparto.getRepartoList().remove(repartoListNewReparto);
                        oldPeliculaOfRepartoListNewReparto = em.merge(oldPeliculaOfRepartoListNewReparto);
                    }
                }
            }
            for (Opinion opinionListNewOpinion : opinionListNew) {
                if (!opinionListOld.contains(opinionListNewOpinion)) {
                    Pelicula oldCodPeliculaOfOpinionListNewOpinion = opinionListNewOpinion.getCodPelicula();
                    opinionListNewOpinion.setCodPelicula(pelicula);
                    opinionListNewOpinion = em.merge(opinionListNewOpinion);
                    if (oldCodPeliculaOfOpinionListNewOpinion != null && !oldCodPeliculaOfOpinionListNewOpinion.equals(pelicula)) {
                        oldCodPeliculaOfOpinionListNewOpinion.getOpinionList().remove(opinionListNewOpinion);
                        oldCodPeliculaOfOpinionListNewOpinion = em.merge(oldCodPeliculaOfOpinionListNewOpinion);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = pelicula.getCodPelicula();
                if (findPelicula(id) == null) {
                    throw new NonexistentEntityException("The pelicula with id " + id + " no longer exists.");
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
            Pelicula pelicula;
            try {
                pelicula = em.getReference(Pelicula.class, id);
                pelicula.getCodPelicula();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The pelicula with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Reparto> repartoListOrphanCheck = pelicula.getRepartoList();
            for (Reparto repartoListOrphanCheckReparto : repartoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Pelicula (" + pelicula + ") cannot be destroyed since the Reparto " + repartoListOrphanCheckReparto + " in its repartoList field has a non-nullable pelicula field.");
            }
            List<Opinion> opinionListOrphanCheck = pelicula.getOpinionList();
            for (Opinion opinionListOrphanCheckOpinion : opinionListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Pelicula (" + pelicula + ") cannot be destroyed since the Opinion " + opinionListOrphanCheckOpinion + " in its opinionList field has a non-nullable codPelicula field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Director codDirector = pelicula.getCodDirector();
            if (codDirector != null) {
                codDirector.getPeliculaList().remove(pelicula);
                codDirector = em.merge(codDirector);
            }
            Genero codGenero = pelicula.getCodGenero();
            if (codGenero != null) {
                codGenero.getPeliculaList().remove(pelicula);
                codGenero = em.merge(codGenero);
            }
            em.remove(pelicula);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Pelicula> findPeliculaEntities() {
        return findPeliculaEntities(true, -1, -1);
    }

    public List<Pelicula> findPeliculaEntities(int maxResults, int firstResult) {
        return findPeliculaEntities(false, maxResults, firstResult);
    }

    private List<Pelicula> findPeliculaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Pelicula.class));
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

    public Pelicula findPelicula(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Pelicula.class, id);
        } finally {
            em.close();
        }
    }

    public int getPeliculaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Pelicula> rt = cq.from(Pelicula.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
