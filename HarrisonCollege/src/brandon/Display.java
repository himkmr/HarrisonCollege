package brandon;

import java.util.List;

import model.Hclass;
import model.Hclassroom;
import model.Hcours;
import model.Hstudent;

public class Display {

	protected static String displayClassrooms(List<Hclassroom> classrooms){ 
		StringBuilder display = new StringBuilder();
		for (Hclassroom c : classrooms) {
			if (c.getEnabled().equals("yes")) {
				display.append("<tr><td>");

			} else {
				display.append("<tr style=\"color: #fff; background: black;\"><td>");
			}
			display.append(c.getRoomNumber()
					+ "</td><td>"
					+ c.getBuilding()
					+ "</td><td>"
					+ c.getCapacity()
					+ "</td></tr>");
		}
		System.out.println(display);
		return display.toString();
	}
	
	protected static String displayClasses(List<Hclass> classes){
		StringBuilder display = new StringBuilder();
		
		for (Hclass c : classes) {
			if (c.getEnabled().equals("yes")) {
				display.append("<tr><td>");

			} else {
				display.append("<tr style=\"color: #fff; background: black;\"><td>");
			}
			display.append(c.getHcours().getCourseId()
					+ "</td><td>"
					+ c.getHcours().getSubject()
					+ "</td><td>"
					+ c.getDay()
					+ "</td><td>"
					+ c.getStarttime()
					+ "</td><td>"
					+ c.getEndtime()
					+ "</td><td>"
					+ c.getSemester()
					+ "</td><td>"
					+ c.getYear()
					+ "</td></tr>");
		}
		System.out.println(display);
		return display.toString();
	}
	
	protected static String displayStudents(List<Hstudent> students) {
		StringBuilder display = new StringBuilder();
		
		for (Hstudent s : students) {
			display.append("<tr><td>"
					+ s.getStudentId()
					+ "</td><td>"
					+ s.getHuser().getName()
					+ "</td><td>"
					+ s.getEntryYear()
					+ "</td></tr>");
		}
		
		return display.toString();
	}
}
