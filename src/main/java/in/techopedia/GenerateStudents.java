package in.techopedia;

import com.google.gson.Gson;
import in.model.Student;

import java.io.File;
import java.io.PrintStream;
import java.util.*;

public class GenerateStudents {
    static String getAlphaNumericString(int n)
    {

        // lower limit for LowerCase Letters
        int lowerLimit = (int) 'a';

        // lower limit for LowerCase Letters
        int upperLimit = (int) 'z';

        Random random = new Random();

        // Create a StringBuffer to store the result
        StringBuffer r = new StringBuffer(n);

        for (int i = 0; i < n; i++) {

            // take a random value between 97 and 122
            int nextRandomChar = lowerLimit
                    + (int)(random.nextFloat()
                    * (upperLimit - lowerLimit + 1));

            // append a character at the end of bs
            r.append((char)nextRandomChar);
        }

        // return the resultant string
        return r.toString();
    }

    public static void main(String[] args) throws Exception {
        PrintStream o = new PrintStream(new File("A.txt"));

        // Store current System.out before assigning a new value
        PrintStream console = System.out;

        // Assign o to output stream
        System.setOut(o);
        //System.out.println("This will be written to the text file");

        Set<Integer> studentIds = new HashSet<>();
        List<String> hostels = new ArrayList<>(Arrays.asList("ah1", "ah-2", "ah3", "ah4", "ah-5", "ah-6", "ah-7"
        ,"ch-1", "ch-2", "ch-3", "ch-6"));

        for (int counter = 0; counter < 1000000; ++counter) {
            Random rand = new Random();
            Integer nextId = rand.nextInt(100000);
            if (!studentIds.contains(nextId)) {
                String firstName = getAlphaNumericString(rand.nextInt(15));
                String lastName = getAlphaNumericString(rand.nextInt(15));
                String hostel = hostels.get(rand.nextInt(hostels.size()-1));
                Student student = new Student(nextId.toString(), firstName, lastName, hostel);
                Gson gson = new Gson();
                System.out.println(gson.toJsonTree(student));
                studentIds.add(nextId);
            }
        }
    }
}
