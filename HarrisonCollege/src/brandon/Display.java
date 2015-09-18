package brandon;

import java.util.List;

import model.Hclass;
import model.Hclassroom;
import model.Hcours;
import model.Hdepartment;
import model.Hmajor;
import model.Hofficial;
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
	
	protected static String displayOfficials(List<Hofficial> officials) {
		StringBuilder display = new StringBuilder();
		//display.append("<div class=\"container\"><h2>Instructors</h2>"
		//		+ "<table class=\"table table-hover\"><thead><tr><th>Id</th><th>Name</th><th>Department</th> <th>Office #</th></tr></thead><tbody>");

		for (Hofficial i : officials) {
			display.append("<tr><td>"
					+ i.getOfficialId()
					+ "</td><td>"
					+ i.getHuser().getName()
					+ "</td><td>"
					+ i.getHdepartment().getName()
					+ "</td><td>"
					+ i.getOfficeNumber() + "</td></tr>");
		}
	//	display.append("</tbody></table></div>");
		return display.toString();
	}
	
	protected static String displayDepartments(List<Hdepartment> departments) {
		StringBuilder display = new StringBuilder();

	//	display.append("<div class=\"container\"><h2>Departments</h2>"
	//			+ "<table class=\"table table-hover\"><thead><tr><th>Code</th><th>Name</th></tr></thead><tbody>");
		for (Hdepartment d : departments) {

			if (d.getEnabled().equals("yes")) {
				display.append("<tr>");

			} else {
				display.append("<tr style=\"color: #fff; background: black;\">");
			}
			display.append("<td>"
					+ d.getCode()
					+ "</td><td>"
					+ d.getName()
					+ "</td></tr>");
		}
	//	display.append("</tbody></table></div>");
		return display.toString();
	}
	
	protected static String displayCourses(List<Hcours> courses) {
		StringBuilder display = new StringBuilder();

		//display.append("<div class=\"container\"><h2>Courses</h2>"
		//		+ "<table class=\"table table-hover\"><thead><tr><th>Subject</th><th>Credit Hours</th></tr></thead><tbody>");
		for (Hcours c : courses) {
			if (c.getEnabled().equals("yes")) {
				display.append("<tr><td>");
			} else {
				display.append("<tr style=\"color: #fff; background: black;\"><td>");
			}
			display.append(c.getSubject()
					+ "</td><td>"
					+ c.getCreditHours()					
					+ "</td></tr>");
		}

	//	display.append("</tbody></table></div>");
		return display.toString();
	}
	
	protected static String displayMajors(List<Hmajor> majors) {
		StringBuilder display = new StringBuilder();

		//display.append("<div class=\"container\"><h2>Majors</h2>"
		//		+ "<table class=\"table table-hover\"><thead><tr><th>Name</th><th>Department</th></tr></thead><tbody>");
		for (Hmajor m : majors) {
			if (m.getEnabled().equals("yes")) {
				display.append("<tr><td>");

			} else {
				display.append("<tr style=\"color: #fff; background: black;\"><td>");
			}
			display.append(m.getName()
					+ "</td><td>"
					+ m.getHdepartment().getName()
					+ "</td></tr>");
		}

	//	display.append("</tbody></table></div>");
		return display.toString();
	}


}
