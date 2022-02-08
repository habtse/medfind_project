package com.gis.medfind.RepositoryTests;

import static org.assertj.core.api.Assertions.assertThat;

import com.gis.medfind.entity.FileInfo;


import com.gis.medfind.entity.Request;
import com.gis.medfind.repository.FileInfoRepository;
import com.gis.medfind.repository.RequestRepository;

import org.junit.jupiter.api.Test;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;
 
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class RequestRepositoryTest {
 
    @Autowired
    private TestEntityManager entityManager;

    private GeometryFactory geometryFactory = new GeometryFactory();
    
    @Autowired
    private FileInfoRepository fileRepo;
    
    @Autowired
    private RequestRepository requestRepo;
     
    @Test
    public void testCreateRequest() {
        Request rq = new Request();
            rq.setCreatedDate("12-05-2021");
            rq.setEmail("amhaznif@gmail.com");    
            rq.setLocation(new Point(null, geometryFactory));
                FileInfo lse = new FileInfo();
                    lse.setName("kenema_license");
                    lse.setUrl("uploads/license/kenema/");
                FileInfo license = fileRepo.save(lse);
            rq.setLicenseFile(license);
        Request saved_rq = requestRepo.save(rq);

        Request exist_rq = entityManager.find(Request.class, rq.getId());
        assertThat(exist_rq.getCreatedDate()).isEqualTo(saved_rq.getCreatedDate());        
    }
}