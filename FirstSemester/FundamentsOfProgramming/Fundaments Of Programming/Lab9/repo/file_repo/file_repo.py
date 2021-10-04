from domain.exceptions import EntityIDException, RepoException


class FileRepo:
    def __init__(self, file, domain_class, validator_class):
        self.__entities = file
        self.__domain_class = domain_class
        self.__validator = validator_class

    def save(self, id, entity):
        self.__validator.validate(entity)
        if self.find_by_id(id) is not None:
            raise EntityIDException("ID {} already exists.".format(id))
        file = open(self.__entities, 'a')
        file.write(entity.attributes)
        file.close()

    def delete_by_id(self, id):
        entities = list()
        if self.find_by_id(id) is None:
            raise EntityIDException("ID {} does not exist.".format(id))
        file = open(self.__entities, 'r')
        for line in file:
            entity = line.split()
            if int(entity[0]) != id:
                entities.append(line)
        file.close()
        file = open(self.__entities, 'w')
        for entity in entities:
            file.write(entity)
            file.write('\n')
        file.close()

    def update(self, id: int, value):
        entities = list()
        if self.find_by_id(id) is None:
            raise EntityIDException("ID {} does not exist.".format(id))
        file = open(self.__entities, 'r')
        for line in file:
            entity = line.split()
            if int(entity[0]) != id:
                entities.append(line)
            else:
                entities.append(value.attributes)
        file.close()
        file = open(self.__entities, 'w')
        for entity in entities:
            file.write(entity)
            file.write('\n')
        file.close()

    def find_all(self) -> list:
        entities = list()
        file = open(self.__entities, 'r')
        for line in file:
            if line != ["\n"]:
                entities.append(self.__domain_class.string_to_entity(line))
        file.close()
        return entities

    def find_by_id(self, id):
        file = open(self.__entities, 'r')
        lines = file.readlines()
        for line in lines:
            print(line)
            entity = self.__domain_class.string_to_entity(line)
            file.close()
            if entity.id == id:
                return entity
        return None
