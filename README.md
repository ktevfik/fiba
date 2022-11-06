# Fibabanka Assignments

## Assignment 1

Create Employee Entity with following fields:
- employeeId
- employeeName
- monthlySalary

Create 5 endpoints for Employee Rest Controller:
- GET /api/v1/employees. Returns all employees
- GET /api/v1/employee/{id}. Returns employee with given id.
- POST /api/v1/employee. Creates new employee.
- PUT /api/v1/employee/{id}. Updates employee with given id.
- DELETE /api/v1/employee/{id}. Deletes employee with given id.

Create 4 endpoints for Employee Client Controller:
- GET /employee/get . Get request to /api/v1/employee/{id}. Creates random employee with given id and returns employee.
- GET /employee/post . Post request to /api/v1/employee. Creates a new employee with random name and salary. Returns created employee.
- GET /employee/put . Put request to /api/v1/employee/{id} (id: hardcoded) and updates employee with random name and salary. Returns updated employee.
- GET /employee/delete . Delete request to /api/v1/employee/{id} (id: hardcoded) and deletes employee with given id. Returns deleted employee id.

## Assignment 2

Create Customer Entity with following fields:
- customerId
- customerName
- totalDebit

Create 5 endpoints for Customer Rest Controller:
- GET /api/v1/customers. Returns all customers
- GET /api/v1/customer/{id}. Returns customer with given id.
- POST /api/v1/customer. Creates new customer.
- PUT /api/v1/customer/{id}. Updates customer with given id.
- DELETE /api/v1/customer/{id}. Deletes customer with given id.

Create 4 endpoints for Customer Client Controller:
- GET /customer/get . Get request to /api/v1/customer/{id}. Creates random customer with given id and returns customer.
- GET /customer/post . Post request to /api/v1/customer. Creates a new customer with random name and totalDebit. Returns created customer.
- GET /customer/put . Put request to /api/v1/customer/{id} (id: hardcoded) and updates customer with random name and totalDebit. Returns updated customer.
- GET /customer/delete . Delete request to /api/v1/customer/{id} (id: hardcoded) and deletes customer with given id. Returns deleted customer id.

CustomerService interface with following methods:
- Customer createCustomer(Customer customer)
- void updateCustomer(Customer customer)
- Customer getCustomerById(Long id)
- List<Customer> getAllCustomers()
- void deleteCustomer(Long id)

CustomerServiceImpl implements CustomerService interface and applies business logic.

## Assignment 3

Create Employee Entity with following fields:
- employeeId
- employeeName
- monthlySalary
- department


Create Department Entity with following fields:
- departmentId
- departmentName
- employees

Create 7 endpoints for Employee Rest Controller:
- GET /api/v1/employees. Returns all employees
- GET /api/v1/employees/{id}. Returns employee with given id.
- GET /api/v1/employees/department/{id}. Returns all employees with given department id. Used relationship and used native sql query.
- GET /api/v1/high-salary/{salary}. Returns all employees with salary greater than given salary. Used relationship and used native sql query.
- POST /api/v1/employees. Creates new employee.
- PUT /api/v1/employees/{id}. Updates employee with given id.
- DELETE /api/v1/employees/{id}. Deletes employee with given id.

Create 7 endpoints for Employee Client Controller:
- GET /employee/get/all . Get request to /api/v1/employees. Returns all employees.
- GET /employee/get/{id} . Get request to /api/v1/employees/{id}. Returns employee with given id.
- GET /employee/get/department/{id} . Get request to /api/v1/employees/department/{id}. Returns all employees with given department id.
- GET /employee/get/high-salary/{salary} . Get request to /api/v1/high-salary/{salary}. Returns all employees with salary greater than given salary.
- GET /employee/post . Post request to /api/v1/employees. Creates a new employee with random name, salary and department. Returns created employee.
- GET /employee/put . Put request to /api/v1/employees/{id} (id: hardcoded) and updates employee with random name, salary and department. Returns updated employee.
- GET /employee/delete . Delete request to /api/v1/employees/{id} (id: hardcoded) and deletes employee with given id. Returns deleted employee id.

EmployeeService interface with following methods:
- Employee saveEmployee(Employee employee)
- Employee updateEmployee(Long id, Employee employee)
- Employee getEmployeeById(Long id)
- List<Employee> getAllEmployees()
- void deleteEmployee(Long id)
- List<Employee> getEmployeesByDepartmentId(Long id)
- List<Employee> getEmployeesBySalaryGreaterThan(Double salary)

EmployeeServiceImpl implements EmployeeService interface and applies business logic with connecting database.

----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

Create 5 endpoints for Department Rest Controller:
- GET /api/v1/departments. Returns all departments
- GET /api/v1/departments/{id}. Returns department with given id.
- POST /api/v1/departments. Creates new department.
- PUT /api/v1/departments/{id}. Updates department with given id.
- DELETE /api/v1/departments/{id}. Deletes department with given id.

Create 5 endpoints for Department Client Controller:
- GET /departments/get/all . Get request to /api/v1/departments. Returns all departments.
- GET /departments/get/{id} . Get request to /api/v1/departments/{id}. Returns department with given id.
- GET /departments/post . Post request to /api/v1/departments. Creates a new department with random name. Returns created department.
- GET /departments/put/{id} . Put request to /api/v1/departments/{id} (id: hardcoded) and updates department with random name. Returns updated department.
- GET /departments/delete/{id} . Delete request to /api/v1/departments/{id} (id: hardcoded) and deletes department with given id. Returns deleted department id.

DepartmentService interface with following methods:
- Department saveDepartment(Department department)
- Department updateDepartment(Long id, Department department)
- Department getDepartmentById(Long id)
- List<Department> getAllDepartments()
- void deleteDepartment(Long id)

DepartmentServiceImpl implements DepartmentService interface and applies business logic with connecting database.