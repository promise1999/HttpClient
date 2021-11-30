package com.keyin.httpclient;

import com.fasterxml.jackson.databind.ObjectMapper;
import sun.net.www.protocol.http.HttpURLConnection;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Scanner;

class MainHTTPClient<input> {
    private static HttpRequest client;
    private static HttpRequest request;
    private static HttpRequest request1;
    private static Scanner scanner;
    private static Scanner scanner1;
    private static int input;
    private static String input1;
    private static String input2;
    private static String input3;
    private static HashMap<String, String> values;
    private static String firstName, lastName, emailAddress;
    private static String phoneNumber;
    private static String startDate, endDate;
    private static ObjectMapper objectMapper;
    private static HttpRequest postRequest;
    private static HttpRequest postRequest1;
    private static HttpRequest putRequest;
    private static HttpResponse response;
    private static String requestBody;



    public static void main(String[] args) throws IOException {
        while (true) {
            HttpClient client = HttpClient.newHttpClient();
            System.out.println("Hello: Choose an API call number or enter 0 to exit");
            System.out.println("Choice: 1. Get all person. 2. Get all person by Last Name. 3. Search By Id. 4. Enter your desired Id. 5. Create a New Post");
            scanner = new Scanner(System.in);
            scanner1 = new Scanner(System.in);
            input = scanner.nextInt();

            //GET METHOD FOR PERSON
            switch (input) {
                case 0:
                    System.out.println("Bye!");
                    System.exit(0);
                case 1:
                    HttpRequest request = HttpRequest.newBuilder().uri(URI.create("http://localhost:8080/api/person")).build();

                    try {
                        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                        if (response.statusCode() == 200) {
                            System.out.println("*****" + response.body());
                        }

                    } catch (IOException | InterruptedException e) {
                        e.printStackTrace();
                    }
                    break;

                case 2:
                    System.out.println("Please enter the Last Name you wish to search");
                    input1 = scanner.next();
                    request = HttpRequest.newBuilder().uri(URI.create("http://localhost:8080/api/person?lastName=" + input1)).build();
                    try {
                        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                        if (response.statusCode() == 200) {
                            System.out.println("*****" + response.body());
                        }

                    } catch (IOException | InterruptedException e) {
                        e.printStackTrace();
                    }
                    break;

                case 3:
                    System.out.println("Please enter the Id you wish to search");
                    input = scanner.nextInt();
                    request = HttpRequest.newBuilder().uri(URI.create("http://localhost:8080/api/person?Id=" + input)).build();
                    try {
                        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                        if (response.statusCode() == 200) {
                            System.out.println("*****" + response.body());
                        }

                    } catch (IOException | InterruptedException e) {
                        e.printStackTrace();
                    }
                    break;

                //PUT METHOD FOR PERSON
                case 4:
                    System.out.println("Please enter the Id");
                    input = scanner.nextInt();
                    request = HttpRequest.newBuilder().uri(URI.create("http://localhost:8080/api/person?Id=" + input)).build();

                    URL url = null;
                    try {
                        url = new URL("http://localhost:8080/api/person?Id=");
                    } catch (MalformedURLException exception) {
                        exception.printStackTrace();
                    }
                    HttpURLConnection httpURLConnection = null;
                    DataOutputStream dataOutputStream = null;
                    try {
                        httpURLConnection = (HttpURLConnection) url.openConnection();
                        httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                        httpURLConnection.setRequestMethod("PUT");
                        httpURLConnection.setDoInput(true);
                        httpURLConnection.setDoOutput(true);
                    } catch (IOException exception) {
                        exception.printStackTrace();

                    }

                    //POST METHOD FOR PERSON
                case 5:
                    System.out.println("create a post");
                    System.out.println("Please enter the firstName");
                    firstName = scanner.next();
                    System.out.println("Please enter the lastName");
                    lastName = scanner.next();
                    System.out.println("Please enter the EmailAddress");
                    emailAddress = scanner.next();
                    System.out.println("Please enter the Phone Number");
                    phoneNumber = scanner1.next();
                    System.out.println("Please enter the Start Date yyyy-mm-dd");
                    startDate = scanner.next();
                    System.out.println("Please enter the End Date yyyy-mm-dd");
                    endDate = scanner.next();

                    values = new HashMap<String, String>() {{
                        put("firstName", firstName);
                        put("lastName", lastName);
                        put("emailAddress", emailAddress);
                        put("phoneNumber", phoneNumber);
                        put("startDate", startDate);
                        put("endDate", endDate);

                    }};
                    objectMapper = new ObjectMapper();
                    requestBody = objectMapper.writeValueAsString(values);
                    postRequest = HttpRequest.newBuilder()
                            .uri(URI.create("http://localhost:8080/api/person"))
                            .header("Content-Type", "application/json")
                            .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                            .build();
                    try {
                        response = client.send(postRequest,
                                HttpResponse.BodyHandlers.ofString());
                        if (response.statusCode() == 200) {
                            System.out.println(response.body());
                            System.out.println();
                        } else if (response.statusCode() == 201) {
                            System.out.println("Created a Post Successfully");
                        } else {
                            System.out.print("Post failed to create : " + response.statusCode());
                        }
                    } catch (IOException | InterruptedException e) {
                        e.printStackTrace();
                    }
                    break;

                //DELETE METHOD FOR PERSON
                case 6:

                    System.out.println("Please enter the Id you wish to delete");
                    input = scanner.nextInt();
                    request = HttpRequest.newBuilder().uri(URI.create("http://localhost:8080/api/person?Id=" + input)).build();
                    url = null;
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("DELETE");
                    int responseCode = connection.getResponseCode();

                    break;

                //GET METHOD FOR CURRENT TOURNAMENT
                case 7:
                    HttpRequest request1 = HttpRequest.newBuilder().uri(URI.create("http://localhost:8080/api/currentTournament")).build();

                    try {
                        HttpResponse<String> response = client.send(request1, HttpResponse.BodyHandlers.ofString());
                        if (response.statusCode() == 200) {
                            System.out.println("*****" + response.body());
                        }

                    } catch (IOException | InterruptedException e) {
                        e.printStackTrace();
                    }
                    break;

                case 8:
                    System.out.println("Please enter the Current Tournament Start Date you wish to search");
                    input2 = scanner.next();
                    request = HttpRequest.newBuilder().uri(URI.create("http://localhost:8080/api/currentTournament?startDate=" + input2)).build();
                    try {
                        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                        if (response.statusCode() == 200) {
                            System.out.println("*****" + response.body());
                        }

                    } catch (IOException | InterruptedException e) {
                        e.printStackTrace();
                    }
                    break;



                //POST METHOD FOR CURRENT TOURNAMENT
                case 13:
                    System.out.println("create a post for current tournament");
                    System.out.println("Please enter the start Date yyyy-mm-dd");
                    startDate = scanner.next();
                    System.out.println("Please enter the end Date yyyy-mm-dd");
                    endDate = scanner.next();
                    System.out.println("Please enter the Location");


                    values = new HashMap<String, String>() {{
                        put("firstName", firstName);
                        put("lastName", lastName);
                        put("emailAddress", emailAddress);
                        put("phoneNumber", phoneNumber);
                        put("startDate", startDate);
                        put("endDate", endDate);

                    }};
                    objectMapper = new ObjectMapper();
                    requestBody = objectMapper.writeValueAsString(values);
                    postRequest = HttpRequest.newBuilder()
                            .uri(URI.create("http://localhost:8080/api/person"))
                            .header("Content-Type", "application/json")
                            .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                            .build();
                    try {
                        response = client.send(postRequest,
                                HttpResponse.BodyHandlers.ofString());
                        if (response.statusCode() == 200) {
                            System.out.println(response.body());
                            System.out.println();
                        } else if (response.statusCode() == 201) {
                            System.out.println("Created a Post Successfully");
                        } else {
                            System.out.print("Post failed to create : " + response.statusCode());
                        }
                    } catch (IOException | InterruptedException e) {
                        e.printStackTrace();
                    }
                    break;

                //GET METHOD FOR PAST TOURNAMENT
                case 9:
                    System.out.println("View all Past Tournament");
                    input2 = scanner.next();
                    HttpRequest request2 = HttpRequest.newBuilder().uri(URI.create("http://localhost:8080/api/pastTournament")).build();

                    try {
                        HttpResponse<String> response = client.send(request2, HttpResponse.BodyHandlers.ofString());
                        if (response.statusCode() == 200) {
                            System.out.println("*****" + response.body());
                        }

                    } catch (IOException | InterruptedException e) {
                        e.printStackTrace();
                    }
                    break;

                case 10:
                    System.out.println("Please enter the Past Tournament End Date you wish to search");
                    input2 = scanner.next();
                    request = HttpRequest.newBuilder().uri(URI.create("http://localhost:8080/api/currentTournament?endDate=" + input2)).build();
                    try {
                        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                        if (response.statusCode() == 200) {
                            System.out.println("*****" + response.body());
                        }

                    } catch (IOException | InterruptedException e) {
                        e.printStackTrace();
                    }
                    break;

                // GET METHOD FOR MEMBERSHIP
                case 11:
                    System.out.println("View all MEMBERSHIP");
                    input3 = scanner.next();
                    HttpRequest request3 = HttpRequest.newBuilder().uri(URI.create("http://localhost:8080/api/membership")).build();

                    try {
                        HttpResponse<String> response = client.send(request3, HttpResponse.BodyHandlers.ofString());
                        if (response.statusCode() == 200) {
                            System.out.println("*****" + response.body());
                        }

                    } catch (IOException | InterruptedException e) {
                        e.printStackTrace();
                    }
                    break;

                case 12:
                    System.out.println("Please enter Membership Type");
                    input3 = scanner.next();
                    request = HttpRequest.newBuilder().uri(URI.create("http://localhost:8080/api/currentTournament?normal=" + input2)).build();
                    try {
                        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                        if (response.statusCode() == 200) {
                            System.out.println("*****" + response.body());
                        }

                    } catch (IOException | InterruptedException e) {
                        e.printStackTrace();
                    }
                    break;


            }
        }
    }
}

