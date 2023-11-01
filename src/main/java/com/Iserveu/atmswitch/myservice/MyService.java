package com.Iserveu.atmswitch.myservice;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Year;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.MUX;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MyService {

	@Autowired
	private MUX mux;
	
//	public MyService(MUX mux) {
//	    this.mux= mux;
//	}
	
	public String response() throws ISOException {
//		ISOMsg request = new ISOMsg();
//        request.setMTI("0810");
////        request.set(2, "8180594886932489");
////        request.set(3, "010000");
////        request.set(4, "000000010000");
//        request.set(7, "0719142142");
//        request.set(11, "123456");
////        request.set(12, "012991");
////        request.set(37, "320019012991");
//        request.set(70, "001");
//		
//        ISOChannel channel = new PostChannel("127.0.0.1", 8081, new EuroPackager());
//		
//		channel.connect();
//		
//		request.dump(System.out, " ");
//        
//        channel.send(request);
//        
//        ISOMsg responseData = channel.receive();
//        
//        responseData.dump(System.out, " ");
//        
//        channel.disconnect();
//		
//		return responseData.toString();
		
		
//		Q2 q2 = new Q2("config-q2.xml");
//        q2.start();
//
//        // Obtain a reference to the MUX
//        QMUX mux = (QMUX) NameRegistrar.get("mux.mymultiplexer");
//        if (mux == null) {
//            System.err.println("MUX not found in registry");
//            return "";
//        }

	
		
		
		LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddHHmmss");
        String formattedDateTime = now.format(formatter);
		
        String stan = String.valueOf(generateRandomNumber());
        String getCurrentTime = getCurrentTime();
        String rrn = getUniqueRRN();
        
        ISOMsg request = new ISOMsg();
        request.setMTI("0200"); // Example MTI
        request.set(2, "1199911115002245");
        request.set(3, "010000");
        request.set(4, "000000010000");
	    request.set(7, formattedDateTime);
	    request.set(11, stan);
//	    request.set(11, "149249");
	    request.set(12, getCurrentTime);
	    request.set(18, "742");
	    request.set(19, "356");
	    request.set(22, "051");
	    request.set(23, "001");
	    request.set(25, "00");
	    request.set(32, "222222");
	    request.set(35, "1199911115002245=28106202880000000000");
	    request.set(37, rrn+stan);
//	    request.set(37, "329417149249");
	    request.set(41, "TEST1234");
	    request.set(42, "111111111111111");
	    request.set(43, "RuPay CP Board  MUMBAI  MHIN");
	    request.set(48, "051005ATM01058005009990990163E3802A79C855B2B");
	    request.set(49, "356");
//	    request.set(55, "9F02060000010800009F2608ED57BD45B0AF39539F2701809F10200FB501A0000004000000000000000000000000000000000000000000000000009F370484C227B99F3602000C950504000480009A032303149C01005F2A020356820219009F1A0203569F03060000000000009F330360F8C89F34034203009F3501229F1E0830373031383731388407A00000052410109F090200029F4104000000019B02E8009F1208444F4D45535449439F1B04000000005008444F4D45535449435F3401015F201A444542415348495348205341424154202020202020202020202F");
    	request.set(70, "001");
    	
        ISOMsg response = mux.request(request, 30000); // 30-second timeout

        // Process the response
        System.out.println(response);

//        q2.stop();
        
        return response.toString();
	}
	
	public static int generateRandomNumber() {
        Random random = new Random();
        int min = 100000; // Minimum 6-digit number
        int max = 999999; // Maximum 6-digit number
        return random.nextInt(max - min + 1) + min;
    }
	
	public static String getCurrentTime() {
        LocalTime now = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HHmmss");
        String formattedTime = now.format(formatter);

        return formattedTime;
    }
	
	public static String getUniqueRRN() {
		 LocalTime currentTime = LocalTime.now();
	     String hour = String.valueOf(currentTime.getHour());

	     Year currentYear = Year.now();
	        int year = currentYear.getValue();
	        String lastDigit = String.valueOf(year % 10);
	     
	        ZonedDateTime currentT = ZonedDateTime.now();
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("D");
	        String julianDay = currentT.format(formatter);
	        
       return lastDigit+julianDay+hour;
	}
}
