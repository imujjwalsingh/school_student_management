package school.driver;

// Driver class

import java.util.Scanner;
import school.entity.Student;
import school.service.StudentService;

public class StudentDriver {
	static Student student;
	static StudentService service;

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		while(true) {
			System.out.println("Choose any option : \n");
			System.out.println("Enter 1 for INSERT (create) a student record");
			System.out.println("Enter 2 for SELECT (fetch) a student record");
			System.out.println("Enter 3 for UPDATE (update) a student record");
			System.out.println("Enter 4 for DELETE (delete) a student record");
			System.out.println("Enter 5 to close the application");
			
			int num = sc.nextInt();
			if(num == 5) {
				System.out.println("Application exited!");
				break;
			}
			
			service = new StudentService();
			
			int id;
			String name;
			int age;
			
			switch (num) {
			case 1:
				// INSERT case logic
				
				System.out.println("Enter student id : ");
				id = sc.nextInt();
				
				System.out.println("Enter student name : ");
				name = sc.next();
				
				System.out.println("Enter student age : ");
				age = sc.nextInt();
				
				student = new Student(id, name, age);
				service.create(student);
				
				break;
				
			case 2:
				// SELECT case logic
				
				System.out.println("Enter student id :");
				id = sc.nextInt();
				
				service.fetch(id);
				
				break;
				
			case 3:
				// UPDATE case logic
				
				System.out.println("Enter student id : ");
				id = sc.nextInt();
				
				System.out.println("Enter student name : ");
				name = sc.next();
				
				System.out.println("Enter student age : ");
				age = sc.nextInt();
				
				student = new Student(id, name, age);
				service.update(student);
				
				break;
				
			case 4:
				// DELETE  case logic
				
				System.out.println("Enter student id : ");
				id = sc.nextInt();
				
				service.delete(id);
				break;
				
			default:
				// EXCEPTION case logic
				
				System.out.println("application terminating by default!");
				System.exit(0);
				
			}

		}
		
		sc.close();
	}

}
