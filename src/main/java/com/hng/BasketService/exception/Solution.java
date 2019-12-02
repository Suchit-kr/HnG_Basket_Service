package com.hng.BasketService.exception;

import java.io.*;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int songsCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> songs = IntStream.range(0, songsCount).mapToObj(i -> {
            try {
                return bufferedReader.readLine().replaceAll("\\s+$", "");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        })
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(toList());

        long result = Result.playlist(songs);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
class Result {

    /*
     * Complete the 'playlist' function below.
     *
     * The function is expected to return a LONG_INTEGER.
     * The function accepts INTEGER_ARRAY songs as parameter.
     */

    public static long playlist(List<Integer> songs) {
        // Write your code here
        int count=0;
        for(int i=0; i<songs.size();i++){
            for( int j=i+1;j<songs.size();j++){
                int sum= songs.get(i)+songs.get(j);
                if(sum/songs.get(i)==0 && sum/songs.get(j)==0){
                    count++;
                }
            }
        }

        return count;
    }

}
