package brandon;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.persistence.TypedQuery;

import customTools.DBUtil;
import model.*;

public class Admin {

	// create, update, list or disable a course
	public static void createCourse(Hdepartment department,
			String subject, int hours) {
		Hcours course = new Hcours();
		course.setHdepartment(department);
		course.setSubject(subject);
		course.setCreditHours(hours);
		course.setEnabled("yes");
		DBUtil.addToDB(course);
	}
	
	public static List<Hpendingadmission> getPendingAdmissions(){
		String q = "select h from Hpendingadmission h";
		return DBUtil.createQuery(q,Hpendingadmission.class).getResultList();
	}

	public static void acceptAdmission(Hpendingadmission admission){
		String[] info = admission.getMessage().split(",");
		Huser user = new Huser();
		user.setName(info[0]);
		user.setPassword(info[1]);
		user.setPermissions(admission.getPermissions());
		user.setUserId(admission.getUserid());
		DBUtil.addToDB(user);
		String permission = user.getPermissions().toLowerCase();
				
		if(permission.equals("student")){ 
			System.out.println(permission);
			Hstudent student = new Hstudent();
			System.out.println(user.getUserId());
			student.setStudentId(user.getUserId());
			System.out.println(info[2]);
			student.setMajor(getMajor(info[2]));
			
			student.setEntryYear(info[3]);
			DBUtil.addToDB(student);
		}
		else{ 
			Hofficial official = new Hofficial();
			official.setOfficialId(user.getUserId());
			official.setHdepartment(getDepartment(info[2]));
			official.setType(permission);
			DBUtil.addToDB(official);
		}
	}
	
	public static void deletePending(Hpendingadmission admission){
		DBUtil.delete(admission);
	}
	
	public static Hdepartment getDepartment(String name){
		String q = "select h from Hdepartment h where h.name like :name";
		return DBUtil.createQuery(q,Hdepartment.class).setParameter("name", name).getSingleResult();
	}
	
	public static Hmajor getMajor(String name){
		String q = "select h from Hmajor h where h.name like :name";
		return DBUtil.createQuery(q,Hmajor.class).setParameter("name", name).getSingleResult();
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
	public static void createClassroom(String building, int cap,
			int roomNum) {
		Hclassroom classroom = new Hclassroom();
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
	public static void createDepartment(String code, String name) {
		model.Hdepartment department = new model.Hdepartment();
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
	public static void createMajor(Hdepartment department, String name) {
		model.Hmajor major = new model.Hmajor();
		major.setHdepartment(department);
		major.setName(name);
		major.setEnabled("yes");
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
	public static void createClass(Hcours course, String day, Hclassroom classroom,
			String starttime, String endtime, String semester, String year) {
		Hclass newClass = new Hclass();
		newClass.setDay(day);
		newClass.setEnabled("yes");
		newClass.setHclassroom(classroom);
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

	// update class based on day,endtime,starttime,classroom,course,semester,year
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


	// override maximum enrollment hold 
	public static void overrideEnrollment(Hclass hclass, int max) {
		Hclassroom classroom = hclass.getHclassroom();
		classroom.setCapacity(max);
		DBUtil.updateDB(classroom);
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
	
	public static List<model.Hofficial> getAllInstructors(){
		String q = "select h from Hofficial h where h.type like \'instructor\'";
		return DBUtil.createQuery(q, Hofficial.class).getResultList();
	}
	
	public static List<model.Hstudent> getAllStudents(){
		String q = "select h from Hstudent h";
		return DBUtil.createQuery(q, Hstudent.class).getResultList();
	}
	
	public static List<model.Hofficial> getAllAdvisors(){
		String q = "select h from Hofficial h where h.type like \'advisor\'";
		return DBUtil.createQuery(q, Hofficial.class).getResultList();
	}
	
	public static List<model.Hdepartment> getAllDepartments(){
		String q = "select h from Hdepartment h";
		return DBUtil.createQuery(q, Hdepartment.class).getResultList();
	}
	
	public static List<model.Hcours> getAllCourses(){
		String q = "select h from Hcours h";
		return DBUtil.createQuery(q, Hcours.class).getResultList();
	}
	
	public static List<model.Hclass> getAllClasses(){
		String q = "select h from Hclass h ";
		return DBUtil.createQuery(q, Hclass.class).getResultList();
	}
	
	public static List<model.Hclassroom> getAllClassrooms(){
		String q = "select h from Hclassroom h ";
		return DBUtil.createQuery(q, Hclassroom.class).getResultList();
	}
	
	public static List<model.Hmajor> getAllMajors(){
		String q = "select h from Hmajor h ";
		return DBUtil.createQuery(q, Hmajor.class).getResultList();
	}
}
