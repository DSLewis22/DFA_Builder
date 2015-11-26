import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
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

    private String valueBeingRead; // this is uesless.
	private State nextState; // needed for transitioning(Think how Linked List works)
    private State currentState; // this is also useless
	private Map<String, State> transitionMap = new HashMap<>(); // gotta figure out how to populate this Mapping.
    private boolean acceptState;
    private boolean startState;
    private char stateName; // this is needed to find the State corresponding with it to do transition mapping.

    // get properties for each field.
    public String getValueBeingRead(){return this.valueBeingRead;} //useless.
    public boolean  getAcceptState() {return this.acceptState;}
    public State  getNextState() { return this.nextState;} // useless.
    public boolean getStartState() { return this.startState;}
    public Map getTransitionMap() {return this.transitionMap;}
    public char getStateName() {return this.stateName;}


    // set properties for each field.
    public void valueBeingRead(String valueToRead){this.valueBeingRead = valueToRead;} // more uslessness
    public void setAcceptState(boolean acceptState){this.acceptState = acceptState;}
    public void setNextState(){} // not sure how this function will work just yet. Doesn't matter, it's uselss
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

    public void displayInfo() // Might not need this.
    {
            // Definitely won't need this.
    }



        public static void main(String[] args) throws IOException {
            FileInputStream in = new FileInputStream("/Users/jlarkin9/Desktop/test.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(in));

            String strLine = null;

            String[] filearray;
            filearray = new String[12];

            while ((filearray[0] = br.readLine()) != null) {
                System.out.println("DFA:\n" + filearray[0]);


                for (int j = 1; j < filearray.length; j++){
                    filearray[j] = br.readLine();
                    System.out.println(filearray[j]);
                }

                filearray[0] = filearray[0].substring(1, filearray[0].indexOf('}'));
                filearray[1] = filearray[1].substring(1, filearray[1].indexOf('}'));
                filearray[3] = filearray[3].substring(1, filearray[3].indexOf('}'));

                String[] alphabet = filearray[0].split(",");
                String[] states = filearray[1].split(",");
                String start = filearray[2];
                String accept = filearray[3];

                System.out.println("alphabet: " + alphabet[0] + " " + alphabet[1]);
                System.out.print("states: ");
                for (int i = 0; i < states.length;i++) {
                    System.out.print(states[i] + " ");
                }
                System.out.println("\nstart state: " + start);
                System.out.println("accept state: " + accept);

                /*
                String[][] transitionMap;
                int transitionsNum = (states.length * alphabet.length);
                System.out.println(transitionsNum);
                transitionMap = new String[transitionsNum][2];
                for(int i = 0, j = 4; i < transitionsNum && j < filearray.length;i++,j++) {
                    transitionMap[i] = filearray[j].split("->");
                }
                for (int k = 0; k < transitionsNum;k++){
                    for(int l = 0; l < 2;l++){
                        System.out.print(transitionMap[k][l] + " ");
                    }
                    System.out.print("\n");
                }
                */

                State[] stateObjects = new State[states.length];
                for (int i = 0; i < states.length;i++) {
                    stateObjects[i] = new State(states[i].charAt(0));
                    if(stateObjects[i].stateName == start.charAt(0))
                        stateObjects[i].setStartState(true);
                    else if (stateObjects[i].stateName == accept.charAt(0))
                        stateObjects[i].setAcceptState(true);
                    else {
                        stateObjects[i].setAcceptState(false);
                        stateObjects[i].setStartState(false);
                    }
                    //testing
                    System.out.println(stateObjects[i].stateName + " start? " + stateObjects[i].getStartState());
                    System.out.println(stateObjects[i].stateName + " accept? " + stateObjects[i].getAcceptState());


                }
            }
            in.close();

        }
    }









