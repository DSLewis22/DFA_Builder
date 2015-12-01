import java.util.ArrayList;
import java.util.List;

/**
 *  This class is will handle all the States and the ordering
 *  of the states. This class will only deal with State Objects.
 *  as well as telling if a string is accepted or not.
 *  UTILIZE THE METHODS WITHIN THE STATE CLASS!
 *
 */
public class DFA {

    /* Required Fields....
     * List of States.
     * Start State.
     * Current State.
     */

    private List<State> states;
    private State startState;
    private State currentState;

    //
    public DFA()
    {
        // initialize your List here...
        // Can't have a DFA object created without States.
        this.states = new ArrayList<State>();
    }

    public void addState(State state)
    {
        // Accept a state object and add it to your List of STates.
        states.add(state);
    }

    public void orderStates()
    {
        // order your states...
        //  just find your Start State and the rest will follow(kind of how linked list work)
        for(State s : states)
        {
            if(s.getStartState())
            {
                startState = s;
                break;
            }
        }
    }

    public void testString(String input)
    {
        // accept a string and Test it to see if it's accepted or not.
        // return an output to the console letting the user know if it was accepted or not.
        // Needs to also be able to handle if you receive a string that is not in the alphabet(Error handling).
        // each State has a Transition Method... Each state also has a boolean field for accepState.
        String[] inputArray = input.split("");
        currentState = startState;
        for(int i = 0; i < inputArray.length; i++)
        {

            if(inputArray[i].isEmpty())continue;// stupid split function adding an empty string for no damn reason.
            currentState = currentState.transition(currentState, inputArray[i]);
            if(currentState == null)
            {
                System.out.println("invalid character has been detected, String will be rejected");
                return;
            }

        }

        if(currentState.getAcceptState())
        {
            System.out.println("The String " + input + " is accepted");
        }
        else
        {
            System.out.println("The String " + input + " is not accepted");
        }
    }

    public State findState(char name)
    {

        // Accept the State's name(char)
        // and find it's corresponding State and return it.
        for(State s : states)
        {
            if(name == s.getStateName())
            {
                return s;
            }
        }
        return null;
    }

}
