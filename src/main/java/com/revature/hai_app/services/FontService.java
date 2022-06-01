package com.revature.hai_app.services;

public class FontService {

    public static final String RESET = "\033[0m";  // Text Reset
    // Bold High Intensity
    public static final String BLACK_BOLD_BRIGHT = "\033[1;90m"; // BLACK
    public static final String RED_BOLD_BRIGHT = "\033[1;91m";   // RED
    public static final String GREEN_BOLD_BRIGHT = "\033[1;92m"; // GREEN
    public static final String YELLOW_BOLD_BRIGHT = "\033[1;93m";// YELLOW
    public static final String BLUE_BOLD_BRIGHT = "\033[1;94m";  // BLUE
    public static final String PURPLE_BOLD_BRIGHT = "\033[1;95m";// PURPLE
    public static final String CYAN_BOLD_BRIGHT = "\033[1;96m";  // CYAN
    public static final String WHITE_BOLD_BRIGHT = "\033[1;97m"; // WHITE

    public static final String BLACK = "\033[0;30m";   // BLACK
    public static final String RED = "\033[0;31m";     // RED
    public static final String GREEN = "\033[0;32m";   // GREEN
    public static final String YELLOW = "\033[0;33m";  // YELLOW
    public static final String BLUE = "\033[0;34m";    // BLUE
    public static final String PURPLE = "\033[0;35m";  // PURPLE
    public static final String CYAN = "\033[0;36m";    // CYAN
    public static final String WHITE = "\033[0;37m";   // WHITE

    //Bold Font
    public String cyanBold(String s){
        return CYAN_BOLD_BRIGHT + s + RESET;
    }
    public String yellowBold(String s){
        return YELLOW_BOLD_BRIGHT + s + RESET;
    }
    public String blueBold(String s){
        return BLUE_BOLD_BRIGHT + s + RESET;
    }
    public String greenBold(String s){
        return GREEN_BOLD_BRIGHT + s + RESET;
    }
    public String redBold(String s){
        return RED_BOLD_BRIGHT + s + RESET;
    }
    public String blackBold(String s){
        return BLACK_BOLD_BRIGHT + s + RESET;
    }
    public String purpleBold(String s){
        return PURPLE_BOLD_BRIGHT + s + RESET;
    }
    public String whiteBold(String s){
        return WHITE_BOLD_BRIGHT + s + RESET;
    }

    //Normal Font

    public String cyan(String s){
        return CYAN + s + RESET;
    }
    public String yellow(String s){
        return YELLOW + s + RESET;
    }
    public String blue(String s){
        return BLUE + s + RESET;
    }
    public String green(String s){
        return GREEN + s + RESET;
    }
    public String red(String s){
        return RED + s + RESET;
    }
    public String black(String s){
        return BLACK + s + RESET;
    }

}
