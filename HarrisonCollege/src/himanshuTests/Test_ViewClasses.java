package himanshuTests;

import static org.junit.Assert.*;

import java.util.List;

import himanshu.ViewClasses;
import himanshu.ViewGrades;
import model.Hclass;
import model.Hstudent;

import org.junit.Test;

public class Test_ViewClasses {

	@Test
	public void test() {
	assertNotNull(ViewClasses.getAllClasses((long)7777));
	}
	@Test
	public void test_class_semester() {
	List<Hclass> classes = ViewClasses.getAllClassesinCurrSem(77777);
	for(Hclass temp:classes)
		System.out.println(temp.getClassId());
	}
	@Test
	public void test_class_in_previous_semester() {
	assertNotNull(ViewClasses.getAllClassesInSemester(7777, "fall", "2015"));
	}
	
	@Test
	public void test_all_students_in_a_class() {
		List<Hclass> classes = ViewClasses.getAllClassesInSemester(7777, "fall", "2015");
		for(Hclass  temp: classes)
		{	
			List<Hstudent> students = ViewClasses.getAllStudentsInClass(temp);
			for(Hstudent temp1: students)
			{
				System.out.println(temp1.getHuser().getName());
			}
		}
	}
	@Test
	public void test_View_Grades() {
	assertNotNull(ViewGrades.getClassGrades(10, "fall", "2015"));
	assertNotNull(ViewGrades.getClassGrades(10));
	}

	@Test
	public void test_all_students_all_classes() {
		List<Hstudent> students = ViewClasses.getAllStudentsAllClasses(7777);
		assertNotNull(students);
		for(Hstudent  temp: students)
		{	
			System.out.println(temp.getHuser().getName());
		}
	}
	
}
