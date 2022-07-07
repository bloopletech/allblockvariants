package net.bloople.allblockvariants

object Util {
    // Based on https://stackoverflow.com/a/1086134
    @JvmStatic
    fun toTitleCase(input: String): String {
        val titleCase = StringBuilder(input.length)
        var nextTitleCase = true

        for(c in input.toCharArray()) {
            var d = c
            if(d == '_') {
                d = ' '
                nextTitleCase = true
            }
            else if(nextTitleCase) {
                d = d.titlecaseChar()
                nextTitleCase = false
            }
            titleCase.append(d)
        }

        return titleCase.toString()
    }
}