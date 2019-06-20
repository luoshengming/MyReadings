package io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Read and print, using BufferedReader from System.in, onto System.out
 */
// BEGIN main
public class CatStdin {

    public static void main(String[] av) {
        try {
            BufferedReader is = new BufferedReader(new InputStreamReader(System.in));
            String inputLine;

            while ((inputLine = is.readLine()) != null) {
                System.out.println(inputLine);
            }
            is.close();
        } catch (IOException e) {
            System.out.println("IOException: " + e);
        }
    }
}
// END main
