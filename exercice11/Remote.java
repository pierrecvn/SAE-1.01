import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;
import net.java.games.input.Component;
import net.java.games.input.Event;
import net.java.games.input.EventQueue;

public class Remote {
    private static final float DEADZONE = 0.2f;

    public static void main(String[] args) {
        // Obtenir l'environnement des contrôleurs
        ControllerEnvironment ce = ControllerEnvironment.getDefaultEnvironment();
        // Obtenir tous les contrôleurs connectés
        Controller[] controllers = ce.getControllers();

        // Rechercher la manette Xbox
        Controller xboxController = null;
        for (Controller controller : controllers) {
            if (controller.getName().toLowerCase().contains("x-box")) {
                xboxController = controller;
                break;
            }
        }

        if (xboxController == null) {
            System.out.println("Manette Xbox non trouvée");
            return;
        }

        // Ouvrir la manette Xbox
        xboxController.poll();

        // Boucle principale
        while (true) {
            xboxController.poll();

            EventQueue eventQueue = xboxController.getEventQueue();
            Event event = new Event();

            while (eventQueue.getNextEvent(event)) {
                Component component = event.getComponent();
                float value = event.getValue();

                // Vérifier les composants de la manette
                if (component.isAnalog()) {
                    // Composant analogique (joystick, gâchette, etc.)
                    if (Math.abs(value) > DEADZONE) {
                        // Faire quelque chose avec la valeur analogique
                        System.out.println(component.getName() + ": " + value);
                    }
                } else {
                    // Composant numérique (bouton, croix directionnelle, etc.)
                    System.out.println(component.getName() + ": " + value);
                }
            }
        }
    }
}