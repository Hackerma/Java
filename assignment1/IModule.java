package assignment1;

/**
 *
 * @author 16108849
 */
 
public interface IModule {

    /**
     * Retrieves the Module's ID
     * @pre true
     * @return the Module's ID
     */
    public String getModuleID();
    
    /**
     * Retrieves the name of the Module
     * @pre true
     * @return the name of the Module
     */
    public String getModuleName();
}
