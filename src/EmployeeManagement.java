import java.util.ArrayList;
import java.util.Scanner;

class Employee {
  int ID;
  String Name;
  double Salary;

  Employee(int id, String name, double salary) {
    ID = id;
    Name = name;
    Salary = salary;
  }

  @Override
  public String toString() {
    return "ID: " + ID + "\tName: " + Name + "\tSalary: " + Salary;
  }

  void changeValue(String newName) { Name = newName; }
  void changeValue(double newSalary) { Salary = newSalary; }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null || getClass() != obj.getClass())
      return false;
    Employee employee = (Employee)obj;
    return ID == employee.ID || Name.equals(employee.Name);
  }
}

class Operations {
  int Finder(ArrayList<Employee> arr, String name) {
    Employee searchByName = new Employee(0, name, 0);
    if (arr.contains(searchByName)) {
      int index = arr.indexOf(searchByName);
      Employee foundEmployee = arr.get(index);
      System.out.println(foundEmployee.toString());
      return index;
    } else {
      System.out.println("Employee not found by name.");
      return -1;
    }
  }

  int Finder(ArrayList<Employee> arr, int id) {
    Employee searchById = new Employee(id, "", 0);
    if (arr.contains(searchById)) {
      int index = arr.indexOf(searchById);
      Employee foundEmployee = arr.get(index);
      System.out.println(foundEmployee.toString());
      return index;
    } else {
      System.out.println("Employee not found by ID.");
      return -1;
    }
  }

  void addEmployee(ArrayList<Employee> arr, Scanner obj) {
    System.out.print("ID: ");
    int id = obj.nextInt();
    obj.nextLine();
    System.out.print("Name: ");
    String name = obj.nextLine();
    System.out.print("Salary: ");
    double salary = obj.nextDouble();

    Employee temp = new Employee(id, name, salary);
    arr.add(temp);
  }

  void removeEmployee(ArrayList<Employee> arr, int id) {
    int idx = Finder(arr, id);
    if (idx != -1) {
      Employee foundEmployee = arr.get(idx);
      System.out.println("Removing employee\n" + foundEmployee.toString());
      arr.remove(foundEmployee);
    }
  }

  void removeEmployee(ArrayList<Employee> arr, String name) {
    int idx = Finder(arr, name);
    if (idx != -1) {
      Employee foundEmployee = arr.get(idx);
      System.out.println("Removing employee\n" + foundEmployee.toString());
      arr.remove(foundEmployee);
    }
  }

  void updateEmployee(ArrayList<Employee> arr, int id, Scanner obj) {
    int idx = Finder(arr, id);
    if (idx != -1) {
      Employee foundEmployee = arr.get(idx);
      System.out.println("Current details: " + foundEmployee.toString());
      System.out.println("What do you want to update?");
      System.out.println("1. Name");
      System.out.println("2. Salary");
      System.out.print("Choose: ");
      int choice = obj.nextInt();
      obj.nextLine();

      switch (choice) {
      case 1:
        System.out.print("Enter new name: ");
        String newName = obj.nextLine();
        foundEmployee.changeValue(newName);
        System.out.println("Name updated successfully.");
        break;

      case 2:
        System.out.print("Enter new salary: ");
        double newSalary = obj.nextDouble();
        foundEmployee.changeValue(newSalary);
        System.out.println("Salary updated successfully.");
        break;

      default:
        System.out.println("Invalid choice.");
      }
    }
  }
}

class exp1 {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    ArrayList<Employee> employees = new ArrayList<>();
    Operations operations = new Operations();
    boolean flag = true;

    System.out.println("Choose Operations-");
    System.out.println("1. Add employee\n2. Find Employee\n3. Remove " +
                       "Employee\n4. Update Employee\n5. Exit");

    while (flag) {
      int choice;
      System.out.print("Enter your choice: ");
      choice = input.nextInt();
      input.nextLine();

      switch (choice) {
      case 1:
        operations.addEmployee(employees, input);
        break;

      case 2:
        System.out.println("Enter name or ID: ");
        String key = input.nextLine();
        try {
          int id = Integer.parseInt(key);
          operations.Finder(employees, id);
        } catch (NumberFormatException e) {
          operations.Finder(employees, key);
        }
        break;

      case 3:
        System.out.println("Enter name or ID: ");
        String removeKey = input.nextLine();
        try {
          int removeId = Integer.parseInt(removeKey);
          operations.removeEmployee(employees, removeId);
        } catch (NumberFormatException e) {
          operations.removeEmployee(employees, removeKey);
        }
        break;

      case 4:
        System.out.print("Enter employee ID to update: ");
        int updateId = input.nextInt();
        input.nextLine();
        operations.updateEmployee(employees, updateId, input);
        break;

      case 5:
        flag = false;
        System.out.println("Exiting...");
        break;

      default:
        System.out.println("Invalid choice. Try again.");
      }
    }
    input.close();
  }
}
