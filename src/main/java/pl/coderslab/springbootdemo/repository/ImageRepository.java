package pl.coderslab.springbootdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.springbootdemo.entity.Image;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
