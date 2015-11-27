import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
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
    public static void main(String[] args)
    { // first accept the rules to the DFA within the command line argument.

        if(args.length == 0)
        {
            // tell the user to specify a path to the text file.
        }
        else if(Files.notExists(Paths.get(args[0])))
        {
            // if the File does not exist.... tell the user it doesn't exist.
        }
        else { parse(args[0]);} // check the parse method below to fill it in accordindly.


        // After everything is parse.. read input from the user for a string to be tested.
        // then call the testString Method located within the dfa class and pass it in and it'll tell you
        // if it's accepted or not.
    }

    public static void parse(String path)
    {
        List<String> file = null; // this is to utilize the function File.ReadAllLines().It returns a List of String.
                                  // since this needs to handle ANY DFA that gets passed regardless of the number of states Arrays won't.
        try {
            file = Files.readAllLines(Paths.get(path), StandardCharsets.UTF_8); // curious about this... just ask me.

        }catch(Exception e)
        {
            // spit out the exception message.
        }

        /* now you just read from the file variable line by line(for loop).. Things to Remember.
         * Line 0 is always the alphabet... Do not read line 0 cause we don't need it so start from Line 1.
         * Line 1 is ALWAYS the State.
            - When going through Line 1, there's a few things to Note.
            1. You can use the File.Get() function and split("")(you'll need another loop after you split)
              then check to see if the value is the value a CharOrDigit.. if it is.. utilize the addState Method to create a new State.
              it'll look like..
              if(Character.isLetterOrDigit(str.charAt(0)) {//Create State here and add it to the DFA}
        * Line 2 is ALWAYS the Start State.
            - since we Created the states before knowing if it was the start or Accept state. we'll need a way to find the State.
            - The DFA class has a findState method... use that to find State and set it's StarttState value accordingly.
            - Pointer objects will work perfectly here(Temp State).
        * Line 3 is ALWAYS the Accept State.
            - This will eseentially be the exact same code for line 2 but keep in mind that
              there can be more than one Accept State...
        * Any Line Greater than 3 will always be the Transitioning.
           -Utilize the .addTransitionMap fucntion within the State Class to populate that State's map.
           -the function takes in 2 parameters... the transitionValue and the State that'll it go to.
           - For example, in the Sample Text File.. the first Transition is (a,0)->b
           - so you'll need to >>find<< the State that corresponds with 'a'. then call that State's addToTransitionMap function.
             and pass in 0 and then pass in the State corresponding with 'b'. (you'll need to >>find<< that State as well)
           - so it'll look something like this once you find the State objects. a.addToTransitionMap(0,b).
             although i would not recommend naming the variables 'a' 'b' since those need to be object names.
             use variable names such as originState.addToTransition("0",transitionState);
        *
        *  Then you're done... Just utilize the Methods that the DFA and the State Class will have...

        */


    }
}
