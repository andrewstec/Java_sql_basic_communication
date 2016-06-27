package a00123456.assignment.data;

public class StudentMarks {
	
	private double quizzes;
	private double assignments;
	private double exams;
	
	public StudentMarks() {
		super();
		
		this.quizzes = 0.0;
		this.assignments = 0.0;
		this.exams = 0.0;
		
	}
	
	public StudentMarks(double quizzes, double assignments, double exams){
		
		this.quizzes = quizzes;
		this.assignments = assignments;
		this.exams = exams;
	}
	
	public StudentMarks(double[] data){
		
		this.quizzes = data[0];
		this.assignments = data[1];
		this.exams = data[2];
	}

	/**
	 * @return the quizzes
	 */
	public double getQuizzes() {
		return quizzes;
	}

	/**
	 * @param quizzes the quizzes to set
	 */
	public void setQuizzes(double quizzes) {
		this.quizzes = quizzes;
	}

	/**
	 * @return the assignments
	 */
	public double getAssignments() {
		return assignments;
	}

	/**
	 * @param assignments the assignments to set
	 */
	public void setAssignments(double assignments) {
		this.assignments = assignments;
	}

	/**
	 * @return the exams
	 */
	public double getExams() {
		return exams;
	}

	/**
	 * @param exams the exams to set
	 */
	public void setExams(double exams) {
		this.exams = exams;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "StudentMarks [quizzes=" + quizzes + ", assignments="
				+ assignments + ", exams=" + exams + "]";
	}

}
