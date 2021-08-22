package com.udemyforbusiness.threads;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class WordSearchHttpServer {
    /**
     *
     */
    private static final int AVAILABLE_PROCESSORS = 8;// Runtime.getRuntime().availableProcessors();

    public static void main(String[] args) throws FileNotFoundException, IOException {
        File file = new File("C:\\Users\\praka\\Projects-GFG\\ds-algo\\src\\main\\resources\\war_and_peace.txt");
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String book = bufferedReader.lines()
                .reduce((accumulator, newLine) -> accumulator + System.lineSeparator() + newLine).get();
        bufferedReader.close();
        startServer(book);
    }

    private static void startServer(String book) throws IOException {
        HttpServer httpServer = HttpServer.create(new InetSocketAddress("127.0.0.1", 8081), 0);
        httpServer.createContext("/book/warandpeace/search/", new BookWordSearchHandler(book));
        Executor executor = Executors.newFixedThreadPool(AVAILABLE_PROCESSORS);
        httpServer.setExecutor(executor);

        System.out.println("Starting Server...");
        httpServer.start();
        System.out.println("Started Server...");
    }

    private static class BookWordSearchHandler implements HttpHandler {
        private final String book;

        protected BookWordSearchHandler(String book) {
            this.book = book;
        }

        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String query = exchange.getRequestURI().getQuery();
            String[] queryParam = query.split("=");
            String keyParam = queryParam[0];
            String valueParam = queryParam[1];

            if (!keyParam.equals("word")) {
                exchange.sendResponseHeaders(404, 0);
            }

            int noOfOccurences = searchWordInBook(valueParam, book);
            byte[] response = Long.toString(noOfOccurences).getBytes();
            exchange.sendResponseHeaders(200, response.length);

            OutputStream responseStream = exchange.getResponseBody();
            responseStream.write(response);
            responseStream.close();
        }

        private static int searchWordInBook(String word, String book) {
            int numOfOccurences = 0;
            int index = 0;
            while (index >= 0) {
                index = book.indexOf(word, index);
                if (index != -1) {
                    numOfOccurences++;
                    index++;
                }
            }

            return numOfOccurences;
        }
    }
}
