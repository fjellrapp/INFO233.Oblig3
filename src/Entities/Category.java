package Entities;

public class Category {


    /**
     * Entitetsbønne for Category. Representerer tabellen Category i databasen.
     */
    private int categoryId;
    private String categoryName;

    /**
     * Getter for category
     *
     * @return Integer eller string
     */

    public Integer getCategoryId() {
        return categoryId;
    }

    /**
     * Mutator for category-bønnen
     *
     * @param categoryId
     */

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
