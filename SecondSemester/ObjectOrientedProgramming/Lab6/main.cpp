#include "tests.h"
#include "ui.h"

int main() {
    tests();
    UI ui;
    ui.run_console();
    return 0;
}
