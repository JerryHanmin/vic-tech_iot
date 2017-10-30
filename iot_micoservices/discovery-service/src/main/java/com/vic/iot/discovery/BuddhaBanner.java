package com.vic.iot.discovery;

import org.springframework.boot.Banner;
import org.springframework.boot.ansi.AnsiOutput;
import org.springframework.core.env.Environment;

import java.io.PrintStream;

import static org.springframework.boot.ansi.AnsiColor.BRIGHT_YELLOW;

/**
 */
public class BuddhaBanner implements Banner {
    private static final String[] BANNER = {
            "\n////////////////////////////////////////////////////////////////////\n" +
            "//                          _ooOoo_                               //\n" +
            "//                         o8888888o                              //\n" +
            "//                         88\" . \"88                              //\n" +
            "//                         (| ^_^ |)                              //\n" +
            "//                         O\\  =  /O                              //\n" +
            "//                      ____/`---'\\____                           //\n" +
            "//                    .'  \\\\|     |//  `.                         //\n" +
            "//                   /  \\\\|||  :  |||//  \\                        //\n" +
            "//                  /  _||||| -:- |||||-  \\                       //\n" +
            "//                  |   | \\\\\\  -  /// |   |                       //\n" +
            "//                  | \\_|  ''\\---/''  |   |                       //\n" +
            "//                  \\  .-\\__  `-`  ___/-. /                       //\n" +
            "//                ___`. .'  /--.--\\  `. . ___                     //\n" +
            "//              .\" \" '<  `.___\\_<|>_/___.'  >'\" \".                //\n" +
            "//            | | :  `- \\`.;`\\ _ /`;.`/ - ` : | |                 //\n" +
            "//            \\  \\ `-.   \\_ __\\ /__ _/   .-` /  /                 //\n" +
            "//      ========`-.____`-.___\\_____/___.-`____.-'========         //\n" +
            "//                           `=---='                              //\n" +
            "//      ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^        //\n" +
            "//            佛祖保佑       永不宕机     永无BUG                 //\n" +
            "////////////////////////////////////////////////////////////////////\n"
    };
    private static final String SPRING_BOOT = " :: Spring Boot Version :: ";

    @Override
    public void printBanner(Environment environment, Class<?> sourceClass, PrintStream printStream) {
        for (String line : BANNER) {
            printStream.println(AnsiOutput.toString(BRIGHT_YELLOW, line));
        }

        String version = Banner.class.getPackage().getImplementationVersion();
        version = (version == null ? "" : " (v" + version + ")...");
        printStream.println(AnsiOutput.toString(BRIGHT_YELLOW, SPRING_BOOT, version));
        printStream.println();
    }
}
