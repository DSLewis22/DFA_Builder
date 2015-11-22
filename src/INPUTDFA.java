

import java.io.*;

public class INPUTDFA {
    public static void main(String[] args) throws IOException {
        FileInputStream in = new FileInputStream("/Users/jlarkin9/Desktop/test.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(in));

        String strLine = null;

        String[] filearray;
        filearray = new String[12];

        while ((filearray[0] = br.readLine()) != null) {

            System.out.println(filearray[0]);

            for (int j = 1; j < filearray.length; j++){
                filearray[j] = br.readLine();
                System.out.println(filearray[j]);
            }

            String[] alphabet = filearray[0].split(",");
            for (int j = 0; j < alphabet.length; j++) {
                
            }
        }
        in.close();
    }
}






