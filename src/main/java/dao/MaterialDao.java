package dao;

import models.Material;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class MaterialDao {
    public void save(int newId, int quantity) {
        String sql = "insert into materials(categoryid, remain) values(?, ?)";
        GeneralDao.executeAllTypeUpdate(sql, newId, quantity);
    }

    public List<Material> getIdByCategoryId(int id) {
        String sql = "select id from materials where categoryId = ?";
        return GeneralDao.executeQueryWithSingleTable(sql, Material.class, id);
    }

    public void updateQuantity(int categoryId, int quantity) {
        String sql = "update materials set remain = remain + ? where categoryId = ?";
        GeneralDao.executeAllTypeUpdate(sql, quantity, categoryId);
    }

    public List<Map<String, Object>> findPerPage(int perPage, int offset, String searchValue, String columns, String orderBy) {
        String sql = "select m.id as id, c.name as name, m.remain as remain, m.createdAt as createdAt from materials m join categories_material c on m.categoryId = c.id where m.id like ? or c.name like ? or m.remain like ? or m.createdAt like ? order by ? ? limit ? offset ?";
        List<Map<String, Object>> materials = GeneralDao.executeQueryWithJoinTables(sql, "%" + searchValue + "%", "%" + searchValue + "%", "%" + searchValue + "%", "%" + searchValue + "%", columns, orderBy, perPage, offset);

        if (orderBy.equals("desc")) {
            Collections.reverse(materials);
        }

        return materials;
    }

    public long countAll() {
        String sql = "SELECT COUNT(*) count FROM materials";

        CountResult result = new CountResult();
        GeneralDao.customExecute(handle -> {
            result.setCount(handle.createQuery(sql)
                    .mapToBean(CountResult.class)
                    .list().get(0).getCount());
        });
        return result.getCount();
    }

    public static class CountResult {
        private long count;

        public CountResult() {
        }

        public CountResult(int count) {
            this.count = count;
        }

        public long getCount() {
            return count;
        }

        public void setCount(long count) {
            this.count = count;
        }
    }
}
