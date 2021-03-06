package kernel;

import tools.Log;
import java.util.Scanner;
import kernel.Kernel;
import org.opencv.core.Core;

public class Pan4 {

	static{System.loadLibrary(Core.NATIVE_LIBRARY_NAME);}
	public static void main(String[] args) {
		Log log = new Log();
		log.initLogger();
		Log.logger.info("Lancement de Accord...");

		// Creation of the decision kernel
		Log.logger.info("Création du coeur décisionnel");
		Kernel kernel = new Kernel("sortie.mp4");
		
		// Modules init
		Log.logger.info("Initialisation des modules");
		kernel.initModules();
				
		kernel.addMusician(0, 1, 5, 100, 100);
		kernel.addMusician(1, 2, 6, 300, 100);
		
		// Start threads
		Log.logger.info("Démarrage des modules");
		kernel.startThreads();
		
		Scanner finput = new Scanner(System.in);
		
		Log.logger.info("Appuyez sur enter pour lancer le traitement");
		finput.nextLine();
		
		kernel.startTreatment();
		
		Log.logger.info("Appuyez sur enter pour arreter Accord");
		finput.nextLine();
		try {
			kernel.stopAll();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		finput.close();
	}
}
