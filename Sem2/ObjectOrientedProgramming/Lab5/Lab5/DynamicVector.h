#pragma once
#include <stdexcept>

template <class T>
class DynamicVector {
private: 
	T* elements_;
	int size_, capacity_;
    void resize();
public: 
  DynamicVector();
  DynamicVector(DynamicVector<T> const&);
  ~DynamicVector();
  void push_back(T const&);
  void pop_back(int);
  T& at(int) const;
  void insert(T, int);
  int get_size() const;
};

/**
 * \brief resizes the vector, doubling its capacity
 */
template<class T>
inline void DynamicVector<T>::resize()
{
  capacity_ *= 2;
  T *newElements = new T[capacity_];
  int index = 0;
  for (int i = 0; i < size_; i++) {
    newElements[i] = elements_[i];
  }
  delete[] elements_;
    elements_ = newElements;
}

/**
 * \brief creates a new DynamicVector of capacity 10
 */
template<class T>
inline DynamicVector<T>::DynamicVector()
{
  size_ = 0;
  capacity_ = 10;
  elements_ = new T[10];
}

/**
 * \brief creates a new DynamicVector as a copy of a given one
 * \param vector given DynamicVector
 */
template<class T>
inline DynamicVector<T>::DynamicVector(DynamicVector<T> const& vector){
  size_ = vector.size_;
  capacity_ = vector.capacity_;
  elements_ = new T[capacity_];
  for (int i = 0; i < size_; i++) {
    elements_[i] = vector.elements_[i];
  }
}

/**
 * \brief deallocates the memory of the DynamicVector
 */
template<class T>
inline DynamicVector<T>::~DynamicVector()
{
  delete[] elements_;
}

/**
 * \brief adds a new element at the end of the DynamicVector
 * \param element the new element
 */
template<class T>
inline void DynamicVector<T>::push_back(T const& element)
{
  if (size_ == capacity_) {
    resize();
 }
  elements_[size_++] = element;
}

/**
 * \brief deletes the element on the given position if it exists, else throws error
 * \param position the given position
 */
template<class T>
void DynamicVector<T>::pop_back(const int position)
{
  if (position < size_ && position > -1) {

    for (auto i = position; i < size_; i++) {
      elements_[i] = elements_[i + 1];
    }
    size_--;
  } else {
      throw std::invalid_argument("value out of bounds");
  }
  
}

/**
 * \brief returns the element on the given position if it exists, else throws error
 * \param position given position
 * \return element on the given position
 */
template<class T>
inline T& DynamicVector<T>::at(int position) const
{
  if (position < size_ && position > -1) {
    return elements_[position];
  }
  throw std::invalid_argument("value out of bounds");
}

/**
 * \brief inserts an element on a given position, if it exists, else throws error
 * \param element the element to be inserted
 * \param position the position on which the element is to be inserted
 */
template<class T>
inline void DynamicVector<T>::insert(T element, int position)
{
  if (position < size_ && position > -1) {
   if (size_ == capacity_) {
     resize();
   }
    size_++;
    for (int i = size_ - 1; i > position; i--) {
      elements_[i] = elements_[i - 1];
    }
    elements_[position] = element;
  } else {
    throw std::invalid_argument("Index out of bounds");
  }
}

/**
 * \brief returns the size of the DynamicVector
 * \return the size of the vector
 */
template<class T>
inline int DynamicVector<T>::get_size() const
{
  return size_;
}

