package fr.pgah.java.unbrco.lecteurs;

import fr.pgah.java.unbrco.model.Dessin;
import fr.pgah.java.unbrco.model.Forme;
import fr.pgah.java.unbrco.ui.EditeurDeFormes;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LecteurDeDessin implements ActionListener {
  private Dessin dessin;
  private Timer timer;
  private int colonneEnCours;

  private List<Forme> dansLaColonnePrecedente;
  private List<Forme> dansLaColonneCourante;

  public LecteurDeDessin(Dessin dessin, Timer timer) {

    // À COMPLÉTER
    // Initialiser toutes les variables d'instance
    this.dessin = dessin;
    this.timer = timer;
    this.colonneEnCours = 0;
    this.dansLaColonnePrecedente = new ArrayList<Forme>();
    this.dansLaColonneCourante = new ArrayList<Forme>();
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    selectionnerEtJouerLesFormes();
    dessinerLigneProgression();
    incrementerColonne();
    arreterSiDessinFini();
  }

  private void incrementerColonne() {
    colonneEnCours += 1;
    dansLaColonnePrecedente = dansLaColonneCourante;
  }

  private void dessinerLigneProgression() {
    dessin.setColonneCourante(colonneEnCours);
    dessin.repaint();
  }

  private void arreterSiDessinFini() {
    if (colonneEnCours > EditeurDeFormes.LONGUEUR) {
      timer.stop();
    }
  }

  private void selectionnerEtJouerLesFormes() {
    dansLaColonneCourante = dessin.formesSurLaColonne(colonneEnCours);

    // À COMPLÉTER

    // Déselectionner et stopper toutes les formes qui étaient dans
    // la colonne précédente et qui ne sont plus dans la colonne courante
    dansLaColonneCourante = dessin.formesSurLaColonne(colonneEnCours);

    for (Forme forme : dansLaColonnePrecedente) {
      if (!dansLaColonneCourante.contains(forme)) {
        forme.deselectionnerEtStopper();
      }
    }
    for (Forme forme : dansLaColonneCourante) {
      if (!dansLaColonnePrecedente.contains(forme)) {
        forme.selectionnerEtJouer();
      }
    }

  }
}
