from domain.exceptions import EntityIDException


class Repo:
    def __init__(self, validator_class):
        self.__entities = dict()
        self.__validator = validator_class

    def save(self, id, entity):
        """
        if the item with given id doesn't exist in the repository, it is appended, otherwise EntityIDException is raised
        :param id: unique ID given to the entity
        :param entity: object to be appended
        :return: None
        """
        self.__validator.validate(entity)
        if self.find_by_id(id) is not None:
            raise EntityIDException("ID {} already exists.".format(id))
        self.__entities.setdefault(id, entity)

    def delete_by_id(self, id):
        """
        deletes an entity from the repository by id if it exists, otherwise EntityIDException is raised
        :param id: unique ID of the object
        :return: None
        """
        if self.find_by_id(id) is None:
            raise EntityIDException("ID {} does not exist.".format(id))
        self.__entities.pop(id)

    def update(self, id, value):
        """
        updates an object with a new value if it exists, otherwise EntityIDException is raised
        :param id: unique ID of the object
        :param value: new value of the object
        :return: None
        """
        if self.find_by_id(id) is None:
            raise EntityIDException("ID {} does not exist.".format(id))
        self.__entities[id] = value

    def find_all(self):
        """
        returns all the entities' values from the repository
        :return: values of the entities
        """
        return self.__entities.values()

    def find_by_id(self, id):
        """
        returns the object with a given ID if it exists, otherwise None
        :param id: unique ID of the object
        :return: object value/None
        """
        if id in self.__entities.keys():
            return self.__entities[id]
        return None
