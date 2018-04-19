package es.pruebas.service.impl;

import es.pruebas.model.Community;
import es.pruebas.service.CommunityService;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service("communityService")
public class CommunityServiceImpl implements CommunityService {

    private static final AtomicLong counter = new AtomicLong();

    private static List<Community> communities;

    static{
        communities= populateComunities();
    }

    public List<Community> findAllComunities() {
        return communities;
    }

    public Community findById(long id) {
        for(Community community : communities){
            if(community.getId() == id){
                return community;
            }
        }
        return null;
    }

    public Community findByName(String name) {
        for(Community community : communities){
            if(community.getName().equalsIgnoreCase(name)){
                return community;
            }
        }
        return null;
    }

    public void saveCommunity(Community community) {
        community.setId(counter.incrementAndGet());
        communities.add(community);
    }

    public void updateCommunity(Community community) {
        int index = communities.indexOf(community);
        communities.set(index, community);
    }

    public void deleteCommunityById(long id) {
        communities.removeIf(community -> community.getId() == id);
    }

    public void deleteAllComunities(){
        communities.clear();
    }

    public boolean isCommunityExist(Community community) {
        return findByName(community.getName())!=null;
    }

    private static List<Community> populateComunities(){
        List<Community> communities = new ArrayList<Community>();
        communities.add(new Community(counter.incrementAndGet(),"QA","Testing"));
        communities.add(new Community(counter.incrementAndGet(),"Devops","CI"));
        communities.add(new Community(counter.incrementAndGet(),"Frontend","UI"));
        return communities;
    }
}
