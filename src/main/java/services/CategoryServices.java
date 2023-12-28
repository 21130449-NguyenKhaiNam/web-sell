package services;

public class CategoryServices {
    private static CategoryServices INSTANCE;

    private CategoryServices() {
    }

    public static CategoryServices getINSTANCE() {
        if (INSTANCE == null)
            INSTANCE = new CategoryServices();
        return INSTANCE;
    }

}
