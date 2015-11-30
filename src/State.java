import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.*;


// All this class does is handle everything about each individual State
// and all the things a STATE should be worried about.
// Each State should know the following:
/*  1. Are you the startState?
 *  2. Are you the AcceptState?
 *  3. What values can you read?
 *  4. What State is next based on the value being read.
 */
public class State {


	private State nextState; // needed for transitioning(Think how Linked List works)
	private Map<String, State> transitionMap = new HashMap<>(); // gotta figure out how to populate this Mapping.
    private boolean acceptState;
    private boolean startState;
    public char stateName; // this is needed to find the State corresponding with it to do transition mapping.

    // get properties for each field.

    public boolean  getAcceptState() {return this.acceptState;}
    public State  getNextState() { return this.nextState;} // useless.
    public boolean getStartState() { return this.startState;}
    public Map getTransitionMap() {return this.transitionMap;}
    public char getStateName() {return this.stateName;}


    // set properties for each field.
    public void setAcceptState(boolean acceptState){this.acceptState = acceptState;}
    public void setStartState(boolean startState){this.startState = startState;}
    public void setStateName(char stateName){this.stateName = stateName;}


    public State(char name) { // since we get the states before knowing if it's the start or accept.
                              // we'll initialize the Start and accept to false and change them later.
                              // We'll change them based on their name, hence why you must pass in a name.
        setStateName(name);
        setAcceptState(false);
        setStartState(false);
    }
    // constructor
    public State(boolean startState, boolean acceptState)
    {
        setStartState(startState);
        setAcceptState(acceptState);
    }

    public State transition(State currentState, String currentInput) // method takes in the CurrentState it is currently on and the input being read.
    {                                                                // I think the currentState can be taken out cause the State calling this
                                                                    // method *SHOULD* be the currentState(so you can essentially use 'this' keyword).
        Map<String,State> transitionState = currentState.getTransitionMap();
        if(!transitionState.containsKey(currentInput)) // if the value being read IS NOT in the mapping.
        {                                              // in other words, if the value is not in the alphabet.
            return null;
        }
        nextState = transitionState.get(currentInput); // grabs the next state based on the input.
        return nextState;
    }

    public void addToTransitionMap(String value, State nextState) // this function populates the the transitioning to the next State.
    {
        transitionMap.put(value,nextState);

    }

}









