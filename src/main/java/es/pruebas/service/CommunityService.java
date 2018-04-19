package es.pruebas.service;

import es.pruebas.model.Community;
import java.util.List;

public interface CommunityService {

    Community findById(long id);

    Community findByName(String name);

    void saveCommunity(Community community);

    void updateCommunity(Community community);

    void deleteCommunityById(long id);

    List<Community> findAllComunities();

    void deleteAllComunities();

    boolean isCommunityExist(Community community);
}
