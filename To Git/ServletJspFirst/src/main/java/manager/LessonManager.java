package manager;


import db.DBConnectionProvider;
import model.Gender;
import model.Lesson;
import model.User;
import model.UserType;
import util.DateUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LessonManager {

    private Connection connection = DBConnectionProvider.getInstance().getConnection();

    public boolean addLesson(Lesson lesson) {
        String sql = "INSERT INTO lesson(name,duration,price,) VALUES(?,?,?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, lesson.getName());
            statement.setDouble(2, lesson.getDuration());
            statement.setDouble(3, lesson.getPrice());
            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                lesson.setId(rs.getLong(1));
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Lesson getLessonByID(long id) {
        String sql = "SELECT * FROM lesson WHERE id=" + id;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            if (resultSet.next()) {
                return getLessonFromResultSet(resultSet);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Lesson getLessonByEmailAndPassword(String email, String password) {
        String sql = "SELECT * FROM lesson WHERE email = ? and password = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, email);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return getLessonFromResultSet(resultSet);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Lesson getByEmail(String email) {
        String sql = "SELECT * FROM lesson WHERE email = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return getLessonFromResultSet(resultSet);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Lesson> getAllLessons() {
        List<Lesson> lessons = new ArrayList<Lesson>();
        String sql = "SELECT * FROM lesson";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                lessons.add(getLessonFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lessons;
    }

    private Lesson getLessonFromResultSet(ResultSet resultSet) {
        try {
            return Lesson.builder()
                    .id(resultSet.getLong(1))
                    .name(resultSet.getString(2))
                    .duration(resultSet.getDouble(3))
                    .price(resultSet.getDouble(4))
                    .build();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void removeLessonById(int id) {
        String query = "DELETE FROM lesson WHERE id = " + id;
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
