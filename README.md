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