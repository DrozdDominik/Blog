package pl.akademiakodu.miniblog.controllers.restcontrollers;


import org.springframework.data.jpa.repository.JpaRepository;

public interface ExampleRepository extends JpaRepository<Example, Long> {

}
