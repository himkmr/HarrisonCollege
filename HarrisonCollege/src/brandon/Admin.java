package brandon;

import java.util.List;

import javax.persistence.TypedQuery;

import customTools.DBUtil;

public class Admin {

	// create, update, list or disable a course
	public static void createCourse(long id, model.Hdepartment deparment,
			String subject, int hours) {
		model.Hcours course = new model.Hcours();
		course.setCourseId(id);
		course.setHdepartment(deparment);
		course.setSubject(subject);
		course.setCreditHours(hours);
		course.setEnabled("yes");
		DBUtil.addToDB(course);
	}

	// update credit hours for course
	public static void updateCourse(model.Hcours course, int hours) {
		String qString = "update Hcours h set h.creditHours =:hours where courseId =:id";
		TypedQuery<model.Hcours> uQuery = DBUtil
				.createQuery(qString, model.Hcours.class)
				.setParameter("hours", hours)
				.setParameter("id", course.getCourseId());
		DBUtil.updateDB(uQuery);
	}

	// update subject for course
	public static void updateCourse(model.Hcours course, String subject) {
		String qString = "update Hcours h set h.subject =:subject where courseId =:id";
		TypedQuery<model.Hcours> uQuery = DBUtil
				.createQuery(qString, model.Hcours.class)
				.setParameter("subject", subject)
				.setParameter("id", course.getCourseId());
		DBUtil.updateDB(uQuery);

	}

	// update department for course
	public static void updateCourse(model.Hcours course,
			model.Hdepartment department) {
		String qString = "update Hcours h set h.hdepartment =:department where courseId =:id";
		TypedQuery<model.Hcours> uQuery = DBUtil
				.createQuery(qString, model.Hcours.class)
				.setParameter("department", department)
				.setParameter("id", course.getCourseId());
		DBUtil.updateDB(uQuery);
	}

	public static void listCourse() {
		//TODO
	}

	// disable course
	public static void disableCourse(model.Hcours course) {
		String qString = "update Hcours h set h.enabled = \'no\' where courseId =:id";
		TypedQuery<model.Hcours> uQuery = DBUtil.createQuery(qString,
				model.Hcours.class).setParameter("id", course.getCourseId());
		DBUtil.updateDB(uQuery);
	}

	// create a classroom
	public static void createClassroom(long id, String building, int cap,
			int roomNum) {
		model.Hclassroom classroom = new model.Hclassroom();
		classroom.setClassroomId(id);
		classroom.setBuilding(building);
		classroom.setCapacity(cap);
		classroom.setRoomNumber(roomNum);
		DBUtil.addToDB(classroom);
	}

	// update capacity or roomNumber for classroom
	public static void updateClassroom(model.Hclassroom classroom, String capOrRoom, int cap) {
		String param;
		if(capOrRoom.equalsIgnoreCase("capacity")){
			param="capacity";
		}
		else if(capOrRoom.equalsIgnoreCase("roomNumber")){
			param="roomNumber";
		}
		else {
			System.err.println("Error, String must be either capacity or roomNumber");
			return;
		}
		
		String qString = "update Hclassroom h set h."+param+" =:param where courseId =:id";
		TypedQuery<model.Hclassroom> uQuery = DBUtil
				.createQuery(qString, model.Hclassroom.class)
				.setParameter("param", param)
				.setParameter("id", classroom.getClassroomId());
		DBUtil.updateDB(uQuery);
	}

	// update building for classroom
	public static void updateClassroom(model.Hclassroom classroom,
			String building) {
		String qString = "update Hclassroom h set h.building =:building where courseId =:id";
		TypedQuery<model.Hclassroom> uQuery = DBUtil
				.createQuery(qString, model.Hclassroom.class)
				.setParameter("building", building)
				.setParameter("id", classroom.getClassroomId());
		DBUtil.updateDB(uQuery);

	}

	// disable classroom
		public static void disableClassroom(model.Hclassroom classroom) {
			String qString = "update Hclassroom h set h.enabled = \'no\' where courseId =:id";
			TypedQuery<model.Hclassroom> uQuery = DBUtil.createQuery(qString,
					model.Hclassroom.class).setParameter("id",classroom.getClassroomId());
			DBUtil.updateDB(uQuery);
		}
	

	public static void listClassroom() {
		//TODO
	}


	// create, update, list or disable a department
	public static void modifyDepartment() {
		
	}

	// create a major
	public static void createMajor(long id, model.Hdepartment department) {
		model.Hmajor major = new model.Hmajor();
		
	}

	// add class to schedule for current or later semester
	public static void addToSchedule() {
	}

	// remove class from schedule for current or later semester
	public static void removeFromSchedule() {
	}

	// change a new users type to(student, instructor advisor or administrator)
	public static void changeUserType() {
	}

	// override maximum enrollment hold
	public static void overrideEnrollment() {
	}

	// view a list of all students taught by an instructor
/*	public List<model.Hstudent> studTaughtByInstr() {
	}

	// view a list of all instructors that have taught a class
	public List<model.Hofficial> instrTaughtClass() {
	}

	// view a list of all classes of a course
	public List<model.Hclass> classesByCourse() {
	}

	// view a list of all classrooms used by a course
	public List<model.Hclassroom> classroomsByCourse() {
	}

	// view a list of all classrooms used by an instructor
	public List<model.Hclassroom> classroomsByInstr() {
	}

	// view a list of all classrooms used by a student
	public List<model.Hclassroom> classroomsByStudent() {
	}
*/
}
