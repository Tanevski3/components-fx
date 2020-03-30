package com.mtanevski.componentsfx.utils;


import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ResourceManagerUtil {

    /*
     Returns resources relative to the 'resources' directory
     */
    public static URL get(String resourcePath){
        URL resource = ResourceManagerUtil.class.getClassLoader().getResource(resourcePath);
        Objects.requireNonNull(resource);
        return resource;
    }


    public static List<String> getClasspathEntriesByPath(String path) throws IOException {
        InputStream is = ResourceManagerUtil.class.getClassLoader().getResourceAsStream(path);
        Objects.requireNonNull(is);
        String string = new String(is.readAllBytes(), Charset.defaultCharset());

        return Arrays.stream(string.split("\n"))                                   // Stream the list
                .filter(line -> line.trim().length()>0)     // Filter out empty lines
                .collect(Collectors.toList());              // Collect remaining lines into a List again
    }
}
