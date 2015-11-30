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

    static DFA dfa = new DFA(); // this is the dfa that'll hold all the states and tell each state what to do basically.
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
        //reading text file
        List<String> lines = new ArrayList<String>();
        FileReader in = new FileReader(path);
        BufferedReader br = new BufferedReader(in);

        String strLine = null;


        while ((strLine = br.readLine()) != null) {
            lines.add(strLine);

        }

        br.close();

        //convert to array of text file lines
        String[] filearray = lines.toArray(new String[lines.size()]);

        //removing brackets
        filearray[0] = filearray[0].substring(1, filearray[0].indexOf('}'));
        filearray[1] = filearray[1].substring(1, filearray[1].indexOf('}'));
        filearray[3] = filearray[3].substring(1, filearray[3].indexOf('}'));

        //split text file lines; create states
        String[] alphabet = filearray[0].split(",");  // NECESSARY???

        String[] states = filearray[1].split(",");
        for (String name : states) {
            dfa.addState(new State(name.charAt(0)));
        }

        //start state
        String startState = filearray[2];
        State tempStart = dfa.findState(startState.charAt(0));
        tempStart.setStartState(true);

        //accept states
        String[] accept = filearray[3].split(",");
        for (String nameOfAcceptStates : accept) // loop through accept array and find the states.
        {
            State acceptTemp = dfa.findState(nameOfAcceptStates.charAt(0)); // pointers(Temp Variable) going to work.
            acceptTemp.setAcceptState(true);
        }
        
        //add to transition map
        for(int k = 4; k < filearray.length;k++) {
            State currentState = dfa.findState(filearray[k].charAt(0));
            State nextState = dfa.findState(filearray[k].charAt(7));
            currentState.addToTransitionMap(filearray[k].substring(3,4),nextState);

        }

    }
}