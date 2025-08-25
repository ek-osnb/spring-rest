package ek.osnb.courseapi.service;

import ek.osnb.courseapi.model.Course;
import ek.osnb.courseapi.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {
    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    // Add service methods here
    public List<Course> getAllCourses(String name) {
        // TODO: Implement filtering by name
        if (name != null && !name.isEmpty()) {
            return courseRepository.findByNameContainingIgnoreCase(name);
        }
        return courseRepository.findAll();
    }

    public Course getCourseById(Long id) {
        Optional<Course> course = courseRepository.findById(id);
        if (course.isPresent()) {
            return course.get();
        }
        throw new RuntimeException("Course not found with id: " + id);
    }

    public Course createCourse(Course course) {
        course.setId(null); // Ensure the ID is null for new entities
        return courseRepository.save(course);
    }

    public Course updateCourse(Long id, Course updatedCourse) {
        Course course = this.getCourseById(id);
        course.setName(updatedCourse.getName());
        course.setDescription(updatedCourse.getDescription());
        course.setActive(updatedCourse.isActive());
        course.setStartDate(updatedCourse.getStartDate());
        return courseRepository.save(course);
    }

    public void deleteCourse(Long id) {
        Course course = this.getCourseById(id);
        if (course == null) {
            throw new RuntimeException("Course not found with id: " + id);
        }
        courseRepository.delete(course);
    }
}
