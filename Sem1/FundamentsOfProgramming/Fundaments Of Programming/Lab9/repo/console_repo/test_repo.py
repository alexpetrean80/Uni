from unittest import TestCase
from domain.exceptions import EntityIDException
from repo.repository import Repo


class DummyValidator:
    @staticmethod
    def validate(entity):
        pass


class TestRepo(TestCase):
    def setUp(self):
        self.__repo = Repo(DummyValidator)
        self.__repo.save(1, 1)
        self.__repo.save(2, 2)
        self.__repo.save(3, 3)
        self.__repo.save(4, 4)
        self.__repo.save(5, 5)
        self.__repo.save(6, 6)

    def test_save__correct__passes(self):
        self.__repo.save(7, 7)
        self.assertIn(7, self.__repo.find_all())

    def test_save__wrong__error_is_raised(self):
        self.assertRaises(EntityIDException, lambda: self.__repo.save(1, 1))

    def test_delete_by_id__correct__passes(self):
        self.__repo.delete_by_id(2)
        self.assertNotIn(2, self.__repo.find_all())

    def test_delete_by_id__wrong__error_is_raised(self):
        self.assertRaises(EntityIDException, lambda: self.__repo.delete_by_id(8))

    def test_update__correct__working(self):
        self.__repo.update(1, -1)
        self.assertIn(-1, self.__repo.find_all())

    def test_update__wrong__error_is_raised(self):
        self.assertRaises(EntityIDException, lambda: self.__repo.update(9, 15))

    def test_find_all__correct__working(self):
        self.assertListEqual([1, 2, 3, 4, 5, 6], list(self.__repo.find_all()))

    def test_find_by_id__correct__value_is_returned(self):
        self.assertEqual(self.__repo.find_by_id(1), 1)

    def test_find_by_id__correct__None_is_returned(self):
        self.assertEqual(self.__repo.find_by_id(132124), None)


