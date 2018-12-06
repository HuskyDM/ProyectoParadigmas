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
 * Modificado por Javier Fernández 11/18
 */
public class NetLogoSimMod implements Callable {
    HeadlessWorkspace workspace;
    HashMap<String, Double> parameters;
    private static final Logger logger = Logger.getLogger(NetLogoSimMod.class.getName());
    
    int id;
    String path;

    public NetLogoSimMod(String path, int id) {
        parameters = null;
        workspace = HeadlessWorkspace.newInstance();
        this.id=id;
        this.path = path;
        try {
            workspace.open(path);
            
            
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage());
            //Todo create exception to avoid executing further
        }
    }

    public void updateParams(HashMap<String, Double> parameters) {
        this.parameters = parameters;
    }

    public HashMap<String,Double> runSimulation() throws Exception {

            HashMap<String, Double> results = new HashMap<String,Double>();
        
            logger.log(Level.INFO, "Setting up the parameters.");
            logger.log(Level.INFO, "   " + parameters.toString());


            Thread.sleep(10000);
            for (Map.Entry<String, Double> entry : parameters.entrySet()) {

                workspace.command("set " + entry.getKey().toLowerCase() + " " + Double.toString(entry.getValue()));

            }

            logger.log(Level.INFO, "Setting up experiment.");
            workspace.command(" Setup-experiment ");

            double aliveResults = 0.0;
            double infectedResults = 0.0;
            double deadResults = 0.0;

            for (int i = 0; i < 5; i++) {

                logger.log(Level.INFO, "        Executing cycle");
                logger.log(Level.INFO, "        Running ID:"+id);
                workspace.command("repeat " + Integer.toString(24) + " [ go ]");

                try {
                    double alive = countAlive();
                    double infected = countInfected();
                    double dead = countDead();
                    
                    aliveResults = alive;
                    infectedResults = infected;
                    deadResults = dead;
                    
                    int r =i+1;
                    
                    results.put("alive"+r, aliveResults);
                    results.put("infected"+r, infectedResults);
                    results.put("dead"+r, deadResults);
                    

                logger.log(Level.INFO,"ID:"+id+ "        alive: " + Double.toString(alive));
                logger.log(Level.INFO, "ID:"+id+"        infected: " + Double.toString(infected));
                logger.log(Level.INFO, "ID:"+id+"        dead: " + Double.toString(dead));
                
                
                
                }catch(Exception e){
                    e.printStackTrace();
                    throw e;
                }
            }

            workspace.dispose();
                      
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


