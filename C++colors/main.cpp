#include <iostream>
#include "CPP_COLORS.h"

using namespace std;

int main() {

    /*
     * to compile c++ file in  linux
     * run `g++ main.cpp -o colors` in terminal
     *
     * */

    cout<<BLACK<<"┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓"<<RESET_COLOR<<endl;

    /* Regular Colors */
    cout<<BLACK<<"┃ 00xWolf "<<RED<<"00xWolf "<<GREEN<<"00xWolf "<<YELLOW<<"00xWolf "<<BLUE<<"00xWolf "<<PURPLE<<"00xWolf "<<CYAN<<"00xWolf "<<WHITE<<"00xWolf "<<BLACK<<"┃"<<RESET_COLOR<<endl;

    cout<<BLACK<<"┃                                                                 ┃"<<RESET_COLOR<<endl;

    /* Bold Colors */
    cout<<BOLD_BLACK<<"┃ 00xWolf "<<BOLD_RED<<"00xWolf "<<BOLD_GREEN<<"00xWolf "<<BOLD_YELLOW<<"00xWolf "<<BOLD_BLUE<<"00xWolf "<<BOLD_PURPLE<<"00xWolf "<<BOLD_CYAN<<"00xWolf "<<BOLD_WHITE<<"00xWolf "<<BLACK<<"┃"<<RESET_COLOR<<endl;

    cout<<BLACK<<"┃                                                                 ┃"<<RESET_COLOR<<endl;

    /* Underline Colors */
    cout<<UNDER_LINE_BLACK<<"┃ 00xWolf "<<UNDER_LINE_RED<<"00xWolf "<<UNDER_LINE_GREEN<<"00xWolf "<<UNDER_LINE_YELLOW<<"00xWolf "<<UNDER_LINE_BLUE<<"00xWolf "<<UNDER_LINE_PURPLE<<"00xWolf "<<UNDER_LINE_CYAN<<"00xWolf "<<UNDER_LINE_WHITE<<"00xWolf "<<BLACK<<"┃"<<RESET_COLOR<<endl;

    cout<<BLACK<<"┃                                                                 ┃"<<RESET_COLOR<<endl;

    /* Background Colors */
    cout<<BLACK<<"┃"<<RESET_COLOR<<BACKGROUND_BLACK<<" 00xWolf "<<BACKGROUND_RED<<"00xWolf "<<BACKGROUND_GREEN<<"00xWolf "<<BACKGROUND_YELLOW<<"00xWolf "<<BACKGROUND_BLUE<<"00xWolf "<<BACKGROUND_PURPLE<<"00xWolf "<<BACKGROUND_CYAN<<"00xWolf "<<BACKGROUND_WHITE<<"00xWolf "<<BLACK<<"┃"<<RESET_COLOR<<endl;

    cout<<BLACK<<"┃                                                                 ┃"<<RESET_COLOR<<endl;

    /* High Intensity Colors */
    cout<<BLACK<<"┃"<<RESET_COLOR<<HIGH_INTENSITY_BLACK<<" 00xWolf "<<HIGH_INTENSITY_RED<<"00xWolf "<<HIGH_INTENSITY_GREEN<<"00xWolf "<<HIGH_INTENSITY_YELLOW<<"00xWolf "<<HIGH_INTENSITY_BLUE<<"00xWolf "<<HIGH_INTENSITY_PURPLE<<"00xWolf "<<HIGH_INTENSITY_CYAN<<"00xWolf "<<HIGH_INTENSITY_WHITE<<"00xWolf "<<BLACK<<"┃"<<RESET_COLOR<<endl;

    cout<<BLACK<<"┃                                                                 ┃"<<RESET_COLOR<<endl;

    /* Bold High Intensity Colors */
    cout<<BLACK<<"┃"<<RESET_COLOR<<BOLD_HIGH_INTENSITY_BLACK<<" 00xWolf "<<BOLD_HIGH_INTENSITY_RED<<"00xWolf "<<BOLD_HIGH_INTENSITY_GREEN<<"00xWolf "<<BOLD_HIGH_INTENSITY_YELLOW<<"00xWolf "<<BOLD_HIGH_INTENSITY_BLUE<<"00xWolf "<<BOLD_HIGH_INTENSITY_PURPLE<<"00xWolf "<<BOLD_HIGH_INTENSITY_CYAN<<"00xWolf "<<BOLD_HIGH_INTENSITY_WHITE<<"00xWolf "<<BLACK<<"┃"<<RESET_COLOR<<endl;

    cout<<BLACK<<"┃                                                                 ┃"<<RESET_COLOR<<endl;

    /* High Intensity Background Colors */
    cout<<BLACK<<"┃"<<RESET_COLOR<<HIGH_INTENSITY_BACKGROUND_BLACK<<" 00xWolf "<<HIGH_INTENSITY_BACKGROUND_RED<<"00xWolf "<<HIGH_INTENSITY_BACKGROUND_GREEN<<"00xWolf "<<HIGH_INTENSITY_BACKGROUND_YELLOW<<"00xWolf "<<HIGH_INTENSITY_BACKGROUND_BLUE<<"00xWolf "<<HIGH_INTENSITY_BACKGROUND_PURPLE<<"00xWolf "<<HIGH_INTENSITY_BACKGROUND_CYAN<<"00xWolf "<<HIGH_INTENSITY_BACKGROUND_WHITE<<"00xWolf "<<BLACK<<"┃"<<RESET_COLOR<<endl;

    cout<<BLACK<<"┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛"<<RESET_COLOR<<endl;

    return 0;
}
