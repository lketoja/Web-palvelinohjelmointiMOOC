/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package weatherservice;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 *
 * @author Lotta
 */
@Service
public class LocationService {
    
    @Autowired
    private LocationRepository locationRepo;
    
    @Cacheable(cacheNames="all-locations")
    public List<Location> readAll(){
        return locationRepo.findAll();
    }

    @Cacheable(cacheNames="find-one")
    public Location readOne(Long id){
        return locationRepo.getOne(id);
    }
    
    @CacheEvict(allEntries=true)
    public Location saveLocation(Location location){
        return locationRepo.save(location);
    }
    
    @CacheEvict(allEntries=true)
    public void flush(){
        
    }


}
