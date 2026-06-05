package co.istad.productapi.rescontroller.repository;

import co.istad.productapi.entity.Category;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

@Repository
public class CategoryRepository {

    private final List<Category> categoryList = new ArrayList<>(){{
        add(new Category(
                1001,
                "Spring Framework",
                "spring-framework",
                "Spring Boot and Spring ecosystem",
                "https://cdn.jsdelivr.net/gh/devicons/devicon/icons/spring/spring-original.svg",
                false
        ));

        add(new Category(
                1002,
                "Java",
                "java",
                "Java programming language",
                "https://cdn.jsdelivr.net/gh/devicons/devicon/icons/java/java-original.svg",
                false
        ));

        add(new Category(
                1003,
                "React",
                "react",
                "React frontend development",
                "https://cdn.jsdelivr.net/gh/devicons/devicon/icons/react/react-original.svg",
                false
        ));

        add(new Category(
                1004,
                "Node.js",
                "nodejs",
                "Node.js backend development",
                "https://cdn.jsdelivr.net/gh/devicons/devicon/icons/nodejs/nodejs-original.svg",
                false
        ));

        add(new Category(
                1005,
                "Docker",
                "docker",
                "Containerization with Docker",
                "https://cdn.jsdelivr.net/gh/devicons/devicon/icons/docker/docker-original.svg",
                false
        ));

        add(new Category(
                1006,
                "Kubernetes",
                "kubernetes",
                "Container orchestration",
                "https://cdn.jsdelivr.net/gh/devicons/devicon/icons/kubernetes/kubernetes-plain.svg",
                false
        ));

        add(new Category(
                1007,
                "PostgreSQL",
                "postgresql",
                "PostgreSQL database",
                "https://cdn.jsdelivr.net/gh/devicons/devicon/icons/postgresql/postgresql-original.svg",
                false
        ));

        add(new Category(
                1008,
                "MySQL",
                "mysql",
                "MySQL database",
                "https://cdn.jsdelivr.net/gh/devicons/devicon/icons/mysql/mysql-original.svg",
                false
        ));

        add(new Category(
                1009,
                "MongoDB",
                "mongodb",
                "MongoDB NoSQL database",
                "https://cdn.jsdelivr.net/gh/devicons/devicon/icons/mongodb/mongodb-original.svg",
                false
        ));

        add(new Category(
                1010,
                "Git",
                "git",
                "Version control with Git",
                "https://cdn.jsdelivr.net/gh/devicons/devicon/icons/git/git-original.svg",
                false
        ));
    }};

    public List<Category> getAllCategories () {
        return categoryList;
    }

    public Category createCategory(Category category) {
        categoryList.add(category);
        return category;
    }

    public Category findCategoryById(Integer id) {
        return categoryList.stream()
                .filter(category -> Objects.equals(category.getId(), id))
                .findFirst()
                .orElseThrow(
                        () -> new NoSuchElementException("Category with ID: " + id + " Not Found")
                );
    }

    public Category updateCategory(Category updateCategory) {
        for (int i = 0; i < categoryList.size(); i++) {
            var category = categoryList.get(i);
            if (Objects.equals(category.getId(), updateCategory.getId())) {
                categoryList.set(i, updateCategory);
                return updateCategory;
            }
        }

        return null;
    }

    public boolean deleteCategoryById(Integer id) {
        var category = findCategoryById(id);
        category.setIsDeleted(true);
        return true;
    }
}
