

import java.io.*;

public class INPUTDFA {
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

        }
        in.close();

    }
}






