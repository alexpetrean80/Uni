from dataclasses import dataclass
from datetime import date


@dataclass
class Assignment:
    __id: int
    __description: str
    __deadline: date

    @property
    def id(self):
        return self.__id

    @id.setter
    def id(self, id):
        self.__id = id

    @property
    def description(self):
        return self.__description

    @description.setter
    def description(self, description):
        self.__description = description

    @property
    def deadline(self):
        return self.__deadline

    @deadline.setter
    def deadline(self, deadline):
        self.__deadline = deadline

    @property
    def attributes(self):
        return "{},{},{}\n".format(self.__id, self.__description, self.__deadline)

    @staticmethod
    def string_to_entity(string: str):
        id = int(string.strip().split(',')[0])
        description = str(string.strip().split(',')[1])
        date_of_assignment = date(string.strip().split(',')[2])
        return Assignment(id, description, date_of_assignment)
