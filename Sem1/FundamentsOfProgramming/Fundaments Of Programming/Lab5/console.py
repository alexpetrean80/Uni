import re

from service.operations import Service


class UI:
    def console_menu(self):
        service = Service()
        service.populate_list()
        while True:
            try:
                self.print_menu()
                user_input = self.read_user_input()
                if user_input == 1:
                    try:
                        day = input("Enter the day of the expense: ")
                        amount_of_money = input("Enter the amount of money: ")
                        type_of_expense = input("Enter the type of expense: ")
                        service.add_expense_to_list(int(day), int(amount_of_money), type_of_expense)
                    except ValueError as value_error:
                        print(value_error)
                    except TypeError as type_error:
                        print(type_error)
                    self.another_operation()
                elif user_input == 2:
                    self.print_list_of_expenses(service.list_of_expenses)
                    self.another_operation()
                elif user_input == 3:
                    try:
                        value = input("Enter the value: ")
                        service.filter_list(int(value))
                    except TypeError as type_error:
                        print(type_error)
                    except ValueError as value_error:
                        print(value_error)

                    self.another_operation()
                elif user_input == 4:
                    try:
                        service.undo()
                    except IndexError as ie:
                        print(ie)
                    self.another_operation()
                elif user_input == 5:
                    self.exit_application()
                else:
                    raise ValueError("Value needs to be between 1 and 5!!")
            except ValueError as value_error:
                print(value_error)

    @staticmethod
    def exit_application():
        print("\nExiting application...\n")
        exit(0)

    @staticmethod
    def print_menu():
        print("<--------------Expenses------------------------->\n\n"
              "1. Add expense to list.\n"
              "2. Print list of expenses.\n"
              "3. Filter the expenses above a certain value.\n"
              "4. Undo the last operation.\n"
              "\n5. Exit application.")

    @staticmethod
    def read_user_input():
        while True:
            try:
                user_input = int(input("Enter the operation."))
                return user_input
            except TypeError:
                print("\nInput needs to be an integer.\n")

    def another_operation(self):
        while True:
            try:
                user_input = input("Do you want to make another operation?(y/n)")
                if re.match(r"[yY]", user_input):
                    break
                elif re.match(r"[nN]", user_input):
                    self.exit_application()
                else:
                    raise ValueError("\nInput needs to be either \'y\' or \'n\'.\n")
            except ValueError as value_error:
                print(value_error)

    @staticmethod
    def print_list_of_expenses(list_of_expenses):
        for expense in list_of_expenses:
            print("Expense's day: " + str(expense.day))
            print("Expense's value: $" + str(expense.amount_of_money))
            print("expense's type: " + expense.type + '\n')
