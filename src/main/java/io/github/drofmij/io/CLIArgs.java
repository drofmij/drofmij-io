package io.github.drofmij.io;

import java.util.HashMap;
import java.util.Map;

/**
 * simple CLI arguments / config handler, pass in args from main method which will be read into argsMap, each argument
 * is split on = if any and loaded into the map for later retrieval, if no = in argument it will be stored in map with
 * empty string.
 *
 * @author drofmij
 */
public class CLIArgs {

    /**
     * map to hold the arguments
     */
    final Map<String, String> argMap;

    /**
     * ye olde constructor, pass in args[] array from main.
     *
     * @param args array of strings
     */
    public CLIArgs(String[] args) {
        argMap = new HashMap<>();
        if (args != null && args.length > 0) {
            for (String arg : args) {
                if (arg.contains("=")) {
                    String split[] = arg.split("=");
                    argMap.put(split[0], split[1]);
                } else {
                    argMap.put(arg, "");
                }
            }
        }
    }

    /**
     * check for existence of ANY arguments.
     *
     * @return true if there are args, false otherwise
     */
    public boolean hasArgs() {
        return !argMap.isEmpty();
    }

    /**
     * check for specified argument in the map.
     *
     * @param arg string of the argument to look for
     * @return true if found, false otherwise
     */
    public boolean has(String arg) {
        return argMap.containsKey(arg);
    }

    /**
     * get the string value stored in map for key arg.
     *
     * @param arg string of the argument name
     * @return value stored in the map for argument name
     */
    public String get(String arg) {
        return argMap.get(arg);
    }

    /**
     * get the Integer value stored in map for key arg.
     * NOTE: assumes you have a string that represents an integer, otherwise NumberFormatException will be thrown
     *
     * @param arg string of the argument name
     * @return Integer value stored in the map for argument name
     */
    public Integer getInt(String arg) throws NumberFormatException {
        return Integer.parseInt(argMap.get(arg));
    }

    /**
     * get the boolean value stored in map for key arg.
     * NOTE: assumes you have a string that represents an integer, otherwise false will be returned.
     *
     * @param arg string of the argument name
     * @return boolean value stored in the map for argument name
     */
    public boolean getBool(String arg) {
        return Boolean.parseBoolean(argMap.get(arg));
    }
}
