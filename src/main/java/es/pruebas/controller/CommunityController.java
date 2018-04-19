package es.pruebas.controller;

import es.pruebas.model.Community;
import es.pruebas.service.CommunityService;
import es.pruebas.utility.CustomErrorType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class CommunityController {

    @Autowired
    CommunityService communityService;

    // -------------------Retrieve All Comunities---------------------------------------------

    @RequestMapping(value = "/community", method = RequestMethod.GET)
    public ResponseEntity<List<Community>> listAllComunities() {
        List<Community> communities = communityService.findAllComunities();
        if (communities.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Community>>(communities, HttpStatus.OK);
    }

    // -------------------Retrieve Single Community------------------------------------------

    @RequestMapping(value = "/community/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getCommunity(@PathVariable("id") long id) {
        Community community = communityService.findById(id);
        if (community == null) {
            return new ResponseEntity<>(new CustomErrorType("Community with id " + id
                    + " not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Community>(community, HttpStatus.OK);
    }

    // -------------------Create a Community-------------------------------------------

    @RequestMapping(value = "/community", method = RequestMethod.POST)
    public ResponseEntity<?> createCommunity(@RequestBody Community community, UriComponentsBuilder ucBuilder) {

        if (communityService.isCommunityExist(community)) {
            return new ResponseEntity<>(new CustomErrorType("Unable to create. A community with name " +
                    community.getName() + " already exist."),HttpStatus.CONFLICT);
        }
        communityService.saveCommunity(community);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/community/{id}").buildAndExpand(community.getId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    // ------------------- Update a Community ------------------------------------------------

    @RequestMapping(value = "/community/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateUser(@PathVariable("id") long id, @RequestBody Community community) {
        Community currentCommunity = communityService.findById(id);

        if (currentCommunity == null) {

            return new ResponseEntity<>(new CustomErrorType("Unable to upate. community with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }

        currentCommunity.setName(community.getName());
        currentCommunity.setDescription(community.getDescription());

        communityService.updateCommunity(currentCommunity);
        return new ResponseEntity<Community>(currentCommunity, HttpStatus.OK);
    }

    // ------------------- Delete a Community-----------------------------------------

    @RequestMapping(value = "/community/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteCommunity(@PathVariable("id") long id) {

        Community community = communityService.findById(id);
        if (community == null) {
            return new ResponseEntity<>(new CustomErrorType("Unable to delete. Community with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        communityService.deleteCommunityById(id);
        return new ResponseEntity<Community>(HttpStatus.NO_CONTENT);
    }

    // ------------------- Delete All Comunities-----------------------------

    @RequestMapping(value = "/community/", method = RequestMethod.DELETE)
    public ResponseEntity<Community> deleteAllComunities() {

        communityService.deleteAllComunities();
        return new ResponseEntity<Community>(HttpStatus.NO_CONTENT);
    }

}
