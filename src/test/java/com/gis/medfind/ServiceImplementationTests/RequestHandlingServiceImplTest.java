package com.gis.medfind.ServiceImplementationTests;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.gis.medfind.entity.FileInfo;
import com.gis.medfind.entity.Request;
import com.gis.medfind.repository.FileInfoRepository;
import com.gis.medfind.repository.PharmacyRepository;
import com.gis.medfind.repository.RequestRepository;
import com.gis.medfind.serviceImplem.RequestHandlingServiceImpl;

import org.junit.jupiter.api.Test;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
 
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class RequestHandlingServiceImplTest {     
     
    @Autowired
    private PharmacyRepository pharmRepo;
     
    @Autowired
    private RequestHandlingServiceImpl requestHandlingService;

    @Autowired
    private RequestRepository requestRepo;

    @Autowired
    private FileInfoRepository fileService;
    
    @Autowired
    private GeometryFactory geom;

    @Test
    public void testNewRequest() {
        Request newRequest = new Request();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
        LocalDateTime now = LocalDateTime.now();  
        String currentDateTime  = dtf.format(now);  
            newRequest.setCreatedDate(currentDateTime);
            newRequest.setEmail("habtetsegaye19@gmail.com");
                FileInfo license = new FileInfo();
                    license = fileService.save(license);
            newRequest.setLicenseFile(license);
            newRequest.setLocation(geom.createPoint(new Coordinate(78.89, 22.2)));
            newRequest.setPharmacyName("ST. MARKOS");
            newRequest.setSenderFullName("Habte Tsegaye");
            newRequest = requestRepo.save(newRequest);

            requestHandlingService.newRequest(newRequest);
            
    }

    @Test
    public void acceptNewRequest() {
        Request newRequest = new Request();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
        LocalDateTime now = LocalDateTime.now();  
        String currentDateTime  = dtf.format(now);  
            newRequest.setCreatedDate(currentDateTime);
            newRequest.setEmail("habtetsegaye19@gmail.com");
                FileInfo license = new FileInfo();
                    license = fileService.save(license);
            newRequest.setLicenseFile(license);
            newRequest.setLocation(geom.createPoint(new Coordinate(78.89, 22.2)));
            newRequest.setPharmacyName("ST. MARKOS");
            newRequest.setSenderFullName("Habte Tsegaye");
            newRequest = requestRepo.save(newRequest);

            requestHandlingService.newRequest(newRequest);
            long initialCount = pharmRepo.count();
           // requestHandlingService.acceptRequest(newRequest);
            assertThat(requestHandlingService.getAllRequests()).isEqualTo(0);
            assertThat(pharmRepo.count()).isEqualTo(initialCount);
    }

    @Test
    public void rejectRequest() {
        Request newRequest = new Request();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
        LocalDateTime now = LocalDateTime.now();  
        String currentDateTime  = dtf.format(now);  
            newRequest.setCreatedDate(currentDateTime);
            newRequest.setEmail("habtetsegaye19@gmail.com");
                FileInfo license = new FileInfo();
                    license = fileService.save(license);
            newRequest.setLicenseFile(license);
            newRequest.setLocation(geom.createPoint(new Coordinate(78.89, 22.2)));
            newRequest.setPharmacyName("ST. MARKOS");
            newRequest.setSenderFullName("Habte Tsegaye");
            newRequest = requestRepo.save(newRequest);

            requestHandlingService.newRequest(newRequest);

            requestHandlingService.rejectRequest(newRequest.getId());
            assertThat(requestHandlingService.getAllRequests()).isEqualTo(0);
    }

    @Test
    public void getAllRequest() {
        Request newRequest = new Request();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
        LocalDateTime now = LocalDateTime.now();  
        String currentDateTime  = dtf.format(now);  
            newRequest.setCreatedDate(currentDateTime);
            newRequest.setEmail("habtetsegaye19@gmail.com");
                FileInfo license = new FileInfo();
                    license = fileService.save(license);
            newRequest.setLicenseFile(license);
            newRequest.setLocation(geom.createPoint(new Coordinate(78.89, 22.2)));
            newRequest.setPharmacyName("ST. MARKOS");
            newRequest.setSenderFullName("Habte Tsegaye");
            newRequest = requestRepo.save(newRequest);

        List<Request> allRequests = requestHandlingService.getAllRequests();
        assertThat(allRequests.size()).isEqualTo(1);

    }
}