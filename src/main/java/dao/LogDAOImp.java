package dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import models.Log;
import services.LogService;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class LogDAOImp implements ILogDAO {
    private String ip;

    public LogDAOImp() {
    }

    public static void main(String[] args) {

        LogDAOImp dao = new LogDAOImp();

        // SELECT
        String typeSelect = "SELECT";
        // FROM
        String selectFORM = "SELECT abc, xyz FROM table WHERE abc...";
        System.out.println("SELECT FROM >> " + dao.getNameTable(new StringBuilder(selectFORM), typeSelect));
        // from
        String selectfrom = "SELECT abc, xyz from table WHERE abc...";
        System.out.println("SELECT from >> " + dao.getNameTable(new StringBuilder(selectfrom), typeSelect));
        // ()
        String select = "SELECT (abc, xyz) from table where abc...";
        System.out.println("SELECT () >> " + dao.getNameTable(new StringBuilder(select), typeSelect));

        // INSERT
        String typeInsert = "insert";
        // Khong co ()
        String isnertNot = "INSERT INTO table VALUES (1, 2, 3)";
        System.out.println("INSERT NOT () >> " + dao.getNameTable(new StringBuilder(isnertNot), typeInsert));
        // Co () o sat
        String insertNear = "INSERT INTO table(a, b, c) VALUES (1, 2, 3)";
        System.out.println("INSERT HAVE () NEAR >> " + dao.getNameTable(new StringBuilder(insertNear), typeInsert));
        // Co () o xa
        String insertSpaceNear = "INSERT INTO table (a, b, c) VALUES (1, 2, 3)";
        System.out.println("INSERT HAVE () SPACE NEAR >> " + dao.getNameTable(new StringBuilder(insertSpaceNear), typeInsert));

        // UPDATE
        String typeUpdate = "update";
        String update = "UPDATE table SET a = 1 WHERE b = 2";
        System.out.println("UPDATE >> " + dao.getNameTable(new StringBuilder(update), typeUpdate));

//        Khi muốn test thì mở dòng in tại đúng vị trí trong hàm tương ứng
//        String queryUpdate = "UPDATE test SET a = ? WHERE b = ?";
//        String queryInsert = "INSERT INTO test(a, b, c) VALUES (?, ?, ?)";
//        dao.insertLog(queryUpdate, 1, 2);
//        dao.insertLog(queryInsert, 1, 2, 3);

    }

    @Override
    public void setIp(String ip) {
        this.ip = ip;
    }

    // Lấy ra tên của bảng theo từng câu lệnh
    private String getNameTable(StringBuilder builder, String type) {
        type = type.toLowerCase(); // Chuyển type về chữ thường

        // Mỗi loại có một từ khóa nhất định
        String keyword;
        switch (type) {
            case "select":
                keyword = "FROM";
                break;
            case "insert":
                keyword = "INTO";
                break;
            case "update":
                keyword = "UPDATE";
                break;
            default:
                return "ERROR";
        }

        // Tìm từ khóa tương ứng trong chuỗi truy vấn
        int startIdx = indexOfKeyword(builder, keyword);
        if (startIdx == -1) {
            return "ERROR";
        }

        String symbol = " ";
        // Insert có 1 trường hợp đặt biệt về các dấu ()
        if (type.equals("insert")) {
            int indOpen = builder.indexOf("(", startIdx); // Tìm dấu ( từ vị trí đầu tiên của tên bảng
            int indValues = builder.indexOf("VALUES"); // Tìm vị trí từ khóa value để so sánh
            indValues = indValues == -1 ? builder.indexOf("values") : indValues;
            if (indOpen < indValues) {
                // Hàm insert này có chỉ định rõ ràng cột insert
                symbol = "(";
            }
        }

        // Vị trí kết thúc tên
        int endIdx = builder.indexOf(symbol, startIdx);

        // Dành cho các type khác
        // Trường hợp câu không có vế điều kiện, ...
        if (endIdx == -1) {
            endIdx = builder.length();
        }

        return builder.substring(startIdx, endIdx).trim(); // Lấy ra tên
    }

    // Phương thức để tìm vị trí đầu tiên của tên bảng
    private int indexOfKeyword(StringBuilder builder, String keyword) {
        int index = builder.indexOf(keyword);
        if (index == -1)
            index = builder.indexOf(keyword.toLowerCase());
        return index + keyword.length() + 1; // Bổ sung thêm độ dài của từ khóa và một dấu cách
    }

    // Sử dụng cho insert và update
    @Override
    public void insertLog(String sql, Object... params) {
        // Tránh vòng lặp vô tận
        if (!sql.contains("logs")) {
            StringBuilder builder = new StringBuilder(sql);
            String nameQuery = builder.substring(0, builder.indexOf(" ")).toLowerCase();
            String nameState = mapStateTypeQuery(nameQuery);
            String nameTable = getNameTable(builder, nameQuery);

            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());

            try {
                String sqlLog;
                switch (nameQuery) {
                    case "update" -> {
                        int indCondition = builder.indexOf("WHERE");
                        if (indCondition == -1)
                            indCondition = builder.indexOf("where");
                        if (indCondition == -1)
                            indCondition = builder.length();

                        // Chia tách 2 tập giá trị thành: giá trị dành cho SET và dành cho WHERE
                        int countEquals = builder.substring(0, indCondition).split("=").length - 1;
                        Object[] splitParam = Arrays.copyOfRange(params, countEquals, params.length);

                        // Các giá trị sẽ thay đổi
                        StringBuilder valueChanges = new StringBuilder(builder.substring(builder.indexOf("SET") + 3, indCondition));
                        for (int i = 0; i < countEquals; i++) {
                            int indEqual = valueChanges.indexOf("=");
                            int indAsk = valueChanges.indexOf("?");
                            valueChanges.replace(indEqual, indEqual + 1, ":");
                            valueChanges.replace(indAsk, indAsk + 1, params[i] + "");
                        }
                        Object[] changes = valueChanges.toString().split(",");
//                    System.out.println("Log UPDATE >> giá trị sẽ thay đổi:" + Arrays.toString(changes));

                        // Lấy ra giá trị trước khi thay đổi
                        String sqlPrevious = "SELECT * FROM " + nameTable + " " + builder.substring(indCondition);
//                    System.out.println("Log UPDATE >> Câu lệnh lấy giá trị trước khi thay đổi: " + sqlPrevious);
                        List<?> list = GeneralDao.executeQueryWithJoinTables(sqlPrevious, splitParam);
                        // Sau dòng này do bảng test không tồn tại nên báo lỗi

                        sqlLog = "INSERT INTO logs (ip, level, resource, dateCreated, previous, current) VALUES (?, ?, ?, ?, ?, ?)";
                        GeneralDao.executeAllTypeUpdate(sqlLog, ip, nameState, nameTable, Date.valueOf(LocalDate.now()), mapper.writeValueAsString(list.toArray()), mapper.writeValueAsString(changes));
                    }
                    case "insert" -> {
                        sqlLog = "INSERT INTO logs (ip, level, resource, dateCreated, current) VALUES (?, ?, ?, ?, ?)";

                        // Lấy ra các tham số sẽ insert của câu lệnh
                        String paramaters = builder.substring(builder.indexOf(nameTable) + nameTable.length(), builder.indexOf("VALUES"));
                        StringBuilder parameter = new StringBuilder(paramaters);

                        // Nếu câu lệnh được chỉ định có tham số mới được phép ghi log
                        if (parameter.length() > 2) {
                            // Loại bỏ dấu (
                            parameter.deleteCharAt(0);
                            // Loại bỏ dấu )
                            parameter.deleteCharAt(parameter.length() - 1);

                            // Phân tách riêng biệt các đối số
                            String[] paras = parameter.toString().split(",");
                            for (int i = 0; i < paras.length; i++) {
                                paras[i] = paras[i].trim() + ":" + params[i];
                            }
//                        System.out.println("Log INSERT >> Tham số hiện tại: " + Arrays.toString(paras));
                            GeneralDao.executeAllTypeUpdate(sqlLog, ip, nameState, nameTable, Date.valueOf(LocalDate.now()), mapper.writeValueAsString(paras));
                            break;
                        }
                        System.out.println("Log >> Phương thức insert của " + nameTable + " không được chỉ định rõ ràng tham số sẽ nhận hoặc có sai sót trong câu lệnh");
                    }
                    case "select" -> System.out.println("Log >> Hàm không hỗ trợ select");
                    default -> {
                        System.out.println("Log >> Câu lệnh chưa tồn tại hoặc chưa được hiện thực");
                    }
                }
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }
    }

    // Chỉ dành cho select
    @Override
    public void insertLogForSelect(String sql, List<?> list) {
        // Nếu trong câu select không trả về kết quả thì cũng không thêm vào log
        if (!list.isEmpty()) {
            StringBuilder builder = new StringBuilder(sql);
            String nameTable = getNameTable(builder, "select");
            String nameQuery = builder.substring(0, builder.indexOf(" "));
            String nameState = mapStateTypeQuery(nameQuery);

            String sqlLog = "INSERT INTO logs (ip, level, resource, dateCreated, current) VALUES (?, ?, ?, ?, ?)";
            ObjectMapper mapper = new ObjectMapper();
            try {
                GeneralDao.executeAllTypeUpdate(sqlLog, ip, nameState, nameTable, Date.valueOf(LocalDate.now()), mapper.writeValueAsString(list.toArray()));
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public List<Log> getLog(int start, int limit, String search, String orderBy, String orderDir) {
        String sql = "SELECT id, ip, level, resource, dateCreated, previous, current FROM logs " +
                "WHERE ip LIKE :search OR level LIKE :search OR resource LIKE :search " +
                "ORDER BY :orderBy :orderDir LIMIT :limit OFFSET :start";
        List<Log> logs = new ArrayList<>();
        GeneralDao.customExecute(handle -> {
            logs.addAll(handle.createQuery(sql)
                    .bind("search", "%" + search + "%")
                    .bind("limit", limit)
                    .bind("start", start)
                    .bind("orderBy", orderBy)
                    .bind("orderDir", orderDir)
                    .mapToBean(Log.class)
                    .list());
        });
        if(orderDir.equals("desc")) {
            Collections.reverse(logs);
        }
        return logs;
    }

    @Override
    public long getSize() {
        String sql = "SELECT COUNT(*) count FROM logs";
        return GeneralDao.executeQueryWithSingleTable(sql, CountResult.class).get(0).getCount();
    }

    @Override
    public long getSizeWithCondition(String search) {
        String sql = "SELECT COUNT(*) count FROM logs WHERE ip LIKE :search OR level LIKE :search OR resource LIKE :search";
        CountResult result = new CountResult();
        GeneralDao.customExecute(handle -> {
            result.setCount(handle.createQuery(sql)
                    .bind("search", "%" + search + "%")
                    .mapToBean(CountResult.class)
                    .list().get(0).getCount());
        });
        return result.getCount();
    }

    @Override
    public void save(Log log) {
        String sql = "INSERT logs (ip, level, resource, dateCreated, previous, current) VALUES (:ip, :level, :resource, :dateCreated, :previous, :current)";
        GeneralDao.customExecute(handle -> {
            handle.createUpdate(sql)
                    .bind("ip", log.getIp())
                    .bind("level", log.getLevel())
                    .bind("resource", log.getResource())
                    .bind("dateCreated", log.getDateCreated())
                    .bind("previous", log.getPrevious())
                    .bind("current", log.getCurrent())
                    .execute();
        });
    }

    @Override
    public void deleteAll() {
        String sql = "DELETE FROM logs WHERE 1=1";
        GeneralDao.customExecute((handle) -> {
            handle.execute(sql);
        });
    }

    @Override
    public List<Log> getLimit(int limit, int offset) {
        String sql = "select id, ip, level, resource, dateCreated, previous, current from logs limit ? offset ?";
        return GeneralDao.executeQueryWithSingleTable(sql, Log.class, limit, offset);
    }

    @Override
    public long getQuantity() {
        String sql = "SELECT COUNT(*) count FROM logs";
        CountResult result = new CountResult();
        GeneralDao.customExecute(handle -> {
            result.setCount(handle.createQuery(sql)
                    .mapToBean(CountResult.class)
                    .list().get(0).getCount());
        });
        return result.getCount();
    }



    // Chuyển đổi tương ứng với tác động của câu query, sau này tách ra
    private String mapStateTypeQuery(String query) {
        String state = query.toLowerCase();
        if (state.equals("select"))
            return "INFO";
        if (state.equals("update"))
            return "WARNING";
        if (state.equals("insert"))
            return "DANGEROUS";
        return "ERROR";
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
