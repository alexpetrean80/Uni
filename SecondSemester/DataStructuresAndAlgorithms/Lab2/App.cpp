#include <iostream>

#include "ShortTest.h"
#include "ExtendedTest.h"

int main(){
    testAll();
	testAllExtended();
    testEmpty();
    std::cout<<"Finished SMM Tests!"<<std::endl;
	system("pause");
}
