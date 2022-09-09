## To-Do List Project.

REST-controllers for managing the following resources:

 - Collection of all the users: `/api/users`
 - Collection of todos for the user: `/api/users/{u_id}/todos`
 - Collection of collaborators for the todo: `/api/users/{u_id}/todos/{t_id}/collaborators`
 - Collection of tasks for the todo: `/api/users/{u_id}/todos/{t_id}/tasks`

1) Used GET, POST, PUT, DELETE methods to manage the Collections.
2) Added security rules.
3) Customize exception handling, use `ResponseStatusException`
4) implemented JWT authentication .


There are three predefined users in the DB with roles ADMIN and USER.

| Login         | Password | Role  |
|---------------|:--------:|:-----:|
| mike@mail.com |   1111   | ADMIN |
| nick@mail.com |   2222   | USER  |
| nora@mail.com |   3333   | USER  |

