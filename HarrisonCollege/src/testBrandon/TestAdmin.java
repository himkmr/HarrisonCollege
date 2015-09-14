package testBrandon;

import static org.junit.Assert.*;
import model.Hdepartment;

import org.junit.Before;
import org.junit.Test;

import customTools.DBUtil;
import brandon.Admin;

public class TestAdmin {
	Hdepartment department;
	@Before
	public void setUp() throws Exception {
		department = DBUtil.createQuery("select h from Hdepartment where h.departmentId = 111",Hdepartment.class).getSingleResult();
	}

	@Test
	public void testCreateCourse() {		
		Admin.createCourse(1, department, "Modern Physics", 3);
	}

	@Test
	public void testUpdateCourseHcoursInt() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateCourseHcoursString() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateCourseHcoursHdepartment() {
		fail("Not yet implemented");
	}

	@Test
	public void testDisableCourse() {
		fail("Not yet implemented");
	}

	@Test
	public void testEnableCourse() {
		fail("Not yet implemented");
	}

	@Test
	public void testCreateClassroom() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateClassroomHclassroomStringInt() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateClassroomHclassroomString() {
		fail("Not yet implemented");
	}

	@Test
	public void testDisableClassroom() {
		fail("Not yet implemented");
	}

	@Test
	public void testEnableClassroom() {
		fail("Not yet implemented");
	}

	@Test
	public void testCreateDepartment() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateDepartment() {
		fail("Not yet implemented");
	}

	@Test
	public void testDisableDepartment() {
		fail("Not yet implemented");
	}

	@Test
	public void testEnableDepartment() {
		fail("Not yet implemented");
	}

	@Test
	public void testCreateMajor() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateMajorHmajorString() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateMajorHmajorHdepartment() {
		fail("Not yet implemented");
	}

	@Test
	public void testDisableMajor() {
		fail("Not yet implemented");
	}

	@Test
	public void testEnableMajor() {
		fail("Not yet implemented");
	}

	@Test
	public void testCreateClass() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddClasstoSemester() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateClassHclassStringObject() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateClassHclassString() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddInstructorToClass() {
		fail("Not yet implemented");
	}

	@Test
	public void testChangeUserType() {
		fail("Not yet implemented");
	}

	@Test
	public void testOverrideEnrollment() {
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
