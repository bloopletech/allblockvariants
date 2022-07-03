package net.bloople.allblockvariants;

public class Util {
    // Based on https://stackoverflow.com/a/1086134
    public static String toTitleCase(String input) {
        StringBuilder titleCase = new StringBuilder(input.length());
        boolean nextTitleCase = true;

        for(char c : input.toCharArray()) {
            if (c == '_') {
                c = ' ';
                nextTitleCase = true;
            }
            else if(nextTitleCase) {
                c = Character.toTitleCase(c);
                nextTitleCase = false;
            }

            titleCase.append(c);
        }

        return titleCase.toString();
    }
}
