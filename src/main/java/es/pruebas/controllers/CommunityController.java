package es.pruebas.controllers;

import es.pruebas.exceptions.ResourceNotFoundException;
import es.pruebas.models.Community;
import es.pruebas.repositories.CommunityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CommunityController {

    @Autowired
    private CommunityRepository communityRepository;

    @GetMapping("/community")
    public List<Community> getAllCommunities() {
        return communityRepository.findAll();
    }

    @GetMapping("/community/{id}")
    public Optional<Community> getCommunityById(@PathVariable String id){
        int blogId = Integer.parseInt(id);
        return communityRepository.findById(blogId);
    }

    @PostMapping("/community")
    public Community createCommunity(@Valid @RequestBody Map<String, String> body) {
        String name = body.get("name");
        String description = body.get("description");
        return communityRepository.save(new Community(name, description));
    }

    @PutMapping("/community/{id}")
    public Community updateCommunity(@PathVariable(value = "id") int communityId,
                           @Valid @RequestBody Community communityDetails) {

        Community community = communityRepository.findById(communityId)
                .orElseThrow(() -> new ResourceNotFoundException("Community", "id", communityId));

        community.setName(communityDetails.getName());
        community.setDescription(communityDetails.getDescription());

        Community updatedCommunity = communityRepository.save(community);
        return updatedCommunity;
    }

    @DeleteMapping("community/{id}")
    public void deleteCommunity(@PathVariable String id){
        int blogId = Integer.parseInt(id);
        communityRepository.deleteById(blogId);

    }

}
