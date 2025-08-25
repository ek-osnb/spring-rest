package ek.osnb.courseapi.controller;

import ek.osnb.courseapi.model.Course;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private final List<Course> courses = new ArrayList<>();
    private Long nextId = 1L;

    public CourseController() {
        // TODO Add Some sample courses to the list

    }

    @GetMapping
    public ResponseEntity<List<Course>> getAllCourses(@RequestParam(required = false) String name) {
        if (name != null && !name.isEmpty()) {
            List<Course> filteredCourses = new ArrayList<>();
            // TODO Filter courses by name if the name parameter is provided

        }
        return ResponseEntity.ok(courses);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable Long id) {
        // TODO Find the course by id and return it

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @PostMapping
    public ResponseEntity<Course> addCourse(@RequestBody Course course) {
        // Simple ID generation (for demo purposes only)
        course.setId(nextId++);
        // TODO Add the course to the list and return it

        return ResponseEntity.status(HttpStatus.CREATED).body(course);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Course> updateCourse(@PathVariable Long id, @RequestBody Course course) {
        // TODO Update the course in the list and return it

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
        // TODO Delete the course from the list
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

}
