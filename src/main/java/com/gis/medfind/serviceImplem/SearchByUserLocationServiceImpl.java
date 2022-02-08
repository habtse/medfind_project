package com.gis.medfind.serviceImplem;

import com.gis.medfind.service.SearchByUserLocationService;

import java.util.ArrayList;
import java.util.List;
import com.gis.medfind.entity.Pharmacy;
import com.gis.medfind.repository.PharmacyRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class SearchByUserLocationServiceImpl implements SearchByUserLocationService {

    @Autowired
    PharmacyRepository pharmRepo;

    public List<Pharmacy> findPharmaciesByUserLocation(String medicineName, Double userLon, Double userLat){
        List<Pharmacy> pharmsCloseToUser = pharmRepo.findAllPharmaciesByDistanceFromUser(userLon, userLat);
        List<Pharmacy> pharmsCloseToUserWithMedicine = new ArrayList<>();
        for(Pharmacy pharm: pharmsCloseToUser){
            if(pharm.checkMedicine(medicineName)){
                pharmsCloseToUserWithMedicine.add(pharm);
            }
        }
        return pharmsCloseToUser;
    }

}
