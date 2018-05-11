package es.pruebas.controllers;

import es.pruebas.models.Community;
import es.pruebas.repositories.CommunityRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CommunityController {

    private static final Logger logger = LoggerFactory.getLogger(CommunityController.class);

    @Autowired
    private CommunityRepository communityRepository;

    @GetMapping("/communities")
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Community> getAllCommunities() {
        logger.debug("Calling retrieve all communities endpoint");
        return  communityRepository.findAll();
    }

    @GetMapping("/communities/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Community getCommunityById(@PathVariable("id") int id){
        logger.debug("Calling to get one community {}", id);
        return communityRepository.findById(id).orElse(null);
    }

    @PostMapping("/communities")
    @ResponseStatus(HttpStatus.CREATED)
    public Community createCommunity(@RequestBody Community communityDetails) {
        logger.debug("community  with id {}", communityDetails.getId() + " updated");

        return  communityRepository.save(communityDetails);

    }

    @PutMapping("/communities/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Community updateCommunity(@PathVariable("id") int communityId,
                                  @RequestBody Community communityDetails) {

        Community community = communityRepository.findById(communityId).orElse(null);
        community.setName(communityDetails.getName());
        community.setDescription(communityDetails.getDescription());
        return communityRepository.save(community);

    }

    @DeleteMapping("/communities/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteCommunity(@PathVariable("id") int id){

        communityRepository.deleteById(id);
        return "Community succesfully deleted with id = " + id;

    }

}
