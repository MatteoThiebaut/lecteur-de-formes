package fr.pgah.java.unbrco.model;

import fr.pgah.java.son.MidiSynth;
import java.awt.*;

public class Forme {

  private static final Color COULEUR_LIGNE_JOUEE = new Color(230, 158, 60);

  private int x;
  private int y;
  private int longueur;
  private int hauteur;
  private boolean estSelectionnee;
  private MidiSynth midiSynth;
  private int instrument;
  private int colonneJouee;

  public Forme(Point hautGauche, MidiSynth midiSynth) {
    this((int) hautGauche.getX(), (int) hautGauche.getY(), 0, 0);
    estSelectionnee = false;
    this.midiSynth = midiSynth;
    instrument = 0;
    colonneJouee = 0;
  }

  public boolean contientX(int x) {

    // À COMPLÉTER
    // renvoyer vrai si le x donné est dans l'espace horizontal de la forme
    // renvoyer faux sinon

    return false;
  }

  public boolean contientY(int y) {

    // À COMPLÉTER
    // renvoyer vrai si le y donné est dans l'espace vertical de la forme
    // renvoyer faux sinon

    return false;
  }

  public boolean contient(Point pt) {

    // À COMPLÉTER
    // renvoyer vrai si le point donné est dans l'espace occupé par la forme
    // renvoyer faux sinon

    return false;
  }

  public void setLimites(Point basDroite) {
    longueur = basDroite.x - x;
    hauteur = basDroite.y - y;
  }

  public void dessiner(Graphics g) {
    Color saveCouleur = g.getColor();
    if (estSelectionnee) {
      g.setColor(COULEUR_LIGNE_JOUEE);
    } else {
      g.setColor(Color.white);
    }
    g.fillRect(x, y, longueur, hauteur);
    g.setColor(saveCouleur);
    g.drawRect(x, y, longueur, hauteur);

    if (colonneJouee > 0 && colonneJouee < longueur) {
      g.setColor(Color.red);
      g.drawLine(x + colonneJouee, y, x + colonneJouee, y + hauteur);
      g.setColor(saveCouleur);
    }
  }

  public void deplacer(int dx, int dy) {
    boolean changementNote;
    changementNote = (convertirCoordVersNote(y) != convertirCoordVersNote(y + dy));
    if (changementNote) {
      stopper();
    }
    x += dx;
    y += dy;
    if (changementNote) {
      jouer();
    }
  }

  public void selectionnerEtJouer() {
    if (!estSelectionnee) {
      estSelectionnee = true;
      jouer();
    }
  }

  public void deselectionnerEtStopper() {
    if (estSelectionnee) {
      estSelectionnee = false;
      stopper();
    }
  }

  private void jouer() {
    int volume = convertirZoneVersVelocite(longueur * hauteur);
    midiSynth.play(instrument, convertirCoordVersNote(y), volume);
  }

  private void stopper() {
    midiSynth.stop(instrument, convertirCoordVersNote(y));
  }

  private int convertirZoneVersVelocite(int zone) {
    return Math.max(60, Math.min(127, zone / 30));
  }

  private int convertirCoordVersNote(int y) {
    return 70 - y / 12;
  }
}
