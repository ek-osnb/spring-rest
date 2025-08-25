package ek.osnb.courseapi.controller;

import ek.osnb.courseapi.model.Course;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private final List<Course> courses = new ArrayList<>();
    private Long nextId = 1L;

    public CourseController() {
        // TODO Add Some sample courses to the list
        LocalDate startDate = LocalDate.of(2025, 8, 25);
        courses.add(new Course(nextId++, "Java Basics", "Learn the basics of Java programming", true, startDate, startDate.plusMonths(5).plusDays(3)));
        courses.add(new Course(nextId++, "Spring Boot", "Build RESTful APIs with Spring Boot", true, startDate, startDate.plusMonths(5).plusDays(10)));
        courses.add(new Course(nextId++, "Docker Essentials", "Containerize applications with Docker", false, startDate, startDate.plusMonths(4).plusDays(3)));
        courses.add(new Course(nextId++, "Kubernetes Fundamentals", "Orchestrate containers with Kubernetes", true, startDate, startDate.plusMonths(4)));
    }

    @GetMapping
    public ResponseEntity<List<Course>> getAllCourses(@RequestParam(required = false) String name) {
        if (name != null && !name.isEmpty()) {
            List<Course> filteredCourses = new ArrayList<>();
            // TODO Filter courses by name if the name parameter is provided
            for (var c : courses) {
                if (c.getName().toLowerCase().contains(name.toLowerCase())) {
                    filteredCourses.add(c);
                }
            }
            return ResponseEntity.ok(filteredCourses);
        }
        return ResponseEntity.ok(courses);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable Long id) {
        // TODO Find the course by id and return it
        for (var c : courses) {
            if (c.getId().equals(id)) {
                return ResponseEntity.ok(c);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @PostMapping
    public ResponseEntity<Course> addCourse(@RequestBody Course course) {
        // Simple ID generation (for demo purposes only)
        course.setId(nextId++);
        // TODO Add the course to the list and return it
        courses.add(course);
        return ResponseEntity.status(HttpStatus.CREATED).body(course);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Course> updateCourse(@PathVariable Long id, @RequestBody Course course) {
        // TODO Update the course in the list and return it
        for (var c : courses) {
            if (c.getId().equals(id)) {
                c.setName(course.getName());
                c.setDescription(course.getDescription());
                c.setActive(course.isActive());
                c.setStartDate(course.getStartDate());
                c.setEndDate(course.getEndDate());
                return ResponseEntity.ok(c);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
        // TODO Delete the course from the list
        for (var c : courses) {
            if (c.getId().equals(id)) {
                courses.remove(c);
                return ResponseEntity.noContent().build();
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

}
