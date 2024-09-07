import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public class AnimationManager {

    private Map<PlayerState, BufferedImage[]> animations;
    private PlayerState currentState;
    private int frameIndex;
    private int animationSpeed; // Temps entre chaque frame (en millisecondes)
    private long lastFrameTime;

    public AnimationManager() {
        this.animations = new HashMap<>();
        this.currentState = PlayerState.IDLE; // État initial du joueur
        this.frameIndex = 0;
        this.animationSpeed = 200; // Par exemple, 200ms entre chaque frame
        this.lastFrameTime = System.currentTimeMillis();
    }

    // Associe une animation (un tableau de frames) à un état spécifique du joueur
    public void addAnimation(PlayerState state, BufferedImage[] frames) {
        animations.put(state, frames);
    }

    // Met à jour l'état de l'animation, en avançant les frames
    public void update() {
        long currentTime = System.currentTimeMillis();

        // Vérifie si assez de temps s'est écoulé pour passer à la frame suivante
        if (currentTime - lastFrameTime >= animationSpeed) {
            frameIndex = (frameIndex + 1) % animations.get(currentState).length;
            lastFrameTime = currentTime;
        }
    }

    // Permet de changer l'état courant du joueur (par exemple, passer de MOVING à ATTACK)
    public void changeState(PlayerState newState) {
        if (newState != currentState) {
            currentState = newState;
            frameIndex = 0; // Réinitialise l'index de la frame pour la nouvelle animation
        }
    }

    // Retourne l'image actuelle à afficher pour l'animation
    public BufferedImage getCurrentFrame() {
        return animations.get(currentState)[frameIndex];
    }
}
