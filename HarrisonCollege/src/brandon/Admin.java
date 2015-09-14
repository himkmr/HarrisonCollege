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
		course.setCreditHours(hours);
		DBUtil.updateDB(course);
	}

	// update subject for course
	public static void updateCourse(Hcours course, String subject) {
		course.setSubject(subject);
		DBUtil.updateDB(course);

	}

	// update department for course
	public static void updateCourse(Hcours course, Hdepartment department) {
		course.setHdepartment(department);
		DBUtil.updateDB(course);
	}

	// disable course
	public static void disableCourse(Hcours course) {
		course.setEnabled("no");
		DBUtil.updateDB(course);
	}

	// enable course
	public static void enableCourse(Hcours course) {
		course.setEnabled("yes");
		DBUtil.updateDB(course);
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

	// update capacity,roomNumber,building
	public static void updateClassroom(Hclassroom classroom, String parameter,
			Object value) {
		switch (parameter.toLowerCase()) {
		case ("capacity"):
			classroom.setCapacity((int) value);
			break;
		case ("roomnumber"):
			classroom.setRoomNumber((int) value);
			break;
		case ("building"):
			classroom.setBuilding((String) value);
			break;
		default:
			System.err.println("error wrong parameter");
			return;
		}
		DBUtil.updateDB(classroom);
	}


	// disable classroom
	public static void disableClassroom(Hclassroom classroom) {
		classroom.setEnabled("no");
		DBUtil.updateDB(classroom);
	}

	// enable classroom
	public static void enableClassroom(Hclassroom classroom) {
		classroom.setEnabled("yes");
		DBUtil.updateDB(classroom);
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
			String parameter, String value) {
		switch (parameter.toLowerCase()) {
		case ("code"):
			department.setCode(value);
			break;
		case ("name"):
			department.setName(value);
			break;
		default:
			System.err.println("error wrong parameter");
			return;
		}
		DBUtil.updateDB(department);
	}

	// disable department
	public static void disableDepartment(Hdepartment department) {
		department.setEnabled("no");
		DBUtil.updateDB(department);
	}

	// enable department
	public static void enableDepartment(Hdepartment department) {
		department.setEnabled("yes");
		DBUtil.updateDB(department);
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

	// update major name, department,
	public static void updateMajor(Hmajor major, String parameter, Object value) {
		switch (parameter.toLowerCase()) {
		case ("department"):
			major.setHdepartment((Hdepartment)value);;
			break;
		case ("name"):
			major.setName((String) value);
			break;
		default:
			System.err.println("error wrong parameter");
			return;
		}
		DBUtil.updateDB(major);
	}


	// disable major
	public static void disableMajor(Hmajor major) {
		major.setEnabled("no");
		DBUtil.updateDB(major);
	}

	// enable major
	public static void enableMajor(Hmajor major) {
		major.setEnabled("yes");
		DBUtil.updateDB(major);
	}

	// create a new class
	public static void createClass(long id, Hcours course, String day,
			String starttime, String endtime, String semester, String year) {
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

	// add previous class to a new semester
	public static void addClasstoSemester(Hclass hclass, String semester, String year) {
		long classId = 0; // need to decide how to change classId
		hclass.setClassId(classId);
		hclass.setSemester(semester);
		hclass.setYear(year);
		DBUtil.addToDB(hclass);
	}
	
	//add instructor to class
	public static void addInstructorToClass(Hclass hclass, Hofficial instructor) {
		hclass.setHofficial(instructor);
		DBUtil.addToDB(hclass);
	}

	// test for using Object as generic. If working change other updates to
	// match this
	public static void updateClass(Hclass hclass, String parameter, Object value) {

		switch (parameter.toLowerCase()) {
		case ("day"):
			hclass.setDay((String) value);
			break;
		case ("endtime"):
			hclass.setEndtime((String)value);
			break;
		case ("starttime"):
			hclass.setStarttime((String) value);
			break;
		case ("classroom"):
			hclass.setHclassroom((Hclassroom) value);
			break;
		case ("course"):
			hclass.setHcours((Hcours) value);
			break;
		case ("semester"):
			hclass.setSemester((String) value);
			break;
		case ("year"):
			hclass.setYear((String) value);
			break;
		default:
			System.err.println("error wrong parameter");
			return;
		}
		DBUtil.updateDB(hclass);
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
