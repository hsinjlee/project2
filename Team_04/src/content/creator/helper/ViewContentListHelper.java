package content.creator.helper;

import content.creator.dao.QuizContentDAO;
import content.creator.operations.DataOps;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import student.dto.QuizContent;

/** @author Hari Krishnan Puthiya Veetil */
public final class ViewContentListHelper {

    private ViewContentListHelper() {
    }

    public static List<Integer> getQuizList() throws SQLException {
        String tableName = "quiz_content";
        String colName = "quizId";
        List<Integer> list = new ArrayList<>();
        String queryString = getQueryString(tableName, colName);
        List<QuizContentDAO> data = DataOps.getData(queryString);
        for (QuizContentDAO content: data) {
            list.add(content.getQuizId());
        }
        return list;
    }

    private static String getQueryString(String tableName, String colName) {
        return String.format("SELECT DISTINCT %s FROM %s", colName, tableName);
    }
}
