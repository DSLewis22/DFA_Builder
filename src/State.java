import java.util.HashMap;
import java.util.Map;

// All this class does is handle everything about each individual State
// and all the things a STATE should be worried about.
// Each State should know the following:
/*  1. Are you the startState?
 *  2. Are you the AcceptState?
 *  3. What values can you read?
 *  4. What State is next based on the value being read.
 */
public class State {

    private String valueBeingRead;
	private State nextState;
    private State currentState; // not quite sure how this will work or if i'll need this.
	private Map<String, State> transitionMap = new HashMap<>(); // gotta figure out how to populate this Mapping.
    private boolean acceptState;
    private boolean startState;

    // get properties for each field.
    public String getValueBeingRead(){return this.valueBeingRead;}
    public boolean  getAcceptState() {return this.acceptState;}
    public State  getNextState() { return this.nextState;}
    public boolean getStartState() { return this.startState;}
    public Map getTransitionMap() {return this.transitionMap;}

    // set properties for each field.
    public void ValueBeingRead(String valueToRead){this.valueBeingRead = valueToRead;}
    public void setAcceptState(boolean acceptState){this.acceptState = acceptState;}
    public void setNextState(){} // not sure how this function will work just yet.
    public void setStartState(boolean startState){this.startState = startState;}

    // constructor
    public State(boolean startState, boolean acceptState)
    {
        setStartState(startState);
        setAcceptState(acceptState);
    }

    public State Transition(State currentState, String currentInput) // method takes in the CurrentState it is currently on and the input being read.
    {                                                                // I think the currentState can be taken out cause the State calling this
                                                                    // method *SHOULD* be the currentState(so you can essentially use 'this' keyword).
        Map<String,State> transitionState = currentState.getTransitionMap();
        nextState = transitionState.get(currentInput); // grabs the next state based on the input.
        return nextState;
    }

    public void addToTransitionMap(String value, State nextState) // this function populates the the transitioning to the next State.
    {
        transitionMap.put(value,nextState);

    }

    public void displayinfo() // Might not need this.
    {

    }


}

