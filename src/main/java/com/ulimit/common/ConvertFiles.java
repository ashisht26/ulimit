package com.ulimit.common;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import java.util.HashMap;
import java.util.concurrent.*;

public class ConvertFiles{

    ParseFiles parse;

    public ParseFiles getParse() {
        return parse;
    }

    public void setParse(ParseFiles parse) {
        this.parse = parse;
    }

    /*  Method Name :   convertFiles
        Arguments   :   Type String array used for file name as input
        Description :   This method will call suitable parseFile method and converts the output POJOs into JSON format.
    */
    public void convertFiles(String[] args) throws InterruptedException, ExecutionException {

        final HashMap<String,String> result = new HashMap<>();
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        Callable<String> task1 = () -> {

            for (String arg : args) {
                if (arg.contains(".csv")) {
                    result.put("CSV", parse.parseCsvFile(arg));
                } else if (arg.contains(".json")) {
                    result.put("JSON", parse.parseJsonFile(arg));
                }
            }

            return "OK";
        };

            Callable<String> task2 = () -> {

                Gson gson = new Gson();
                parse.rows.parallelStream().forEach(row ->
                        {
                            JsonElement jsonElement = gson.toJsonTree(row);
                            if(row.getFileName().contains(".csv"))
                                jsonElement.getAsJsonObject().addProperty("result", result.get("CSV"));
                            else if(row.getFileName().contains(".json"))
                                jsonElement.getAsJsonObject().addProperty("result", result.get("JSON"));
                            System.out.println(gson.toJson(jsonElement));
                        }
                );

                return "OK";
            };

        Future<String> future1 = executorService.submit(task1);
        Future<String> future2 = executorService.submit(task2);

        String res1 = future1.get();
        String res2 = future2.get();
        //System.out.println(res1);
        //System.out.println(res2);

        executorService.shutdown();
    }


}