package smpl.semantics;

import smpl.sys.SmplException;
import java.util.*;

/**
 * An instance of class <code>Environment</code> maintains a
 * collection of bindings from valid identifiers to integers.
 * It supports storing and retrieving bindings, just as would
 * be expected in any frame.
 *
 * 
 */

public class Environment {

    HashMap<String, G> dictionary;       //current environment  
    Environment<G> parent = null;        // parent envornment

    

     /**
     * Create a new (empty) top level Environment.
     *
     */
    public Environment(){
        dictionary = new HashMap<String, G>();
        parent = null;
    }


    /* This does not extend the new environment. It adds to the current environment*/
    public Environment(String[] ids, G[] values){
        dictionary = new HashMap<>();
        for(int i=0; i<ids.length; i++){
            put(ids[i], values[i]);
        }
    }


    /**
     * Create a new <code>Environment</code> instance that is
     * initialised with the given collection of bindings
     *
     * @param ids the collection of identifiers to be bound.
     * @param values the corresponding collection of values
     * for the identifiers.  Note that the two arrays must
     * have the same length.
     * @param parent The parent of this environment 
     */
    public Environment(String[] ids, G[] values, Environment<G> parent) {
    dictionary = new HashMap<>();
    parent = parent;
    for (int i = 0; i < ids.length; i++) {
        dictionary.put(ids[i], values[i]);
    }

    }



    /*The environment is not extended*/
    public Environment(ArrayList<String> ids, ArrayList<G> values){
        dictionary = new HashMap<>();
        for(int i=0; i<ids.size(); i++){
            put(ids.get(i), values.get(i));
        }
    }

 /**
     * Create a new <code>Environment</code> instance that is
     * initialised with the given collection of bindings
     * (presented as separate lists of names and values).
     *
     * @param ids The list of identifiers to be bound
     * @param values The corresponding list of values to be bound
     * @param parent The environment being extended.
     */
    public Environment(ArrayList<String> ids, ArrayList<G> values, Environment<G> parent) {
        dictionary = new HashMap<>();
        parent = parent;
        for (int i = 0; i < ids.size(); i++) {
            put(ids.get(i), values.get(i));
        }
    }




    /**
     * Create an instance of a global environment suitable for
     * evaluating a program.
     *
     * @return the <code>Environment</code> created.
     */
    public static <G extends SmplValue<G>> Environment<T> makeGlobalEnv() {
    Environment<G> result =  new Environment<>();
    return result;
    }




    /**
     * Store a binding for the given identifier to the given
     * int within this environment.
     *
     * @param id the name to be bound
     * @param value the value to which the name is bound.
     */
    public void put(String id, G value) {
    dictionary.put(id, value);
    }



    /**
     * Return the int associated with the given identifier.
     *
     * @param id the identifier.
     * @return the int associated with the identifier in
     * this environment.
     * @exception SmplException if <code>id</code> is unbound
     */
    public G get(String id) throws SmplException {
    G result = dictionary.get(id);
    if (result == null)
        if (parent == null)
            throw new SmplException("Unbound variable " + id);
        else
            return parent.get(id);
    else
        return result;
    }






    /**
     * Create a string representation of this environment.
     *
     * @return a string of all the names bound in this environment.
     */
    @Override
    public String toString() {
	StringBuffer result = new StringBuffer();
    Iterator<String> i = dictionary.keySet().Iterator();
    while(i.hasNext()) {
        result = result.append(i.next());
    }
	return result.toString();
    }
}