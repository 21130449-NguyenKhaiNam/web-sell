import dao.GeneralDao;
import dao.UserDAO;
import dao.UserDAOImplement;

import database.DBProperties;
import database.JDBIConnector;
import models.User;
import services.ReviewServices;
import utils.ProductFactory;

import java.util.ArrayList;
import java.util.List;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestSelect {
    public static void main(String[] args) {
        System.out.println(ProductFactory.getListSizesByProductId(3));
        System.out.println(ReviewServices.getINSTANCE().getListReview(43));
    }
}
