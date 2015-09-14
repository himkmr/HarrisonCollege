package testBrandon;

import static org.junit.Assert.*;
import model.*;

import org.junit.Before;
import org.junit.Test;

import customTools.DBUtil;
import brandon.Admin;

public class TestAdmin {
	Hdepartment department;
	Hdepartment department2;
	Hcours course;
	Hcours course2;
	Hclassroom classroom;
	Hmajor major;
	Hclass hclass;
	Hofficial instructor;
	@Before
	public void setUp() throws Exception {
		department = DBUtil.createQuery("select h from Hdepartment h where h.departmentId = 111",Hdepartment.class).getSingleResult();
		course = DBUtil.createQuery("select h from Hcours h where h.courseId = 1", Hcours.class).getSingleResult();
		department2 = DBUtil.createQuery("select h from Hdepartment h where h.departmentId = 222",Hdepartment.class).getSingleResult();
		classroom = DBUtil.createQuery("select h from Hclassroom h where h.classroomId = 20", Hclassroom.class).getSingleResult();
		major = DBUtil.createQuery("select h from Hmajor h where h.majorId = 100", Hmajor.class).getSingleResult();
		hclass = DBUtil.createQuery("select h from Hclass h where h.classId = 1234", Hclass.class).getSingleResult();
		course2 = DBUtil.createQuery("select h from Hcours h where h.courseId = 2", Hcours.class).getSingleResult();
		instructor = DBUtil.createQuery("select h from Hofficial h where h.officialId=12", Hofficial.class).getSingleResult();
	}

	@Test
	public void testCreateCourse() {		
	//	Admin.createCourse(1, department, "Modern Physics", 3);
	}

	@Test 
	public void testUpdateCourseHcoursInt() {
		
	//	Admin.updateCourse(course, 4);
	}

	@Test
	public void testUpdateCourseHcoursString() {
	//	Admin.updateCourse(course, "english");
	}

	@Test
	public void testUpdateCourseHcoursHdepartment() {
	//	Admin.updateCourse(course, department2);
	}

	@Test
	public void testDisableCourse() {
		//Admin.disableCourse(course);
	}

	@Test
	public void testEnableCourse() {
		//Admin.enableCourse(course);
	}

	@Test
	public void testCreateClassroom() {
		//Admin.createClassroom(20, "COM", 30, 123);
	}

	@Test
	public void testUpdateClassroomBuilding() {
	//	Admin.updateClassroom(classroom, "building", "McAllister");
	}
	
	@Test
	public void testUpdateClassroomCapacity() {
	//	Admin.updateClassroom(classroom, "capacity", 20);
	}
	
	@Test
	public void testUpdateClassroomRoomNumber() {
	//	Admin.updateClassroom(classroom, "roomNumber", 13);
	}

	@Test
	public void testDisableClassroom() {
		//Admin.disableClassroom(classroom);
	}

	@Test
	public void testEnableClassroom() {
		//Admin.enableClassroom(classroom);
	}

	@Test
	public void testCreateDepartment() {
	//	Admin.createDepartment(23, "COM", "Communications");
	}

	@Test
	public void testUpdateDepartmentName() {
	//	Admin.updateDepartment(department, "name", "Department 1");
	}
	
	@Test
	public void testUpdateDepartmentCode() {
	//	Admin.updateDepartment(department, "code", "uno");
	}

	@Test
	public void testDisableDepartment() {
		//Admin.disableDepartment(department);
	}

	@Test
	public void testEnableDepartment() {
		//Admin.enableDepartment(department);
	}

	@Test
	public void testCreateMajor() {
		//Admin.createMajor(100, department, "Engineering Physics");
	}

	@Test
	public void testUpdateMajorName() {
		//Admin.updateMajor(major, "name", "Math and Physics");
	}

	@Test
	public void testUpdateMajorDepartment() {
	//	Admin.updateMajor(major, "department", department2);
	}

	@Test
	public void testDisableMajor() {
	//	Admin.disableMajor(major);
	}

	@Test
	public void testEnableMajor() {
		Admin.enableMajor(major);
	}

	@Test
	public void testCreateClass() {
	//	Admin.createClass(1234, course, "MWF", "9:30", "10:30", "Spring", "2015");
	}

	@Test //Still need to fix how new id is created
	public void testAddClasstoSemester() {
	//	Admin.addClasstoSemester(hclass, "Spring", "2016");
	}

	@Test
	public void testUpdateClassDay() {
		//Admin.updateClass(hclass, "day", "TH");
	}
	
	@Test
	public void testUpdateClassEndTime() {
		//Admin.updateClass(hclass, "endtime", "12:30");
	}
	
	@Test
	public void testUpdateClassStartTime() {
		//Admin.updateClass(hclass, "starttime", "11:30");
	}
	
	@Test
	public void testUpdateClassClassroom() {
		//Admin.updateClass(hclass, "classroom", classroom);
	}
	
	@Test
	public void testUpdateClassCourse() {
		//Admin.updateClass(hclass, "course", course2);
	}
	@Test
	public void testUpdateClassSemester() {
	//	Admin.updateClass(hclass, "semester", "Fall");
	}
	@Test
	public void testUpdateClassYear() {
	//	Admin.updateClass(hclass, "year", "2014");
	}
	
	
	@Test
	public void testAddInstructorToClass() {
	//	Admin.addInstructorToClass(hclass, instructor);
	}

	@Test
	public void testChangeUserType() {
		//TODO
	//	Admin.changeUserType(instructor, "advisor");
	}

	@Test
	public void testOverrideEnrollment() {
		//TODO
		fail("Not yet implemented");
	}

	@Test
	public void testStudTaughtByInstr() {
		fail("Not yet implemented");
	}

	@Test
	public void testInstrTaughtClass() {
		fail("Not yet implemented");
	}

	@Test
	public void testClassesByCourse() {
		fail("Not yet implemented");
	}

	@Test
	public void testClassroomsByCourse() {
		fail("Not yet implemented");
	}

	@Test
	public void testClassroomsByInstr() {
		fail("Not yet implemented");
	}

	@Test
	public void testClassroomsByStudent() {
		fail("Not yet implemented");
	}

}
