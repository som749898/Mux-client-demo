package com.Iserveu.atmswitch;

import org.jpos.iso.ISOUtil;
import org.jpos.iso.MUX;
import org.jpos.q2.Q2;
import org.jpos.q2.iso.QMUX;
import org.jpos.util.NameRegistrar.NotFoundException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AtmSwitchApplication {

	public static void main(String[] args) {
		SpringApplication.run(AtmSwitchApplication.class, args);
	}
	
    public static String convertHexToAscii(String hexString) {
        StringBuilder asciiString = new StringBuilder();

        for (int i = 0; i < hexString.length(); i += 2) {
            String hex = hexString.substring(i, i + 2);
            int decimal = Integer.parseInt(hex, 16);
            asciiString.append((char) decimal);
        }

        return asciiString.toString();
    }
	
    public static String convertAsciiToHex(String asciiString) {
        StringBuilder hexString = new StringBuilder();

        for (int i = 0; i < asciiString.length(); i++) {
            char c = asciiString.charAt(i);
            String hex = String.format("%02X", (int) c); // Convert character to 2-digit hex representation
            hexString.append(hex);
        }

        return hexString.toString();
    }
	
	// Client mux configuration
	@Bean(name="Q2")
    public Q2 q2() {
        Q2 q2 = new Q2();
        q2.start();
        return q2;
    }
	
	@Bean
    public MUX mux (Q2 q2) throws NotFoundException {
    while (!q2.ready()) {
    ISOUtil.sleep(10);
    	}
    return QMUX.getMUX("my-mux");
    }
	
}