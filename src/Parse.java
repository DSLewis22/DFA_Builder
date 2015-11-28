import java.io.BufferedReader;
import java.io.FileNotFoundException;
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

    //static DFA dfa = new DFA(); // this is the dfa that'll hold all the states and tell each state what to do basically.
    public static void main (String[] args) throws IOException
    { // first accept the rules to the DFA within the command line argument.

        if(args.length == 0)
        {
            System.out.println("Specify a path ");
        }
        else if(Files.notExists(Paths.get(args[0])))
        {
            System.out.println("File not found ");
        }
        else { parse(args[0]);} // check the parse method below to fill it in accordindly.


        // After everything is parse.. read input from the user for a string to be tested.
        // then call the testString Method located within the dfa class and pass it in and it'll tell you
        // if it's accepted or not.
    }

    public static void parse(String path) throws IOException
    {
        List<String> lines = new ArrayList<String>();
        FileReader in = new FileReader(path);
        BufferedReader br = new BufferedReader(in);

        String strLine = null;


        while ((strLine = br.readLine()) != null) {
            lines.add(strLine);

        }

        br.close();


        String[] filearray = lines.toArray(new String[lines.size()]);

        //removing brackets
        filearray[0] = filearray[0].substring(1, filearray[0].indexOf('}'));
        filearray[1] = filearray[1].substring(1, filearray[1].indexOf('}'));
        filearray[3] = filearray[3].substring(1, filearray[3].indexOf('}'));

        //split text file lines; create states
        String[] alphabet = filearray[0].split(",");
        String[] states = filearray[1].split(",");
        String start = filearray[2];
        String accept = filearray[3];

        //just some printing
        System.out.println("alphabet: " + alphabet[0] + " " + alphabet[1]);
        System.out.print("states: ");
        for (int i = 0; i < states.length; i++) {
            System.out.print(states[i] + " ");
        }
        System.out.println("\nstart state: " + start);
        System.out.println("accept state: " + accept);



        //creates array of states and sets them accept, start, or neither
        State[] stateObjects = new State[states.length];
        for (int i = 0; i < states.length; i++) {
            stateObjects[i] = new State(states[i].charAt(0));
            if (stateObjects[i].stateName == start.charAt(0))
                stateObjects[i].setStartState(true);
            else if (stateObjects[i].stateName == accept.charAt(0))
                stateObjects[i].setAcceptState(true);
            else {
                stateObjects[i].setAcceptState(false);
                stateObjects[i].setStartState(false);
            }
            //testing accept or start
            System.out.println(stateObjects[i].stateName + " start? " + stateObjects[i].getStartState());
            System.out.println(stateObjects[i].stateName + " accept? " + stateObjects[i].getAcceptState());


        }

        int transitionsNum = (states.length * alphabet.length);
        //adds to transition map

        System.out.println(filearray[0].substring(3,4));
        for (int i = 0, j = 0, k = 0; i < stateObjects.length && j < stateObjects.length && k < transitionsNum; i++, j++, k++) {
            if (filearray[k].charAt(7) == stateObjects[i].stateName && filearray[k].charAt(1) == stateObjects[j].stateName)
                stateObjects[j].addToTransitionMap(filearray[k].substring(3,4), stateObjects[i]);
        }

    }
}