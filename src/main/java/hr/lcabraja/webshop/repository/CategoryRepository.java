package hr.lcabraja.webshop.repository;

import hr.lcabraja.webshop.model.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoryRepository {
    private static CategoryRepository singleton = null;
    private List<Category> categories = new ArrayList<>();

    private CategoryRepository() {
        categories.add(new Category(1L, "Bread"));
        categories.add(new Category(2L, "Pastry"));
        categories.add(new Category(3L, "Fine pastry and sweet rolls"));
        categories.add(new Category(4L, "Burek"));
        categories.add(new Category(5L, "Sandwiches"));
        categories.add(new Category(6L, "Pizza"));
        categories.add(new Category(7L, "Salads and yogurts"));
        categories.add(new Category(8L, "Packaged products"));
    }

    public static List<Category> getAllCategories() {
        if (singleton == null) {
            singleton = new CategoryRepository();
            return getAllCategories();
        }
        return singleton.categories;
    }
}
