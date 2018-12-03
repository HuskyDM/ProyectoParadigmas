package optimizacion;
import org.nlogo.headless.HeadlessWorkspace;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by irvin on 10/22/18.
 */
public class NetLogoSim implements Callable {
    HeadlessWorkspace workspace;
    HashMap<String, Double> parameters;
    private static final Logger logger = Logger.getLogger(NetLogoSim.class.getName());
    int ticksPerCycle;
    int numCycles;
    String path;

    public NetLogoSim(String path, int ticksPerCycle, int numCycles) {
        parameters = null;
        workspace = HeadlessWorkspace.newInstance();
        this.path = path;
        try {
            workspace.open(path);
            this.ticksPerCycle = ticksPerCycle;
            this.numCycles = numCycles;
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage());
            //Todo create exception to avoid executing further
        }
    }

    public void updateParams(HashMap<String, Double> parameters) {
        this.parameters = parameters;
    }

    public HashMap<String, double[]> runSimulation() throws Exception {

            logger.log(Level.INFO, "Setting up the parameters.");
            logger.log(Level.INFO, "   " + parameters.toString());


            Thread.sleep(10000);
            for (Map.Entry<String, Double> entry : parameters.entrySet()) {

                workspace.command("set " + entry.getKey().toLowerCase() + " " + Double.toString(entry.getValue()));

            }

            logger.log(Level.INFO, "Setting up experiment.");
            workspace.command(" Setup-experiment ");

            double[] aliveResults = new double[numCycles];
            double[] infectedResults = new double[numCycles];
            double[] deadResults = new double[numCycles];

            for (int i = 0; i < numCycles; i++) {

                logger.log(Level.INFO, "        Executing cycle: " + Integer.toString(i + 1));

                workspace.command("repeat " + Integer.toString(ticksPerCycle) + " [ go ]");

                try {
                    double alive = countAlive();
                    double infected = countInfected();
                    double dead = countDead();
                    
                    aliveResults[i] = alive;
                    infectedResults[i] = infected;
                    deadResults[i] = dead;


                logger.log(Level.INFO, "        alive: " + Double.toString(alive));
                logger.log(Level.INFO, "        infected: " + Double.toString(infected));
                logger.log(Level.INFO, "        dead: " + Double.toString(dead));
                }catch(Exception e){
                    e.printStackTrace();
                    throw e;
                }
            }

            workspace.dispose();

            HashMap<String, double[]> results = new HashMap<String, double[]>();
            results.put("alive", aliveResults);
            results.put("infected", infectedResults);
            results.put("dead", deadResults);
            return results;




    }

    private Double countAlive() {
        return (Double) workspace.report("count turtles with [state = \"infected\" and fluorescence / 120 > marker-detection-threashold and chromatin-condensed]");
    }

    private Double countInfected() {
        return (Double) workspace.report("count turtles with [state = \"infected\" and fluorescence / 120 > marker-detection-threashold]");
    }

    private Double countDead() {
        return (Double) workspace.report("count turtles with [state = \"dead\"]");
    }
    

    public Object call() throws Exception {
        if(parameters != null ) {
            return runSimulation();
        }else{
            throw new NullPointerException();
            //Era NullParameterException
        }
    }
}


