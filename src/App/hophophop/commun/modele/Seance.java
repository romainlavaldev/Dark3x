//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package hophophop.commun.modele;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Seance implements Serializable {
    private Long heureDebut;
    private Long heureFin;
    private Map<Long, CoupleEvt> lesEvenements;
    private int nombreEvenements;
    private int nombreExecutions;
    private int nombreCompilations;
    private Long duree;
    private List<IndicateurNumerique> lesIndicateurs = new ArrayList();

    public Seance(Long heureDebut) {
        this.heureDebut = heureDebut;
        this.lesEvenements = new TreeMap();
    }

    public void setHeureFin(Long heureFin) {
        this.heureFin = heureFin;
    }

    public void setNombreEvenements(int nombreEvenements) {
        this.nombreEvenements = nombreEvenements;
    }

    public void setNombreExecutions(int nombreExecutions) {
        this.nombreExecutions = nombreExecutions;
    }

    public void setNombreCompilations(int nombreCompilations) {
        this.nombreCompilations = nombreCompilations;
    }

    public Map<Long, CoupleEvt> getLesEvenements() {
        return this.lesEvenements;
    }

    public void setHeureDebut(Long heureDebut) {
        this.heureDebut = heureDebut;
    }

    public void setDuree(Long duree) {
        this.duree = duree;
    }

    public Long getDuree() {
        return this.duree;
    }

    public void setIndicateurNumerique(List<IndicateurNumerique> lesIndicateurs) {
        this.lesIndicateurs = lesIndicateurs;
    }

    public Long getHeureDebut() {
        return this.heureDebut;
    }

    public Long getHeureFin() {
        return this.heureFin;
    }

    public int getNombreEvenements() {
        return this.nombreEvenements;
    }

    public int getNombreExecutions() {
        return this.nombreExecutions;
    }

    public int getNombreCompilations() {
        return this.nombreCompilations;
    }

    public List<IndicateurNumerique> getLesIndicateurs() {
        return this.lesIndicateurs;
    }
}
