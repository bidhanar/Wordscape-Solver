package com.example.wordscape_solution;


import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import java.io.*;
import java.lang.reflect.Array;
import java.util.*;
import java.util.logging.FileHandler;
import java.util.stream.StreamSupport;

public class Finder {

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static void main(String[] args)  {


    }

    //This is the caller function
    @RequiresApi(api = Build.VERSION_CODES.N)
    public static ArrayList<String> caller(String input, BufferedReader br) throws IOException{

        // Declaring a string variable
        String st;

        HashMap<String , Integer> dict = new HashMap<String, Integer>();

        // Condition holds true till
        // there is character in a string
        while ((st = br.readLine()) != null) {
//            System.out.print("\"" + st.toLowerCase()+"\" ," + " 1 ,");
            dict.put(st.toLowerCase(), 1);
        }

//        Map<String, Integer> map = Map.of();

//        System.out.println("Mappings of ht1 : " + dict);

        //Finding All different combinations
        ArrayList<String> words = new ArrayList<String>();
        ArrayList<String> temp = new ArrayList<String>();
//        Scanner sc = new Scanner(System.in);
//        System.out.println("Please enter the letters as a single string:");
//        String input = sc.nextLine();
        ArrayList<String> arrayList = combs(input,0);
        for(String s : arrayList){
            if(s.length() >= 3)
                temp.add(s);
        }
        words = all_words(temp);
        Collections.sort(words, Comparator.comparing(String::length));

        //Finding all the words from the dict
        ArrayList<String> result = new ArrayList<String>();
        for(String k : words){
            k = k.substring(0,k.length()-1);
//            System.out.print(k);
            try{
                int i = dict.get(k);
//                System.out.println(k);
                if(!result.contains(k.toUpperCase()))
                    result.add(k.toUpperCase());
            }
            catch (Exception e){

            }
//            if(dict.contains(k))
//                System.out.println(k);
        }
        for(String s : result){
            Log.i("Result","The word is " + s);
        }
        return result;
    }

    public static ArrayList<String> combs(String letters, int startIdx) {
        //base cases
        if (startIdx == letters.length()){

            ArrayList<String> arrayList = new ArrayList<String>();
            arrayList.add("");
            return arrayList;

        }
        //recursive cases
        ArrayList<String> res, temp = new ArrayList<String>();
        //exclude
        res = combs(letters, startIdx + 1);
        //include
        temp = (ArrayList<String>) res.clone();
        for(int i = 0; i < temp.size(); i++) {
            temp.set(i, temp.get(i) + String.valueOf(letters.charAt(startIdx)));
            res.add(temp.get(i));
        }

        return res;
    }

    //This function returns all the permutations of a list of words
    public static ArrayList<String> all_words(ArrayList<String> words) {
        ArrayList<String> all = new ArrayList<String>();
        for(int i = 0; i < words.size(); i++) {
            ArrayList<String> arrayList = new ArrayList<String>();
            ArrayList<String> temp = new ArrayList<String>();
            temp = permutes(words.get(i), "", arrayList);
            for(String s : temp){
                all.add(s);
            }
        }
        return all;
    }

    //Function to return all the permutations of string
    public static ArrayList<String> permutes(String str, String ans, ArrayList<String> arrayList) {

        // If string is empty
        if (str.length() == 0) {
            arrayList.add(ans + " ");
//          System.out.print(ans + " ");
            return arrayList;
        }

        for (int i = 0; i < str.length(); i++) {
            // ith character of str
            char ch = str.charAt(i);
            // Rest of the string after excluding
            // the ith character
            String ros = str.substring(0, i) + str.substring(i + 1);
            // Recursive step
            permutes(ros, ans + ch, arrayList);
        }
        return arrayList;
    }

}

