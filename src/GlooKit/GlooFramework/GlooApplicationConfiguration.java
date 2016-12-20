package GlooKit.GlooFramework;

import GlooKit.Utils.JSONObject;
import GlooKit.Utils.JSONable;
import GlooKit.Utils.Vector;
import org.lwjgl.glfw.GLFWVidMode;

import java.io.*;

/**
 * GlooApplicationConfiguration is essentially a handle for accessing config files (.cfg) for the GlooApplication
 *
 * Allows for the configuration of fullscreen mode, window pointSize, and frames per second
 *
 * @author Duncan Walter
 * @author Eli Jergensen
 * @since 1.0
 * */
public class GlooApplicationConfiguration implements Serializable, JSONable<GlooApplicationConfiguration> {

    // toggles fullscreen / windowed mode
    private boolean fullscreen;

    // null for auto detection / default
    // only used for fullscreen mode
    private GLFWVidMode displayMode;

    private Vector size;

    // application spacing between elements by default
    private float spacingPoints;

    // accepts values from 28 - 180
    private int framesPerSecond;

    private String fileName;


    /**
     * Attempts to load a config file. If the file is not found, it makes a new default one
     *
     * @param fileName A String representing the filepath
     * */
    public GlooApplicationConfiguration(String fileName){

        this.fileName = fileName;

        JSONObject json = constructJSONObject();
        json.writeToFile(fileName.substring(0, fileName.lastIndexOf('.')) + ".json");

        try {
            // try to load the config file

            FileInputStream fileIn = new FileInputStream(fileName);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            GlooApplicationConfiguration config  = (GlooApplicationConfiguration)in.readObject();
            in.close();
            fileIn.close();

//            System.out.println(config);

            fullscreen = config.fullscreen;
            displayMode = config.displayMode;
            size = config.size;
            framesPerSecond = config.framesPerSecond;
            this.spacingPoints = config.spacingPoints;

        }catch(IOException e) {
            // if unable to load the config file (it probably doesn't exist), make a new default one

//            System.out.println(fileName + " not found; Creating a default config file");

            fullscreen = false;
            displayMode = null;
            framesPerSecond = 60;
            size = new Vector(960, 520, 0);
            spacingPoints = 5f;
            save();

        }catch(ClassNotFoundException e) {

            e.printStackTrace();

        }

    }

    public GlooApplicationConfiguration(String fileName, boolean REMOVELATER) {

        this.fileName = fileName;

        try {

            // attempt to load in the existing file

            FileInputStream inputFile = new FileInputStream(fileName); // construct a file input stream

        } catch (IOException e) {

        }
    }

    public JSONObject constructJSONObject() {
        JSONObject json = new JSONObject();

        json.add("name", "Fred");
        json.add("age", "13");
        json.add("height", 169);
        json.add("desert", new String[] {"cheesecake", "(gummi-)bears", "I fu[]{}ing h*te y,u"});
        json.add("bottle", new JSONObject().add("message", "\"Duncan said so, JSON format!\""));
//        json.add("friends", new JSONObject[] {
//            new JSONObject().add("name", "Bob"),
//            new JSONObject().add("name", "Bill"),
//            new JSONObject().add("name", "Jebediah")
//        });

        return json;
    }

    public GlooApplicationConfiguration constructFromJSON(JSONObject json) {
        return this;
    }

    /**
     * Attempts to save a configuration to a .cfg file
     *
     * @return false if it failed to save, true if it saved
     * */
    public boolean save(){
        try {
            FileOutputStream fileOut = new FileOutputStream(fileName);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(this);
            out.close();
            fileOut.close();
            return true;
        }catch(IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean isFullscreen(){
        return fullscreen;

    }
    public void setFullscreen(boolean fullscreen){
        this.fullscreen = fullscreen;

    }
    public GLFWVidMode getDisplayMode(){
        return displayMode;

    }
    public void setDisplayMode(GLFWVidMode displayMode){
        this.displayMode = displayMode;

    }
    public int getFramesPerSecond(){
        return framesPerSecond;

    }
    public void setFramesPerSecond(int framesPerSecond){
        this.framesPerSecond = framesPerSecond;

    }
    public Vector getSize() {
        return size;

    }
    public void setSize(Vector size) {
        this.size = size;

    }
    public float getSpacingPoints() {
        return spacingPoints;

    }
    public void setSpacingPoints(float spacingPoints) {
        this.spacingPoints = spacingPoints;

    }

}
