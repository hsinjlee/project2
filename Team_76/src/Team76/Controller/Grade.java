package Team76.Controller;

public class Grade {

	private int studentId;
	private int quizId;
	private String quizTitle;
	private String studentName;
	private String grade;
	
	public Grade(int studentId, int quizId, String quizTitle, String studentName, String grade) {
		
		this.studentId = studentId;
		this.quizId = quizId;
		this.quizTitle = quizTitle;
		this.studentName = studentName;
		this.grade = grade;
	}

	public Grade(int quizId, String quizTitle, String studentName, String grade) {
		
		this.quizId = quizId;
		this.quizTitle = quizTitle;
		this.studentName = studentName;
		this.grade = grade;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public int getQuizId() {
		return quizId;
	}

	public void setQuizId(int quizId) {
		this.quizId = quizId;
	}

	public String getQuizTitle() {
		return quizTitle;
	}

	public void setQuizTitle(String quizTitle) {
		this.quizTitle = quizTitle;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	@Override
	public String toString() {
		return "Grade [studentId=" + studentId + ", quizId=" + quizId 
				+ ", quizTitle=" + quizTitle + ", studentName="
				+ studentName + ", grade=" + grade + "]";
	}	
}
