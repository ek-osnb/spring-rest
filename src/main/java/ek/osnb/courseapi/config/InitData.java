package ek.osnb.courseapi.config;

import ek.osnb.courseapi.model.Course;
import ek.osnb.courseapi.repository.CourseRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class InitData implements CommandLineRunner {
    private final CourseRepository courseRepository;
    
    public InitData(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }
    
    @Override
    public void run(String... args) throws Exception {
        LocalDate startDate = LocalDate.of(2025, 8, 25);
        List<Course> courses = new ArrayList<>();

        courses.add(new Course(null, "Java Basics", "Learn the basics of Java programming", true, startDate, startDate.plusMonths(5).plusDays(3)));
        courses.add(new Course(null, "Spring Boot", "Build RESTful APIs with Spring Boot", true, startDate, startDate.plusMonths(5).plusDays(10)));
        courses.add(new Course(null, "Docker Essentials", "Containerize applications with Docker", false, startDate, startDate.plusMonths(4).plusDays(3)));
        courses.add(new Course(null, "Kubernetes Fundamentals", "Orchestrate containers with Kubernetes", true, startDate, startDate.plusMonths(4)));

        courseRepository.saveAll(courses);
    }
}
