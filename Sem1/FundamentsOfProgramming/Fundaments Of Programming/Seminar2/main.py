import os, sys

class Student:
    def __init__(self, id, name, grade):
        self.__id = id
        self.__name = name
        self.__grade = grade

    def get_id(self):
        return self.__id
    
    def set_id(self, id):
        self.__id = id
    
    def get_name(self):
        return self.__name
    
    def set_name(self, name):
        self.__name = name
    
    def get_grade(self):
        return self.__grade

    def set_grade(self, grade):
        self.__grade = grade
    
def get_student():
    id, name, grade = int(input('Enter the student\'s ID: ')), input('Enter the student\'s name: '), int(input('Enter the student\'s grade: '))
    student = Student(id, name, grade)
    return student

def add_to_list(students_database):
    students_database.append(get_student())

def delete_student_by_id(database):
    id = input("Enter the ID of the student which you want deleted: ")
    for student in database[:]:
        if student.get_id() is id:
            del student

def print_students_list(database):
    for student in database:
        print('ID: ', student.get_id(),'Name: ', student.get_name(),'Grade: ', student.get_grade())

def clear_console():
    if sys.platform == 'linux' or sys.platform == 'darwin':
        os.system('clear')
    elif sys.platform == 'windows':
        os.system('cls')

def check_operations():
    user_input = input('Do you want to make another change in the database? (y/n): ')
    if user_input == 'y':
        return True;
    elif user_input == 'n':
        return False;
    else:
        return check_operations()

def exit_application():
    print('\nExiting the application...')
    sys.exit(0)
def print_menu():
    clear_console()
    print('''\n1. Insert a new student.
2. Print the list of the students.
3. Delete the student by ID.
X. Exit the application.''')

def application_menu(database):
    print_menu()
    user_input = input('\nEnter the operation which you want to execute (1, 2, X): ')
    print()
    if user_input is '1':
        add_to_list(database)
        if check_operations() is True:
            application_menu(database)
        else:
            exit_application()
    elif user_input is '2':
        print_students_list(database)
        if check_operations() is True:
            application_menu(database)
        else: 
            exit_application()
    elif user_input is '3':
        delete_student_by_id(database)
        if check_operations() is True:
            application_menu(database)
        else: 
            exit_application()
    elif user_input is 'x' or 'X':
        exit_application()

if __name__ == '__main__':
    students_database = []
    application_menu(students_database)