import processing.core.PApplet;
import processing.core.PVector;

public class EtoileArrierePlan extends PApplet {

    //Position de l'étoile.
    private PVector position;

    //Taille de l'étoile.
    private float taille;

    //Sintille ou non.
    private boolean sintille;

    //Constructeur
    public EtoileArrierePlan() {

        //Position random dans les limites du cadre.
        this.position = new PVector( random(Test.processing.width), random(Test.processing.height));

        this.taille = random((float) 0.3, (float) 1.5);

        this.sintille = false;
    }

    //----------------------------------------------
    /** ROLE : Dessine toutes les étoiles de l'arriere plan. */
    public void drawEtoile() {
        Test.processing.fill(255);
        Test.processing.noStroke();
        Test.processing.circle(position.x, position.y, taille);
    }


    //----------------------------------------------
    /** ROLE : Augmente ou diminue la taille de l'étoile
     * Donne un effet de scintillement. */
    public void sintillement() {
        if (!this.sintille) {
            this.taille += 0.5; //Augmentation de 0.5 de la taille.
            this.sintille = true;
        } else {
            this.taille -= 0.5; //Diminution de 0.5 de la taille.
            this.sintille = false;
        }
    }
}
