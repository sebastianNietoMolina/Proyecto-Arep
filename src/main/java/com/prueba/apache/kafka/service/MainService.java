package com.prueba.apache.kafka.service;

import com.prueba.apache.kafka.model.City;
import com.prueba.apache.kafka.model.CountryInformation;
import com.prueba.apache.kafka.persistence.GeneralRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Random;

@Service
public class MainService {

    @Autowired
    GeneralRepository generalRepository;

    @Autowired
    private ProductorKafka productorKafka;

    public String sendMessage(CountryInformation messages) throws InterruptedException {
        String message = "";
        Random random = new Random();
        int randomNumber = random.nextInt(3);
        if(randomNumber==0 ){
            message = messages.getCountryName();
            productorKafka.send(message+"-0");
        }else if(randomNumber==1 ){
            message = messages.getCoordinates();
            productorKafka.send(message+"-1");
        }else{
            message = messages.getIdCountry();
            productorKafka.send(message+"-2");
        }
        return "Salvado con éxito";
    }

    public void sendMessageToPetition(String message, String condition) throws IOException {
        HttpService httpService = new HttpService();
        City city;
        if(condition.equals("0")){
            city = httpService.doGet("http://ec2-52-90-33-141.compute-1.amazonaws.com:42000/city?name="+message);
        }else if(condition.equals("1")){
            String[] coords = message.split(",");
            city = httpService.doGet("http://ec2-3-92-134-208.compute-1.amazonaws.com:44000/city?lon="+coords[0]+"&lat="+coords[1]);
        }else{
            city = httpService.doGet("http://ec2-54-145-155-145.compute-1.amazonaws.com:43000/city?id="+message);
        }
        generalRepository.insertData(city);
    }

    public Object sendMessage2(CountryInformation messages) throws IOException {
        String message = "";
        Random random = new Random();
        int randomNumber = random.nextInt(3);
        if(randomNumber==0 ){
            message = messages.getCountryName();
            sendMessageToPetition(message, random.toString());
        }else if(randomNumber==1 ){
            message = messages.getCoordinates();
            sendMessageToPetition(message, random.toString());
        }else{
            message = messages.getIdCountry();
            sendMessageToPetition(message, random.toString());
        }
        
        return "Salvado con éxito para mensaje 2";
    }
}
