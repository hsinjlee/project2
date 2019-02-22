package services;

import java.sql.Time;
import java.util.List;
import com.google.gson.Gson;

import bean.Answer;
import bean.AnswerMapper;
import bean.Question;
import bean.QuestionAnswer;
import bean.QuestionMapper;
import bean.Quiz;
import bean.ResponseStatistics;
import bean.User;
import dao.QuestionDAO;
import dao.StatisticsDAO;

/**
 * A class to get the JSON string to the student controller
 * 
 * @author amanjotsingh
 * @version 1.0
 * @since 02/21/2019
 * 
 */

public class StudentServices {

	public String getQuestionDetails(int quizId) {
		QuestionAnswerGenerator quizData = new QuestionAnswerGenerator();
		String jsonString = null;
		QuestionAnswer quizList = quizData.generator(quizId);
		jsonString = quizData.ObjectToJSON(quizList);
		return jsonString;
	}

	public String feedAnswers(String studentResponse) {

		QuestionAnswer jsonResponse = StudentServices.convertStringtoJSON(studentResponse);
		User user = new User("abc", "student", "abc.com", "1234");
		int quizId = jsonResponse.getQuizId();
		StatisticsDAO statisticsDAO = new StatisticsDAO();
		ResponseStatistics stats;
		List<QuestionMapper> questions = jsonResponse.getQuestion();
		Quiz quiz = new Quiz(quizId, jsonResponse.getQuizName(), jsonResponse.getQuizInstructions(),
				jsonResponse.getQuizType(), jsonResponse.getQuizTimeLimit(), jsonResponse.isShuffled(),
				jsonResponse.isPublished());
		for (QuestionMapper questionMapper : questions) {
			int questionId = questionMapper.getQuestionId();
			Question question = new Question(quiz, questionId, questionMapper.getQuestion(),
					questionMapper.getCorrectAnswerId(), questionMapper.isMultiple(), questionMapper.getPoints());
			List<AnswerMapper> answers = questionMapper.getResponseAnswer();
			if (answers != null) {
				for (AnswerMapper ansMapper : answers) {
					Answer answer = new Answer(question, ansMapper.getAnswerId(), ansMapper.getAnswer(),
							ansMapper.getCorrectAnswer());
					stats = new ResponseStatistics(user, quiz, question, answer);
					statisticsDAO.insertAnswer(answer);
					statisticsDAO.insertStudentResponse(stats);
				}
			}
		}

		return "/success";

	}

	public static QuestionAnswer convertStringtoJSON(String studentResponse) {
		Gson gson = new Gson();
		QuestionAnswer quizList = gson.fromJson(studentResponse, QuestionAnswer.class);
		return quizList;
	}
}
