package pl.akademiakodu.miniblog.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.akademiakodu.miniblog.model.entities.Tag;

public interface TagRepository extends JpaRepository<Tag, Long> {
}
