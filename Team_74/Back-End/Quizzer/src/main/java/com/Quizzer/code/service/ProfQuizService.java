package com.Quizzer.code.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Quizzer.code.dao.QuestionRepo;
import com.Quizzer.code.dao.QuizRepo;
import com.Quizzer.code.exceptions.Prof_AddQuiz_Exception;
import com.Quizzer.code.exceptions.Prof_GetQuiz_Exception;
import com.Quizzer.code.model.db.Question;
import com.Quizzer.code.model.db.Quiz;
import com.Quizzer.code.model.response.ResponseQuizListVO;
import com.mongodb.MongoWriteException;

/**
 * This is the service layer for add quiz, get quizzes.
 * 
 * @author Kumar Prabhu Kalyan and Kirti Jha
 *
 */
@Service
public class ProfQuizService {

	@Autowired
	QuestionRepo questionrepo;

	@Autowired
	QuizRepo quizRepo;

	/**
	 * This method adds the quiz to the database and sends a appropriate response.
	 * 
	 * @param quiz
	 * @throws Prof_AddQuiz_Exception
	 */
	public void addQuiz(Quiz quiz) throws Prof_AddQuiz_Exception {

		if (quiz != null) {
			List<Question> listQuestion = quiz.getQuestions();
			try {
				quizRepo.insert(quiz);
				quiz.setTotalMarks(addQuestions(listQuestion, quiz.getId()));
				quizRepo.save(quiz);
			} catch (MongoWriteException e) {
				throw new Prof_AddQuiz_Exception("Error while writing the object to database." + e.getMessage());

			}

		} else {
			throw new Prof_AddQuiz_Exception("The quiz object is null");
		}

	}

	/**
	 * This method adds all the questions,calculates the total marks, in the quiz to
	 * the DB in Question table.
	 * 
	 * @param listQuestion
	 * @throws Prof_AddQuiz_Exception
	 */
	private int addQuestions(List<Question> listQuestion, String id) throws Prof_AddQuiz_Exception {
		int totalMarks = 0;
		if (listQuestion != null) {
			int iLengthQuestions = listQuestion.size();
			if (iLengthQuestions > 0) {
				for (Question question : listQuestion) {

					if (question.getMarks() == 0)
						throw new Prof_AddQuiz_Exception(
								"The mark for question with text: " + question.getQuestionText() + " is not entered");
					else
						totalMarks = totalMarks + question.getMarks();

					question.setQuizId(id);

					questionrepo.insert(question);

				}
			}
		} else {
			throw new Prof_AddQuiz_Exception(
					"No questions to add in the quiz," + " Please add questions before adding quiz");
		}
		return totalMarks;
	}

	/**
	 * This method returns the list of quizzes for the professor.
	 * 
	 * @return
	 * @throws Prof_GetQuiz_Exception
	 */
	public List<ResponseQuizListVO> getQuiz() throws Prof_GetQuiz_Exception {
		try {

			List<ResponseQuizListVO> response_list = new ArrayList<ResponseQuizListVO>();
			for (Quiz quiz : quizRepo.findAll()) {
				response_list.add(new ResponseQuizListVO(quiz.getId(), quiz.getName()));
			}

			return response_list;

		} catch (Exception e) {
			throw new Prof_GetQuiz_Exception(e.getMessage());
		}
	}

	/**
	 * This method returns the quiz data according to the input Id.
	 * 
	 * @param quizId
	 * @return
	 * @throws Prof_GetQuiz_Exception
	 */
	public Quiz getQuiz(String quizId) throws Prof_GetQuiz_Exception {
		if (quizId != null && !StringUtils.isEmpty(quizId)) {
			Optional<Quiz> quiz = quizRepo.findById(quizId);
			if (quiz != null)
				return quiz.get();
			else
				return null;

		}
		return null;
	}

}
