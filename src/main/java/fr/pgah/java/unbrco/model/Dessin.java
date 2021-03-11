package fr.pgah.java.unbrco.model;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

@SuppressWarnings("serial")
public class Dessin extends JPanel {

  private static final int ESPACEMENT_LIGNES = 30;

  private List<Forme> formes;
  private int colonneCourante;

  public Dessin() {
    super();
    formes = new ArrayList<Forme>();
    setBackground(Color.white);
  }

  public boolean contientLaForme(Forme f) {

    // À COMPLÉTER
    // Renvoyer vrai si le dessin contient la forme donnée

    return false;
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    dessinerLignesHorizontales(g);
    for (Forme forme : formes) {
      forme.dessiner(g);
    }
  }

  private void dessinerLignesHorizontales(Graphics g) {
    Color originale = g.getColor();
    g.setColor(new Color(227, 227, 227));
    for (int y = ESPACEMENT_LIGNES; y < getHeight(); y += ESPACEMENT_LIGNES) {
      g.drawLine(0, y, getWidth(), y);
    }
    if (colonneCourante > 0 && colonneCourante < getWidth()) {
      g.setColor(Color.RED);
      g.drawLine(colonneCourante, 0, colonneCourante, getHeight());
    }
    g.setColor(originale);
  }

  public void ajouterForme(Forme f) {

    // À COMPLÈTER
    // Ajouter la forme donnée au dessin

  }

  public void supprimerForme(Forme f) {

    // À COMPLÈTER
    // Supprimer la forme donnée du dessin

  }

  public Forme getPremiereFormeEn(Point point) {

    // À COMPLÉTER
    // Renvoyer la forme qui se trouve au point donné
    // Si plusieurs : renvoyer la première trouvée
    // Si aucune : renvoyer null

    return null;
  }

  public List<Forme> formesSurLaColonne(int col) {

    // À COMPLÉTER
    // Renvoyer la liste des formes qui se trouvent sur la colonne donnée
    // (liste vide si aucune)

    return null;
  }
}
