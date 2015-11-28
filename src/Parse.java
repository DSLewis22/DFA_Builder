import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/*
 * this class parses the Text File.
 * Creates the States and passes them to the DFA
 * This classes also populates each STate Transition Map(a Field in the State Class)
 * it will also accept a string to pass to the DFA class to see if it's accepted or not.
 * This class just really facilitates everything that is going on under the hood.
 * UTILIZE THE METHODS THAT ARE IN THE DFA CLASS AND THE STATE CLASS.
 */
public class Parse {

    static DFA dfa = new DFA(); // this is the dfa that'll hold all the states and tell each state what to do basically.

    public static void main(String[] args) { // first accept the rules to the DFA within the command line argument.

        if (args.length == 0) {
            // tell the user to specify a path to the text file.
        } else if (Files.notExists(Paths.get(args[0]))) {
            // if the File does not exist.... tell the user it doesn't exist.
        } else {
            try {
                parse(args[0]);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        }

        // After everything is parse.. read input from the user for a string to be tested.
        // then call the testString Method located within the dfa class and pass it in and it'll tell you
        // if it's accepted or not.
    }

    public static void parse(String path) throws IOException {
        /*List<String> file = null; */


        /*try {
            file = Files.readAllLines(Paths.get(path), StandardCharsets.UTF_8); // not needed since you used File and buffReader.

        } catch (Exception e) {
            // spit out the exception message.
        }
         */

        List<String> lines = new ArrayList<>();
        FileReader in = new FileReader(path);
        BufferedReader br = new BufferedReader(in);

        String strLine;


        while ((strLine = br.readLine()) != null) { // this is okay.
            lines.add(strLine);
        }

        br.close();

        String[] filearray = lines.toArray(new String[lines.size()]); // this is also okay.

        //removing brackets.
        filearray[0] = filearray[0].substring(1, filearray[0].indexOf('}'));
        filearray[1] = filearray[1].substring(1, filearray[1].indexOf('}'));
        filearray[3] = filearray[3].substring(1, filearray[3].indexOf('}'));

        //split text file lines; create states
        /*String[] alphabet = filearray[0].split(",") */ // alphabet isn't needed so i'm commenting it out.
        String[] states = filearray[1].split(","); // go ahead and create the states now while you're already here. and store it in dfa.
        for (String name : states) {
            dfa.addState(new State(name.charAt(0)));
        }

        String start = filearray[2]; // this is fine.. but go ahead and set the value to 'true' for the Start State. DFA class has a findState method.
        // just pass in the string name to it... for the example you'll pass in 'a' or the variable 'start'(thats what you named it)

        State temp = dfa.findState(start.charAt(0)); // once you find it.. Set the temp.setStartState(true) This is how pointers work.
        temp.setStartState(true);

        String[] accept = filearray[3].split(","); // remember, there can be more than one accept state. so you'll need to split here like you did the states and alphabet.
        // since we have the accept states now, let's go ahead and set those values now and not later.

        for (String nameOfAcceptStates : accept) // loop through accept array and find the states.
        {
            State anotherTemp = dfa.findState(nameOfAcceptStates.charAt(0)); // pointers(Temp Variable) going to work.
            anotherTemp.setAcceptState(true);
        }
        /*
        //just some printing
        System.out.println("alphabet: " + alphabet[0] + " " + alphabet[1]); // commenting this out.
        System.out.print("states: ");
        for (int i = 0; i < states.length; i++) {
            System.out.print(states[i] + " ");
        }
        System.out.println("\nstart state: " + start);
        System.out.println("accept state: " + accept);
        */


        for (int line = 4; line < filearray.length; line++) // line 4 is *ALWAYS* the beginning for the transitioning.
        {                                                      // we're going line by line adding to the transition map.
            // I explain all this down below in detail.
            String[] transitionLine = filearray[line].split("->");
            String[] OriginStateAndTransitionValue = transitionLine[0]
                    .replace('(', ' ')
                    .replace('(', ' ')
                    .trim()
                    .split(",");
            String originState = OriginStateAndTransitionValue[0];
            String transitionValue = OriginStateAndTransitionValue[1];
            String transitionState = transitionLine[1];


            State tempOriginState = dfa.findState(originState.charAt(0)); // pointer objects are legit...
            State tempTransitionState = dfa.findState(transitionState.charAt(0));
            tempOriginState.addToTransitionMap(transitionValue, tempTransitionState);


            /*  split on '->' which returns a string array that'll look like  String[] TransitionLine = ["(a,0)", "b"]
                first letter of the 0th index 'a' is always the originState for transitioning.
                first digit of the 0th index '0' is always the transition value.
                All I have to do is get rid of the parenthesis around them.
                and the 1st index in the TransitionLine will always be the TransitionState which in this case is 'b'

               in the 0th index of the TransitionLine array is "(a,0)"
            1. Replace that left parenthesis with a blank space which will look like " a,0)"
            2. Replace that right parenthesis with a blank space which will look like " a,0 "
            3. Split on the comma, which the split function returns a String array that'll look like String [] = ["a","0"]
            4. set the origin state to 0th index of that String array which is 'a'
            5. set the transitionValue to the 1st index of that String array which is '0'
            6. Transition State comes from the very first intial split on the TransitionLine array, but the first index which is 'b'
            7. Now we have string versions of the origin and Transition State. Let's create temporary States(Pointers) to find
               the actual state object and populate it's transitionMap. Rinse and Repeat for each line... */
        }
    }
}






























/*
        //transitions to 3-d array
        String[][] transitionArray;
        int transitionsNum = (states.length * alphabet.length);
        transitionArray = new String[transitionsNum][2];
        for (int i = 0, j = 4; i < transitionsNum && j < filearray.length; i++, j++) {
            transitionArray[i] = filearray[j].split("->");
        }

        //printing transition array
        for (int k = 0; k < transitionsNum; k++) {
            for (int l = 0; l < 2; l++) {
                System.out.print(transitionArray[k][l] + " ");
            }
            System.out.print("\n");
        }

        //creates array of states and sets them accept, start, or neither
        State[] stateObjects = new State[states.length];
        for (int i = 0; i < states.length; i++) {
            stateObjects[i] = new State(states[i].charAt(0));
            if (stateObjects[i].getStateName() == start.charAt(0))
                stateObjects[i].setStartState(true);
            else if (stateObjects[i].getStateName() == accept.charAt(0))
                stateObjects[i].setAcceptState(true);
            else {
                stateObjects[i].setAcceptState(false);
                stateObjects[i].setStartState(false);
            }
            //testing accept or start
            System.out.println(stateObjects[i].getStateName() + " start? " + stateObjects[i].getStartState());
            System.out.println(stateObjects[i].getStateName() + " accept? " + stateObjects[i].getAcceptState());
*/

//adds to transition map
/*
        for (int i = 0, j = 0, k = 0; i < stateObjects.length && j < stateObjects.length && k < transitionsNum; i++, j++, k++) {
            if (transitionArray[k][1].charAt(0) == stateObjects[i].getStateName() && transitionArray[k][0].charAt(1) == stateObjects[j].getStateName())
                stateObjects[j].addToTransitionMap(transitionArray[k][0].substring(3, 4), stateObjects[i]);
        }*/




