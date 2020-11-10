import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PVector;

public class BoutonSuivant extends PApplet implements Boutons {
    //Centre du bouton.
    private float centreX, centreY;
    private PVector centre;

    //Taille du grand rectangle.
    private float gLongeur, gHauteur;
    //Taille du petit rectangle.
    private float pLongeur, pHauteur;

    //Coins du grand rectangle.
    private PVector gCoinHG, gCoinHD, gCoinBG, gCoinBD;
    //Coins du petit rectangle.
    private PVector pCoinHG, pCoinHD, pCoinBG, pCoinBD;

    //vitesse de l'effet de du bouton.
    private float velociteEffetSouriX, velociteEffetSouriY;
    private float cptVelociteEffetSouri;

    //Lancement effet.
    private boolean lancement;

    private String texteBouton;

    public BoutonSuivant(float centreX, float centreY, float gLongeur, float gHauteur, String texteBouton) {
        this.centreX = centreX;
        this.centreY = centreY;

        this.centre = new PVector(centreX, centreY);

        this.gLongeur = gLongeur;
        this.gHauteur = gHauteur;

        this.pLongeur = (this.gLongeur * 75) / 100;
        this.pHauteur = (this.gHauteur * 75) / 100;

        //Calcul des positions des coins du grand rectangle.
        // ( HG = Haut Gauche, HD = Haut Droit, BG = Bas Gauche et BD = Bas Droit ).
        this.gCoinHG = new PVector(this.centreX - (this.gLongeur / 2), this.centreY - (this.gHauteur / 2));
        this.gCoinHD = new PVector(this.centreX + (this.gLongeur / 2), this.centreY - (this.gHauteur / 2));
        this.gCoinBG = new PVector(this.centreX - (this.gLongeur / 2), this.centreY + (this.gHauteur / 2));
        this.gCoinBD = new PVector(this.centreX + (this.gLongeur / 2), this.centreY + (this.gHauteur / 2));

        //Calcul des positions des coins du petit rectangle.
        // ( HG = Haut Gauche, HD = Haut Droit, BG = Bas Gauche et BD = Bas Droit ).
        this.pCoinHG = new PVector(this.centreX - (this.pLongeur / 2), this.centreY - (this.pHauteur / 2));
        this.pCoinHD = new PVector(this.centreX + (this.pLongeur / 2), this.centreY - (this.pHauteur / 2));
        this.pCoinBG = new PVector(this.centreX - (this.pLongeur / 2), this.centreY + (this.pHauteur / 2));
        this.pCoinBD = new PVector(this.centreX + (this.pLongeur / 2), this.centreY + (this.pHauteur / 2));

        this.velociteEffetSouriX = (float) (1 * this.pLongeur) / 100;
        this.velociteEffetSouriY = (float) (1 * this.pHauteur) / 100;
        this.cptVelociteEffetSouri = 0;
        this.lancement = false;

        this.texteBouton = texteBouton;
    }

    //*****************************************************************************************
    /** ROLE : Dessine le rectangle qui compose le bouton ainsi que les trapèzes aux coordonnées correspondantes.
     * PRECONDITION : PVector ont bien été initalisé.
     * Ce sont ces 4 trapèzes qui donnent un effet de "relief". */
    @Override
    public void drawBouton() {
        //Rectangle centre
        Test.processing.noStroke();
        Test.processing.fill(255,255,0);
        Test.processing.rectMode(CENTER);
        Test.processing.rect(this.centre.x, this.centre.y, gLongeur, gHauteur);
        Test.processing.strokeWeight(1);
        Test.processing.fill(0);

        // Trapèze haut
        Test.processing.beginShape();
        Test.processing.fill(255,255,51);
        Test.processing.vertex(this.gCoinHG.x, this.gCoinHG.y);
        Test.processing.vertex(this.pCoinHG.x, this.pCoinHG.y);
        Test.processing.vertex(this.pCoinHD.x, this.pCoinHD.y);
        Test.processing.vertex(this.gCoinHD.x, this.gCoinHD.y);
        Test.processing.vertex(this.gCoinHG.x, this.gCoinHG.y);
        Test.processing.endShape();

        //Trapèze droite
        Test.processing.beginShape();
        Test.processing.fill(255,255,102);
        Test.processing.vertex(this.gCoinHD.x, this.gCoinHD.y);
        Test.processing.vertex(this.pCoinHD.x, this.pCoinHD.y);
        Test.processing.vertex(this.pCoinBD.x, this.pCoinBD.y);
        Test.processing.vertex(this.gCoinBD.x, this.gCoinBD.y);
        Test.processing.vertex(this.gCoinHD.x, this.gCoinHD.y);
        Test.processing.endShape();

        //Trapèze bas
        Test.processing.beginShape();
        Test.processing.fill(153,153,0);
        Test.processing.vertex(this.pCoinBG.x, this.pCoinBG.y);
        Test.processing.vertex(this.gCoinBG.x, this.gCoinBG.y);
        Test.processing.vertex(this.gCoinBD.x, this.gCoinBD.y);
        Test.processing.vertex(this.pCoinBD.x, this.pCoinBD.y);
        Test.processing.vertex(this.pCoinBG.x, this.pCoinBG.y);
        Test.processing.endShape();

        //Trapèze gauche
        Test.processing.beginShape();
        Test.processing.fill(204,204,0);
        Test.processing.vertex(this.gCoinHG.x, this.gCoinHG.y);
        Test.processing.vertex(this.gCoinBG.x, this.gCoinBG.y);
        Test.processing.vertex(this.pCoinBG.x, this.pCoinBG.y);
        Test.processing.vertex(this.pCoinHG.x, this.pCoinHG.y);
        Test.processing.vertex(this.gCoinHG.x, this.gCoinHG.y);
        Test.processing.endShape();

    }

    //********************************************** EFFET BOUTON *******************************************
    /** ROLE : Effet du bouton lorsque la souris passe dessus.
     * Affiche les éléments décrit dans les sous-classe.  */
    @Override
    public void effetSouri() {
        if (verificationBouton()) {
            Test.processing.fill(153,153,0);
            PFont policeBouton;
            policeBouton = Test.processing.createFont("nasalization-rg.ttf", 20);
            Test.processing.textFont(policeBouton);
            Test.processing.textAlign(CENTER, CENTER);

            //Cela inscit le texte rentré en parametre sur le bouton.
            Test.processing.text(this.texteBouton, this.centre.x, centre.y - 5);
        }
    }

    //-------------------------------------------
    /** ROLE : Dessine les effets de la souris quand on clique dessus.
     *
     *  @return boolean : "true" si l'affichage de l'effet est terminé.
     */
    @Override
    public boolean effetClicSouri() {

        //augmentation du compteur de l'effet de clique.
        this.cptVelociteEffetSouri += this.velociteEffetSouriX;

        //Elargissement
        //Continue tant que le compteur n'as pas atteind 10 fois la largeur.
        if (this.cptVelociteEffetSouri <= 10*this.velociteEffetSouriX && !this.lancement) {
            //augmentation de la largeur et hauteur du rectangle central.
            this.pCoinHG.add(-this.velociteEffetSouriX, -this.velociteEffetSouriY);
            this.pCoinHD.add(this.velociteEffetSouriX, -this.velociteEffetSouriY);
            this.pCoinBG.add(-this.velociteEffetSouriX, this.velociteEffetSouriY);
            this.pCoinBD.add(this.velociteEffetSouriX, this.velociteEffetSouriY);
            //Rectrecissement
            //Continue tant que le compteur n'as pas atteinds 20 fois la largeur.
        } else if (this.cptVelociteEffetSouri <= 20*this.velociteEffetSouriX && !this.lancement) {
            //diminution de la largeur et hauteur du rectangle central.
            this.pCoinHG.add(this.velociteEffetSouriX, this.velociteEffetSouriY);
            this.pCoinHD.add(-this.velociteEffetSouriX, this.velociteEffetSouriY);
            this.pCoinBG.add(this.velociteEffetSouriX, -this.velociteEffetSouriY);
            this.pCoinBD.add(-this.velociteEffetSouriX, -this.velociteEffetSouriY);
            //Deque c'est supérieur à 20 : lancement du bouton sinon l'animation n'est pas finis.
        } else if(this.cptVelociteEffetSouri > 20*this.velociteEffetSouriX || this.lancement) {
            this.lancement = true;
            this.cptVelociteEffetSouri = 0;
            return true;
        }
        return false;
    }

    //********************************************** ACTIVATION *******************************************
    /** ROLE : Lance le programme du bouton.
     *
     *  @return boolean : "true" si "effetClicSouri()" est terminé.
     */
    @Override
    public boolean lancementBouton() {
        if (this.effetClicSouri()) { //Tant que l'animation du clique n'est pas terminer, ne lançe pas le programme.
            return true;
        }
        return false;
    }

    //-------------------------------------------
    /** ROLE : Vérifie si le curseur est bien sur le bouton.
     *
     *  @return boolean : Renvois "true" si oui.
     */
    @Override
    public boolean verificationBouton() {
        return  (int) this.pCoinHG.x < Test.processing.mouseX && (int) this.pCoinHG.y < Test.processing.mouseY &&
                (int) this.pCoinHD.x > Test.processing.mouseX && (int) this.pCoinBG.y > Test.processing.mouseY;
    }

    //-------------------------------------------
    /** ROLE : reinitialise le lancement de l'effet clique du bouton.
     *
     * @param lance boolean.
     */
    public void setLance(boolean lance) {
        this.lancement = lance;
    }


}
