package es.pruebas.repositories;

import es.pruebas.models.Community;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommunityRepository extends CrudRepository<Community, Integer> {

    List<Community> findByName(String name);

}
