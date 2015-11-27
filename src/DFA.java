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

    //
    public DFA()
    {
      // initialize your List here...
      // Can't have a DFA object created without States.
    }

    public void addState()
    {
        // Accept a state object and add it to your List of STates.
    }

    public void orderStates()
    {
        // order your states...
        // hint: just find your Start State and the rest will follow.
        // each State has a boolean field for startState....
    }

    public void testString()
    {
        // accept a string and Test it to see if it's accepted or not.
        // return an output to the console letting the user know if it was accepted or not.
        // Needs to also be able to handle if you receive a string that is not in the alphabet(Error handling).
        // each State has a Transition Method... Each state also has a boolean field for accepState.
    }

    public void findState()
    {
        // Accept the State's name(char)
        // and find it's corresponding State and return it.
    }

}
