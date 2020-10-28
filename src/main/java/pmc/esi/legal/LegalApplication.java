package pmc.esi.legal;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pmc.esi.legal.handlers.SOAPConnector;
import pmc.esi.legal.soap.client.gen.GetFullLegalEntityInfo;
import pmc.esi.legal.soap.client.gen.LEGALENTITYINFORMATION;
import pmc.esi.legal.soap.testclient.gen.NumberToDollars;
import pmc.esi.legal.soap.testclient.gen.NumberToDollarsResponse;

import java.math.BigDecimal;

@SpringBootApplication
public class LegalApplication {

    public static void main(String[] args) {
        SpringApplication.run(LegalApplication.class, args);
    }

    @Bean
    CommandLineRunner lookup(SOAPConnector soapConnector) {
        return args -> {
            NumberToDollars request = new NumberToDollars();
            request.setDNum(BigDecimal.valueOf(1000));

            NumberToDollarsResponse response = (NumberToDollarsResponse) soapConnector.callWebService("http://www.dataaccess.com/webservicesserver/numberconversion.wso", request);
            System.out.println("Got Response As below ========= : ");
            System.out.println("data : " + response.getNumberToDollarsResult());

        };
    }
}
