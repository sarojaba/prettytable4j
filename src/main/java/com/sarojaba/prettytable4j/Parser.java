package com.sarojaba.prettytable4j;

import java.io.IOException;

/**
 * Interface for Parser.
 */
public interface Parser {

    PrettyTable parse(final String text) throws IOException;
}
