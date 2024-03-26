package com.nagarro.miniassignment.apiconsumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nagarro.miniassignment.models.UserData;
import com.nagarro.miniassignment.payloads.ApiResponse;

import reactor.core.publisher.Mono;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.StreamSupport;

@Service
public class ApiConsumer {

    private final WebClient webClientApi1;
    private final WebClient webClientApi2;
    private final WebClient webClientApi3;
    private final ObjectMapper objectMapper;

    @Autowired
    public ApiConsumer(WebClient webClientApi1, WebClient webClientApi2, WebClient webClientApi3, ObjectMapper objectMapper) {
        this.webClientApi1 = webClientApi1;
        this.webClientApi2 = webClientApi2;
        this.webClientApi3 = webClientApi3;
        this.objectMapper = objectMapper;
    }
   

    public Mono<UserData> callApisAndMapToObject() {
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        return  webClientApi1.get()
        		.uri("https://randomuser.me/api/")
                .retrieve()
                .bodyToMono(String.class)
                .flatMap(api1Response -> {
                    JsonNode api1Json = null;
                    try {
                        api1Json = objectMapper.readTree(api1Response);
                        // Extract necessary data from api1Json
                        String firstName = api1Json.get("results").get(0).get("name").get("first").asText();
                         System.out.println(firstName);
                        String api2Url = "https://api.nationalize.io/?name=" + firstName;
                        String api3Url = "https://api.genderize.io/?name=" + firstName;
                       
                        CompletableFuture<String> api2Response = CompletableFuture.supplyAsync(() ->
                                webClientApi2.get()
                                        .uri(api2Url)
                                        .retrieve()
                                        .bodyToMono(String.class)
                                        .block(), executorService);

                        CompletableFuture<String> api3Response = CompletableFuture.supplyAsync(() ->
                                webClientApi3.get()
                                        .uri(api3Url)
                                        .retrieve()
                                        .bodyToMono(String.class)
                                        .block(), executorService);

                        return Mono.zip(Mono.just(api1Json), Mono.fromFuture(api2Response), Mono.fromFuture(api3Response));
                    } catch (Exception e) {
                        return Mono.error(e);
                    }
                })
                .map(tuple -> {
                    try {
                        JsonNode api1Json = tuple.getT1();
                        String responseFromApi2 = tuple.getT2();
                        String responseFromApi3 = tuple.getT3();

                        // Process the responses from API1, API2, and API3 as needed
                        ApiResponse response= objectMapper.readValue(api1Json.toString(), ApiResponse.class);
                        UserData userDataFromApi1 =response.getResults()[0];
                        		
                        String FirstName = userDataFromApi1.getName().getFirst();
                        String LastName = userDataFromApi1.getName().getLast();
                        
                        userDataFromApi1.setFullName(FirstName+" "+LastName);
                        
                        String nationality = userDataFromApi1.getNat();
                        String gender = userDataFromApi1.getGender();
                        
                        
                        System.out.println("API1");
                        System.out.println("NATIONALITY--->"+userDataFromApi1.getNat());
                        System.out.println("GENDER--->"+userDataFromApi1.getGender());
                        
                        JsonNode api3Json = objectMapper.readTree(responseFromApi3);
                        String genderFromApi3 = api3Json.get("gender").asText();
                        
                        System.out.println("Gender from API3: " + genderFromApi3);
                        
                        
                        JsonNode api2Json = objectMapper.readTree(responseFromApi2);
                        System.out.println(api2Json);
                        
                        JsonNode countryArray = api2Json.get("country");

                        boolean nationalityMatched = StreamSupport.stream(countryArray.spliterator(), false)
                                .map(country -> country.get("country_id").asText())
                                .anyMatch(countryId -> countryId.equals(nationality));
                        System.out.println(nationalityMatched);


        	         // Validate the conditions
        	            if (nationalityMatched && gender.equals(genderFromApi3)) {
        	            	userDataFromApi1.setVerificationStatus("VERIFIED");
        	                System.out.println("Validation successful: Nationality and gender match!");
        	            } else {
        	            	userDataFromApi1.setVerificationStatus("TO_BE_VERIFIED");
        	                System.out.println("Validation failed: Nationality or gender does not match!");
        	            }
        	            
        	            
                        System.out.println("Response from API2: " + responseFromApi2);
                        System.out.println("Response from API3: " + responseFromApi3);

                        

                        // Return the UserData object from API1
                        return userDataFromApi1;
                    } catch (Exception e) {
                        throw new RuntimeException("Error processing API responses", e);
                    } finally {
                        executorService.shutdown();
                    }
                });
    }
  

}
