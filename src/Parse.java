import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
 * this class parses the Text File.
 * Creates the States and passes them to the DFA
 * This classes also populates each STate Transition Map(a Field in the State Class)
 * it will also accept a string to pass to the DFA class to see if it's accepted or not.
 * This class just really facilitates everything that is going on under the hood.
 * UTILIZE THE METHODS THAT ARE IN THE DFA CLASS AND THE STATE CLASS.
 */
public class Parse {

    static String data; // data to be read.
    static DFA dfa = new DFA(); // this is the dfa that'll hold all the states and tell each state what to do basically.
    public static void main (String[] args) throws IOException
    { // first accept the rules to the DFA within the command line argument.

        if(args.length == 0)
        {
            System.out.println("Specify a path ");
            return;
        }
        else if(Files.notExists(Paths.get(args[0])))
        {
            System.out.println("File not found ");
            return;
        }
        else
        {
            System.out.println("Parsing file");
            parse(args[0]);
            dfa.orderStates();
            System.out.println("Ready for input.");
        }

        // continuous input from the user.
        Scanner readInput = new Scanner(System.in);
        data = readInput.nextLine();
        dfa.testString(data);
        System.out.println("Press Enter if you're done testing inputs");

        while(true)
        {
            readInput = new Scanner(System.in);
            data = readInput.nextLine();
            if(data.isEmpty())
            {
                System.out.print("Program will terminate now");
                return;
            }
            else
                dfa.testString(data);
        }

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
        String[] accept = filearray[3].split("");
        for (String nameOfAcceptStates : accept) // loop through accept array and find the states.
        {
            State acceptTemp = dfa.findState(nameOfAcceptStates.charAt(0)); // pointers(Temp Variable) going to work.
            acceptTemp.setAcceptState(true);
        }
        
        //add to transition map
        for(int k = 4; k < filearray.length;k++) {
            filearray[k] = filearray[k].replace('(', ' ').replace(')', ' ').trim(); // replacing the '( )' with blank spaces. and then trim the blank space
            char currentStateName = filearray[k].charAt(0);
            char NextStatechar = filearray[k].charAt(6);
            State currentState = dfa.findState(currentStateName);
            State nextState = dfa.findState(NextStatechar);
            currentState.addToTransitionMap(filearray[k].substring(2,3),nextState);
        }

    }
}