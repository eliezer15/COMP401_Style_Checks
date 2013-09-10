package logging;

public class TestLog {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Log log = new Log();
		System.out.println(log.getProjectDirectory());
		
		log.closeStream();

	}

}
