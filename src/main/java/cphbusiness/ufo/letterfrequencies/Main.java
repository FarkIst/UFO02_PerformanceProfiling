package cphbusiness.ufo.letterfrequencies;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import static java.util.stream.Collectors.toMap;

/**
 * Frequency analysis Inspired by
 * https://en.wikipedia.org/wiki/Frequency_analysis
 *
 * @author kasper
 */
public class Main {
    
    private static String _fileContents;
    
    public static void main(String[] args) throws FileNotFoundException, IOException {
        String fileName = "./src/main/resources/FoundationSeries.txt";
        readToString(fileName);
        Reader reader = new StringReader(_fileContents);
       // Map<Integer, Long> freq = new HashMap<>();

        tallyCharsOptimized();
       // tallyChars(reader, freq);
       // print_tally(freq);
    }

    private static void readToString(String fileName) throws IOException{
        _fileContents = new String(Files.readAllBytes(Paths.get(fileName)), StandardCharsets.UTF_8);
    }
    
    private static void tallyCharsOptimized(){
        HashMap<Integer, Integer> hash = new HashMap<>();

            for (int i = 0; i < _fileContents.length(); i++) {
                char c = _fileContents.charAt(i);
                if (c != ' ') {
                    // Increment existing value in HashMap.
                    // ... Start with zero if no key exists.
                    int value = hash.getOrDefault((int) c, 0);
                    hash.put((int) c, value + 1);
                }
            }  
            
            for (int key : hash.keySet()) {
            System.out.println((char) key + ": " + hash.get(key));
        }
    }
    
    private static void tallyChars(Reader reader, Map<Integer, Long> freq) throws IOException {
        int b;
        while ((b = reader.read()) != -1) {
            try {
                freq.put(b, freq.get(b) + 1);
            } catch (NullPointerException np) {
                freq.put(b, 2L);                
            }
        }
    }

    private static void print_tally(Map<Integer, Long> freq) {
        int dist = 'a' - 'A';
        Map<Character, Long> upperAndlower = new LinkedHashMap();
        for (Character c = 'A'; c <= 'Z'; c++) {
            upperAndlower.put(c, freq.getOrDefault(c, 0L) + freq.getOrDefault(c + dist, 0L));
        }
        Map<Character, Long> sorted = upperAndlower
                .entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .collect(
                        toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2,
                                LinkedHashMap::new));
        for (Character c : sorted.keySet()) {
            System.out.println("" + c + ": " + sorted.get(c));;
        }
    }
}
