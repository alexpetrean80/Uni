from dataclasses import dataclass


@dataclass
class Student:
    __id: int
    __name: str
    __group: int

    @property
    def id(self):
        return self.__id

    @id.setter
    def id(self, id):
        self.__id = id

    @property
    def name(self):
        return self.__name

    @name.setter
    def name(self, name):
        self.__name = name

    @property
    def group(self):
        return self.__group

    @group.setter
    def group(self, group):
        self.__group = group

    @property
    def attributes(self):
        return "{},{},{}\n".format(self.__id, self.__name, self.__group)

    @staticmethod
    def string_to_entity(string: str):
        string_as_student = string.strip().split(',')
        return Student(int(string_as_student[0]), str(string_as_student[1]), int(string_as_student[2]))
