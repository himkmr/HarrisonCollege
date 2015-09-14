package brandon;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import customTools.DBUtil;
import model.*;

public class Admin {

	// create, update, list or disable a course
	public static void createCourse(long id, Hdepartment deparment,
			String subject, int hours) {
		Hcours course = new Hcours();
		course.setCourseId(id);
		course.setHdepartment(deparment);
		course.setSubject(subject);
		course.setCreditHours(hours);
		course.setEnabled("yes");
		DBUtil.addToDB(course);
	}

	// update credit hours for course
	public static void updateCourse(Hcours course, int hours) {
		String qString = "update Hcours h set h.creditHours =:hours where courseId =:id";
		TypedQuery<Hcours> uQuery = DBUtil.createQuery(qString, Hcours.class)
				.setParameter("hours", hours)
				.setParameter("id", course.getCourseId());
		DBUtil.updateDB(uQuery);
	}

	// update subject for course
	public static void updateCourse(Hcours course, String subject) {
		String qString = "update Hcours h set h.subject =:subject where courseId =:id";
		TypedQuery<Hcours> uQuery = DBUtil.createQuery(qString, Hcours.class)
				.setParameter("subject", subject)
				.setParameter("id", course.getCourseId());
		DBUtil.updateDB(uQuery);

	}

	// update department for course
	public static void updateCourse(Hcours course, Hdepartment department) {
		String qString = "update Hcours h set h.hdepartment =:department where courseId =:id";
		TypedQuery<Hcours> uQuery = DBUtil.createQuery(qString, Hcours.class)
				.setParameter("department", department)
				.setParameter("id", course.getCourseId());
		DBUtil.updateDB(uQuery);
	}


	// disable course
	public static void disableCourse(Hcours course) {
		String qString = "update Hcours h set h.enabled = \'no\' where courseId =:id";
		TypedQuery<Hcours> uQuery = DBUtil.createQuery(qString, Hcours.class)
				.setParameter("id", course.getCourseId());
		DBUtil.updateDB(uQuery);
	}

	//enable course
	public static void enableCourse(Hcours course) {
		String qString = "update Hcours h set h.enabled = \'yes\' where courseId =:id";
		TypedQuery<Hcours> uQuery = DBUtil.createQuery(qString, Hcours.class)
				.setParameter("id", course.getCourseId());
		DBUtil.updateDB(uQuery);
	}

	// create a classroom
	public static void createClassroom(long id, String building, int cap,
			int roomNum) {
		Hclassroom classroom = new Hclassroom();
		classroom.setClassroomId(id);
		classroom.setBuilding(building);
		classroom.setCapacity(cap);
		classroom.setRoomNumber(roomNum);
		classroom.setEnabled("yes");
		DBUtil.addToDB(classroom);
	}

	// update capacity or roomNumber for classroom parameter must be either of these. will be validated at a higher level
	public static void updateClassroom(Hclassroom classroom, String parameter,
			int value) {

		String qString = "update Hclassroom h set h." + parameter
				+ " =:param where courseId =:id";
		TypedQuery<Hclassroom> uQuery = DBUtil
				.createQuery(qString, Hclassroom.class)
				.setParameter("param", value)
				.setParameter("id", classroom.getClassroomId());
		DBUtil.updateDB(uQuery);
	}

	// update building for classroom
	public static void updateClassroom(Hclassroom classroom, String building) {
		String qString = "update Hclassroom h set h.building =:building where courseId =:id";
		TypedQuery<Hclassroom> uQuery = DBUtil
				.createQuery(qString, Hclassroom.class)
				.setParameter("building", building)
				.setParameter("id", classroom.getClassroomId());
		DBUtil.updateDB(uQuery);

	}

	// disable classroom
	public static void disableClassroom(Hclassroom classroom) {
		String qString = "update Hclassroom h set h.enabled = \'no\' where courseId =:id";
		TypedQuery<Hclassroom> uQuery = DBUtil.createQuery(qString,
				Hclassroom.class)
				.setParameter("id", classroom.getClassroomId());
		DBUtil.updateDB(uQuery);
	}

	//enable classroom
	public static void enableClassroom(Hclassroom classroom) {
		String qString = "update Hclassroom h set h.enabled = \'yes\' where courseId =:id";
		TypedQuery<Hclassroom> uQuery = DBUtil.createQuery(qString,
				Hclassroom.class)
				.setParameter("id", classroom.getClassroomId());
		DBUtil.updateDB(uQuery);
	}

	

	// create a department
	public static void createDepartment(long id, String code, String name) {
		model.Hdepartment department = new model.Hdepartment();
		department.setDepartmentId(id);
		department.setCode(code);
		department.setName(name);
		department.setEnabled("yes");
		DBUtil.addToDB(department);
	}

	// update department name or code
	public static void updateDepartment(Hdepartment department,
			String nameOrCode, String value) {
		String param;
		if (nameOrCode.equalsIgnoreCase("name")) {
			param = "name";
		} else if (nameOrCode.equalsIgnoreCase("code")) {
			param = "code";
		} else {
			System.err
					.println("Error, String must be either \"name\" or \"code\"");
			return;
		}
		String qString = "update Hdepartment h set h." + param
				+ " = :value where courseId =:id";
		TypedQuery<Hdepartment> uQuery = DBUtil
				.createQuery(qString, Hdepartment.class)
				.setParameter("value", value)
				.setParameter("id", department.getDepartmentId());
		DBUtil.updateDB(uQuery);
	}

	// disable department
	public static void disableDepartment(Hdepartment department) {
		String qString = "update Hdepartment h set h.enabled = \'no\' where courseId =:id";
		TypedQuery<Hdepartment> uQuery = DBUtil.createQuery(qString,
				Hdepartment.class).setParameter("id",
				department.getDepartmentId());
		DBUtil.updateDB(uQuery);
	}

	// enable department
	public static void enableDepartment(Hdepartment department) {
		String qString = "update Hdepartment h set h.enabled = \'yes\' where courseId =:id";
		TypedQuery<Hdepartment> uQuery = DBUtil.createQuery(qString,
				Hdepartment.class).setParameter("id",
				department.getDepartmentId());
		DBUtil.updateDB(uQuery);
	}

	// create a major
	public static void createMajor(long id, Hdepartment department, String name) {
		model.Hmajor major = new model.Hmajor();
		major.setHdepartment(department);
		major.setName(name);
		major.setEnabled("yes");
		major.setMajorId(id);
		DBUtil.addToDB(major);
	}

	// update major name
	public static void updateMajor(Hmajor major, String name) {
		String qString = "update Hmajor h set h.name =:name where courseId =:id";
		TypedQuery<Hmajor> uQuery = DBUtil.createQuery(qString, Hmajor.class)
				.setParameter("name", name)
				.setParameter("id", major.getMajorId());
		DBUtil.updateDB(uQuery);
	}

	// update major department
	public static void updateMajor(Hmajor major, Hdepartment department) {
		String qString = "update Hmajor h set h.hdepartment =:department where courseId =:id";
		TypedQuery<Hmajor> uQuery = DBUtil.createQuery(qString, Hmajor.class)
				.setParameter("department", department)
				.setParameter("id", major.getMajorId());
		DBUtil.updateDB(uQuery);
	}

	// disable major
	public static void disableMajor(Hmajor major) {
		String qString = "update Hmajor h set h.enabled = \'no\' where courseId =:id";
		TypedQuery<Hmajor> uQuery = DBUtil.createQuery(qString, Hmajor.class)
				.setParameter("id", major.getMajorId());
		DBUtil.updateDB(uQuery);
	}

	// enable major
	public static void enableMajor(Hmajor major) {
		String qString = "update Hmajor h set h.enabled = \'yes\' where courseId =:id";
		TypedQuery<Hmajor> uQuery = DBUtil.createQuery(qString, Hmajor.class)
				.setParameter("id", major.getMajorId());
		DBUtil.updateDB(uQuery);
	}

	// create a new class 
	public static void createClass(long id,  Hcours course, String day, String starttime, String endtime, String semester, String year) {
		Hclass newClass = new Hclass();
		newClass.setClassId(id);
		newClass.setDay(day);
		newClass.setEnabled("yes");
		newClass.setEndtime(endtime);
		newClass.setHcours(course);
		newClass.setSemester(semester);
		newClass.setStarttime(starttime);
		newClass.setYear(year);
		DBUtil.addToDB(newClass);
	}
	
	//add previous class to a new semester
	public static void addClasstoSemester(Hclass hclass, String semester){
		long classId=0; //need to decide how to change classId
		hclass.setClassId(classId);
		hclass.setSemester(semester);
		DBUtil.addToDB(hclass);
	}
	
	//test for using Object as generic. If working change other updates to match this
	public static void updateClass(Hclass hclass, String parameter, Object value){
		
		String q = "update Hclass h set h."+parameter+" = :param";
		TypedQuery<Hclass> query = DBUtil.createQuery(q, Hclass.class).setParameter("param", value);
		DBUtil.updateDB(query);
	}
	
	public static void updateClass(Hclass hclass, String endtime){
		String q = "update Hclass h set h.endtime = :endtime";
		TypedQuery<Hclass> query = DBUtil.createQuery(q, Hclass.class);
		DBUtil.updateDB(query);
	}
	
	public static void addInstructorToClass(Hclass hclass, Hofficial instructor){
		String qString = "update Hclassr h set h.official =:official where classId =:id";
		TypedQuery<Hmajor> uQuery = DBUtil.createQuery(qString, Hmajor.class)
				.setParameter("official", instructor)
				.setParameter("id", hclass.getClassId());
		DBUtil.updateDB(uQuery);
	}


	// change a new users type to(student, instructor advisor or administrator)
	public static void changeUserType(Huser user, String permission) {
		// TODO
	}

	// override maximum enrollment hold
	public static void overrideEnrollment() {
		// TODO
	}

	// view a list of all students taught by an instructor
	public List<model.Hstudent> studTaughtByInstr(Hofficial instructor) {
		List<Hstudent> students = new ArrayList<Hstudent>();
		for (Hclass h : instructor.getHclasses()) {
			for (Hclassenrollment e : h.getHclassenrollments()) {
				students.add(e.getHstudent());
			}
		}
		return students;
	}

	// view a list of all instructors that have taught a course
	public List<model.Hofficial> instrTaughtClass(Hcours course) {
		List<Hofficial> instructors = new ArrayList<Hofficial>();
		for (Hclass h : course.getHclasses()) {
			instructors.add(h.getHofficial());
		}
		return instructors;
	}

	// view a list of all classes of a course
	public static List<model.Hclass> classesByCourse(Hcours course) {
		return course.getHclasses();
	}

	// view a list of all classrooms used by a course
	public static List<model.Hclassroom> classroomsByCourse(Hcours course) {
		List<Hclassroom> classrooms = new ArrayList<Hclassroom>();
		for (Hclass h : course.getHclasses()) {
			classrooms.add(h.getHclassroom());
		}
		return classrooms;
	}

	// view a list of all classrooms used by an instructor
	public static List<model.Hclassroom> classroomsByInstr(Hofficial instructor) {
		List<Hclassroom> classrooms = new ArrayList<Hclassroom>();
		for (Hclass h : instructor.getHclasses()) {
			classrooms.add(h.getHclassroom());
		}
		return classrooms;

	}

	// view a list of all classrooms used by a student
	public static List<model.Hclassroom> classroomsByStudent(Hstudent student) {
		List<Hclassroom> classrooms = new ArrayList<Hclassroom>();
		for (Hclassenrollment h : student.getHclassenrollments()) {
			classrooms.add(h.getHclass().getHclassroom());
		}
		return classrooms;
	}

}
