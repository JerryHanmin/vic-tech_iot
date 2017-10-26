package com.vic.iot.gateway;

import org.springframework.boot.Banner;
import org.springframework.boot.ansi.AnsiOutput;
import org.springframework.core.env.Environment;

import java.io.PrintStream;

import static org.springframework.boot.ansi.AnsiColor.BRIGHT_YELLOW;

/**
 */
public class BuddhaBanner implements Banner {
    private static final String[] BANNER = {
            "////////////////////////////////////////////////////////////////////" +
            "//                          _ooOoo_                               //" +
            "//                         o8888888o                              //" +
            "//                         88\" . \"88                                //" +
            "//                         (| ^_^ |)                              //" +
            "//                         O\\  =  /O                              //" +
            "//                      ____/`---'\\____                           //" +
            "//                    .'  \\\\|     |//  `.                         //" +
            "//                   /  \\\\|||  :  |||//  \\                        //" +
            "//                  /  _||||| -:- |||||-  \\                       //" +
            "//                  |   | \\\\\\  -  /// |   |                       //" +
            "//                  | \\_|  ''\\---/''  |   |                       //" +
            "//                  \\  .-\\__  `-`  ___/-. /                       //" +
            "//                ___`. .'  /--.--\\  `. . ___                     //" +
            "//              .\" \" '<  `.___\\_<|>_/___.'  >'\" \".                  //" +
            "//            | | :  `- \\`.;`\\ _ /`;.`/ - ` : | |                 //" +
            "//            \\  \\ `-.   \\_ __\\ /__ _/   .-` /  /                 //" +
            "//      ========`-.____`-.___\\_____/___.-`____.-'========         //" +
            "//                           `=---='                              //" +
            "//      ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^        //" +
            "//            佛祖保佑       永不宕机     永无BUG                  //" +
            "////////////////////////////////////////////////////////////////////"
    };
    private static final String SPRING_BOOT = " :: Spring Boot Version :: ";

    private static final int STRAP_LINE_SIZE = 42;

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
