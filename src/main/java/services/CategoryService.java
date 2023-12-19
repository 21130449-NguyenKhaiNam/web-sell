package services;

import dao.CategoryDAO;

import java.util.ArrayList;
import java.util.List;

public class CategoryService {
    private static CategoryService INSTANCE;

    private CategoryService() {
    }

    public static CategoryService getINSTANCE() {
        if (INSTANCE == null)
            INSTANCE = new CategoryService();
        return INSTANCE;
    }

}
