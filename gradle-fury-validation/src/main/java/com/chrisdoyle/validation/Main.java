package com.chrisdoyle.validation;

import com.chrisdoyle.validation.tests.Test_Issue12;
import com.chrisdoyle.validation.tests.Test_Issue12Sigs;
import com.chrisdoyle.validation.tests.Test_Issue22;
import com.chrisdoyle.validation.tests.Test_Issue25;
import com.chrisdoyle.validation.tests.Test_Issue27;
import com.chrisdoyle.validation.tests.Test_Issue31;
import com.chrisdoyle.validation.tests.Test_Issue38;
import com.chrisdoyle.validation.tests.Test_Issue46;
import com.chrisdoyle.validation.tests.Test_Issues_23_27;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;


public class Main {

    static final Class[] normalTests = new Class[]{

            Test_Issue12.class,
            Test_Issue22.class,
            Test_Issues_23_27.class,
            Test_Issue25.class,
            Test_Issue27.class,
            Test_Issue31.class,
            Test_Issue38.class,
            Test_Issue46.class,
    };

    static final Class[] signatureTests = new Class[]{
            Test_Issue12Sigs.class
    };


    public final static String[] allSignedArtifacts = new String[]{
            "./hello-world-aar/build/outputs/aar/hello-world-aar-$version-debug.aar" ,
            "./hello-world-aar/build/outputs/aar/hello-world-aar-$version-release.aar" ,
            "./hello-world-aar/build/libs/hello-world-aar-$version-debug-javadoc.jar" ,
            "./hello-world-aar/build/libs/hello-world-aar-$version-debug-sources.jar" ,
            "./hello-world-aar/build/libs/hello-world-aar-$version-release-javadoc.jar" ,
            "./hello-world-aar/build/libs/hello-world-aar-$version-release-sources.jar" ,
            "./hello-world-aar/build/publications/androidArtifacts/pom-default.xml" ,

            "./hello-world-apk/build/outputs/apk/hello-world-apk-$version-barDebug.apk" ,
            "./hello-world-apk/build/outputs/apk/hello-world-apk-$version-barRelease.apk" ,
            "./hello-world-apk/build/outputs/apk/hello-world-apk-$version-bazDebug.apk" ,
            "./hello-world-apk/build/outputs/apk/hello-world-apk-$version-bazRelease.apk" ,
            "./hello-world-apk/build/outputs/apk/hello-world-apk-$version-fooDebug.apk" ,
            "./hello-world-apk/build/outputs/apk/hello-world-apk-$version-fooRelease.apk" ,

            "./hello-world-apk/build/libs/hello-world-apk-$version-barDebug-javadoc.jar" ,
            "./hello-world-apk/build/libs/hello-world-apk-$version-barDebug-sources.jar" ,
            "./hello-world-apk/build/libs/hello-world-apk-$version-barRelease-javadoc.jar" ,
            "./hello-world-apk/build/libs/hello-world-apk-$version-barRelease-sources.jar" ,

            "./hello-world-apk/build/libs/hello-world-apk-$version-bazDebug-javadoc.jar" ,
            "./hello-world-apk/build/libs/hello-world-apk-$version-bazDebug-sources.jar" ,
            "./hello-world-apk/build/libs/hello-world-apk-$version-bazRelease-javadoc.jar" ,
            "./hello-world-apk/build/libs/hello-world-apk-$version-bazRelease-sources.jar" ,

            "./hello-world-apk/build/libs/hello-world-apk-$version-fooDebug-javadoc.jar" ,
            "./hello-world-apk/build/libs/hello-world-apk-$version-fooDebug-sources.jar" ,
            "./hello-world-apk/build/libs/hello-world-apk-$version-fooRelease-javadoc.jar" ,
            "./hello-world-apk/build/libs/hello-world-apk-$version-fooRelease-sources.jar" ,
            "./hello-world-apk/build/publications/androidArtifacts/pom-default.xml" ,



            "./hello-world-apk-overrides/build/outputs/apk/hello-world-apk-overrides-$version-barDebug.apk" ,
            "./hello-world-apk-overrides/build/outputs/apk/hello-world-apk-overrides-$version-barRelease.apk" ,
            "./hello-world-apk-overrides/build/outputs/apk/hello-world-apk-overrides-$version-bazDebug.apk" ,
            "./hello-world-apk-overrides/build/outputs/apk/hello-world-apk-overrides-$version-bazRelease.apk" ,
            "./hello-world-apk-overrides/build/outputs/apk/hello-world-apk-overrides-$version-fooDebug.apk" ,
            "./hello-world-apk-overrides/build/outputs/apk/hello-world-apk-overrides-$version-fooRelease.apk" ,

            "./hello-world-apk-overrides/build/libs/hello-world-apk-overrides-$version-barDebug-javadoc.jar" ,
            "./hello-world-apk-overrides/build/libs/hello-world-apk-overrides-$version-barDebug-sources.jar" ,
            "./hello-world-apk-overrides/build/libs/hello-world-apk-overrides-$version-barRelease-javadoc.jar" ,
            "./hello-world-apk-overrides/build/libs/hello-world-apk-overrides-$version-barRelease-sources.jar" ,

            "./hello-world-apk-overrides/build/libs/hello-world-apk-overrides-$version-bazDebug-javadoc.jar" ,
            "./hello-world-apk-overrides/build/libs/hello-world-apk-overrides-$version-bazDebug-sources.jar" ,
            "./hello-world-apk-overrides/build/libs/hello-world-apk-overrides-$version-bazRelease-javadoc.jar" ,
            "./hello-world-apk-overrides/build/libs/hello-world-apk-overrides-$version-bazRelease-sources.jar" ,

            "./hello-world-apk-overrides/build/libs/hello-world-apk-overrides-$version-fooDebug-javadoc.jar" ,
            "./hello-world-apk-overrides/build/libs/hello-world-apk-overrides-$version-fooDebug-sources.jar" ,
            "./hello-world-apk-overrides/build/libs/hello-world-apk-overrides-$version-fooRelease-javadoc.jar" ,
            "./hello-world-apk-overrides/build/libs/hello-world-apk-overrides-$version-fooRelease-sources.jar" ,
            "./hello-world-apk-overrides/build/publications/androidArtifacts/pom-default.xml" ,



            "./hello-world-lib/build/libs/hello-world-lib-$version.jar" ,
            "./hello-world-lib/build/libs/hello-world-lib-$version-javadoc.jar" ,
            "./hello-world-lib/build/libs/hello-world-lib-$version-sources.jar" ,
            "./hello-world-lib/build/publications/javaArtifacts/pom-default.xml" ,


            /*
            "./hello-world-universe/build/libs/hello-universe-lib-$version.jar" ,
            "./hello-world-universe/build/libs/hello-universe-lib-$version-javadoc.jar" ,
            "./hello-world-universe/build/libs/hello-universe-lib-$version-sources.jar" ,
            "./hello-world-universe/build/publications/javaArtifacts/pom-default.xml" ,
*/

            "./hello-world-war/build/libs/hello-world-war-$version.war" ,
            "./hello-world-war/build/libs/hello-world-war-$version-javadoc.jar" ,
            "./hello-world-war/build/libs/hello-world-war-$version-sources.jar" ,
            "./hello-world-war/build/publications/webApp/pom-default.xml" ,
    };
    public final static String[] allArtifacts = new String[]{
            "~/.m2/repository/com/chrisdoyle/hello-world-aar/$version/hello-world-aar-$version-debug.aar",
            "~/.m2/repository/com/chrisdoyle/hello-world-aar/$version/hello-world-aar-$version-debug-sources.jar",
            "~/.m2/repository/com/chrisdoyle/hello-world-aar/$version/hello-world-aar-$version-debug-javadoc.jar",
            "~/.m2/repository/com/chrisdoyle/hello-world-aar/$version/hello-world-aar-$version.pom",

            "~/.m2/repository/com/chrisdoyle/hello-world-lib/$version/hello-world-lib-$version.jar",
            "~/.m2/repository/com/chrisdoyle/hello-world-lib/$version/hello-world-lib-$version-sources.jar",
            "~/.m2/repository/com/chrisdoyle/hello-world-lib/$version/hello-world-lib-$version-javadoc.jar",
            "~/.m2/repository/com/chrisdoyle/hello-world-lib/$version/hello-world-lib-$version.pom",

          /*  "~/.m2/repository/com/chrisdoyle/hello-univers-lib/$version/hello-univers-lib-$version.jar",
            "~/.m2/repository/com/chrisdoyle/hello-univers-lib/$version/hello-univers-lib-$version-sources.jar",
            "~/.m2/repository/com/chrisdoyle/hello-univers-lib/$version/hello-univers-lib-$version-javadoc.jar",
            "~/.m2/repository/com/chrisdoyle/hello-univers-lib/$version/hello-univers-lib-$version.pom",
*/

            "~/.m2/repository/com/chrisdoyle/hello-world-war/$version/hello-world-war-$version.war",
            "~/.m2/repository/com/chrisdoyle/hello-world-war/$version/hello-world-war-$version-sources.jar",
            "~/.m2/repository/com/chrisdoyle/hello-world-war/$version/hello-world-war-$version-javadoc.jar",
            "~/.m2/repository/com/chrisdoyle/hello-world-war/$version/hello-world-war-$version.pom",

            "~/.m2/repository/com/chrisdoyle/hello-world-apk/$version/hello-world-apk-$version-barDebug.apk",
            "~/.m2/repository/com/chrisdoyle/hello-world-apk/$version/hello-world-apk-$version-barDebug-sources.jar",
            "~/.m2/repository/com/chrisdoyle/hello-world-apk/$version/hello-world-apk-$version-barDebug-javadoc.jar",
            "~/.m2/repository/com/chrisdoyle/hello-world-apk/$version/hello-world-apk-$version-bazDebug.apk",
            "~/.m2/repository/com/chrisdoyle/hello-world-apk/$version/hello-world-apk-$version-bazDebug-sources.jar",
            "~/.m2/repository/com/chrisdoyle/hello-world-apk/$version/hello-world-apk-$version-bazDebug-javadoc.jar",
            "~/.m2/repository/com/chrisdoyle/hello-world-apk/$version/hello-world-apk-$version-fooDebug.apk",
            "~/.m2/repository/com/chrisdoyle/hello-world-apk/$version/hello-world-apk-$version-fooDebug-sources.jar",
            "~/.m2/repository/com/chrisdoyle/hello-world-apk/$version/hello-world-apk-$version-fooDebug-javadoc.jar",
            "~/.m2/repository/com/chrisdoyle/hello-world-apk/$version/hello-world-apk-$version.pom",


            "~/.m2/repository/com/chrisdoyle/hello-world-apk-overrides/$version/hello-world-apk-overrides-$version-barDebug.apk",
            "~/.m2/repository/com/chrisdoyle/hello-world-apk-overrides/$version/hello-world-apk-overrides-$version-barDebug-sources.jar",
            "~/.m2/repository/com/chrisdoyle/hello-world-apk-overrides/$version/hello-world-apk-overrides-$version-barDebug-javadoc.jar",
            "~/.m2/repository/com/chrisdoyle/hello-world-apk-overrides/$version/hello-world-apk-overrides-$version-bazDebug.apk",
            "~/.m2/repository/com/chrisdoyle/hello-world-apk-overrides/$version/hello-world-apk-overrides-$version-bazDebug-sources.jar",
            "~/.m2/repository/com/chrisdoyle/hello-world-apk-overrides/$version/hello-world-apk-overrides-$version-bazDebug-javadoc.jar",
            "~/.m2/repository/com/chrisdoyle/hello-world-apk-overrides/$version/hello-world-apk-overrides-$version-fooDebug.apk",
            "~/.m2/repository/com/chrisdoyle/hello-world-apk-overrides/$version/hello-world-apk-overrides-$version-fooDebug-sources.jar",
            "~/.m2/repository/com/chrisdoyle/hello-world-apk-overrides/$version/hello-world-apk-overrides-$version-fooDebug-javadoc.jar",
            "~/.m2/repository/com/chrisdoyle/hello-world-apk-overrides/$version/hello-world-apk-overrides-$version.pom"

    };

    public final static String[] allPoms = new String[]{
            "~/.m2/repository/com/chrisdoyle/hello-world-aar/$version/hello-world-aar-$version.pom",
            "~/.m2/repository/com/chrisdoyle/hello-world-lib/$version/hello-world-lib-$version.pom",
           // "~/.m2/repository/com/chrisdoyle/hello-universe-lib/$version/hello-universe-lib-$version.pom",
            "~/.m2/repository/com/chrisdoyle/hello-world-apk/$version/hello-world-apk-$version.pom",
            "~/.m2/repository/com/chrisdoyle/hello-world-apk-overrides/$version/hello-world-apk-overrides-$version.pom",
            "~/.m2/repository/com/chrisdoyle/hello-world-war/$version/hello-world-war-$version.pom",

    };

    public static String version;
    public static String cwdDir;
    public static String gpg = "/usr/bin/gpg";
    public static void main(String[] args) throws Exception {

        //init
        File cwd = new File(".");
        System.out.println("============ Gradle Fury Validation ============");
        System.out.println("CWD is " + cwd.getAbsolutePath());
        cwdDir = cwd.getAbsolutePath();

        version = getVersion(cwd);
        String homeDir = System.getProperty("user.home");
        for (int i=0; i < allArtifacts.length; i++){
            allArtifacts[i] = replaceAll(allArtifacts[i], VERSION, version);
            allArtifacts[i] = replaceAll(allArtifacts[i], "~", homeDir);
        }

        for (int i=0; i < allPoms.length; i++){
            allPoms[i] = replaceAll(allPoms[i], VERSION, version);
            allPoms[i] = replaceAll(allPoms[i], "~", homeDir);
        }

        for (int i=0; i < allSignedArtifacts.length; i++){
            allSignedArtifacts[i] = replaceAll(allSignedArtifacts[i], VERSION, version);
            allSignedArtifacts[i]=allSignedArtifacts[i].replaceFirst("\\,", cwdDir);
        }


        Class[] classesToRun=null;

        //cli stuff
        // create Options object
        Options options = new Options();

        options.addOption("withSig", false, "Also run the gpg signature tests");
        options.addOption("help", false, "Help");
        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = parser.parse( options, args);

        if (cmd.hasOption("help")){
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp( "gradle-fury-validation", options );
            return;
        }

        if (cmd.hasOption("withSig")){
            classesToRun = new Class[normalTests.length + signatureTests.length];
            int index=0;
            for (int i=0; i < normalTests.length; i++){
                classesToRun[index] = normalTests[i];
                index++;
            }
            for (int i=0; i < signatureTests.length; i++){
                classesToRun[index] = signatureTests[i];
                index++;
            }

        } else {
            classesToRun = normalTests;
        }


        //execute the unit tests

        Result result = null;
        JUnitCore junit = new JUnitCore();


        result = junit.run(classesToRun);

        String filename = "tck-results-" + new SimpleDateFormat("yyyyMMddhhmm").format(new Date()) + ".txt";
        FileWriter fw = new FileWriter(filename);

        BufferedWriter bw = new BufferedWriter(fw);
        bw.write("Technical Conformance Kit (TCK) Test Results generated " + new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").format(new Date()));
        bw.newLine();
        bw.write("____________________________________________");
        bw.newLine();
        bw.write("Summary");
        bw.newLine();
        bw.write("Failed Test Cases: " + result.getFailureCount());
        bw.newLine();
        bw.write("Skipped Test Cases: " + result.getIgnoreCount());
        bw.newLine();
        bw.write("Ran Test Cases: " + result.getRunCount());
        bw.newLine();
        bw.write("Time: " + result.getRunTime());
        bw.newLine();
        bw.write("____________________________________________");

        bw.newLine();
        bw.write("Tests Ran");
        bw.newLine();
        for (int i = 0; i < normalTests.length; i++) {
            bw.write(normalTests[i].getCanonicalName());
            bw.newLine();
        }
        bw.write("____________________________________________");
        bw.newLine();
        bw.write("Failed Test cases");
        bw.newLine();
        bw.write("____________________________________________");
        bw.newLine();
        for (int i = 0; i < result.getFailures().size(); i++) {
            try {
                try {
                    bw.write(result.getFailures().get(i).getTestHeader());
                } catch (Exception ex) {
                    bw.write(ex.getMessage());
                }
                bw.newLine();
                try {
                    bw.write(result.getFailures().get(i).getDescription().getClassName());
                }
                catch (Exception ex) {
                    bw.write(ex.getMessage());
                }

                bw.newLine();
                try {
                    bw.write(result.getFailures().get(i).getDescription().getMethodName() == null ? "null method!" : result.getFailures().get(i).getDescription().getMethodName());
                } catch (Exception ex) {
                    bw.write(ex.getMessage());
                }
                try{
                    System.out.println("[FAIL] " + result.getFailures().get(i).getDescription().getClassName()+":" + result.getFailures().get(i).getDescription().getMethodName());
                }catch (Exception ex){}
                bw.newLine();
                try {
                    bw.write(result.getFailures().get(i).getMessage() == null ? "no message" : result.getFailures().get(i).getMessage());
                } catch (Exception ex) {
                    bw.write(ex.getMessage());
                }
                bw.newLine();
                //result.getFailures().get(i).getException().printStackTrace();
                try {
                    bw.write(result.getFailures().get(i).getTrace());
                } catch (Exception ex) {
                    bw.write(ex.getMessage());
                }
                bw.newLine();
                bw.write("____________________________________________");
                bw.newLine();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        System.out.println("____________________________________________");
        System.out.println("Summary");
        System.out.println("Failed Test Cases: " + result.getFailureCount());
        System.out.println("Skipped Test Cases: " + result.getIgnoreCount());
        System.out.println("Ran Test Cases: " + result.getRunCount());
        System.out.println("Time: " + result.getRunTime() + "ms which is " +
                org.apache.commons.lang3.time.DurationFormatUtils.formatDurationHMS(result.getRunTime()));
        System.out.println("-------------------------------------");
        System.out.println("Results written to " + filename);

        bw.close();
        fw.close();
        junit = null;
        System.out.println("Exit code: " + result.getFailureCount());
        System.exit(result.getFailureCount());

    }

    public static String getVersion(File cwd) throws Exception {
        File prop = new File(cwd.getAbsolutePath() + File.separator + "gradle.properties");
        if (!prop.exists()) {
            throw new Exception("can't find gradle.properties");
        }
        Properties p = new Properties();
        p.load(new FileInputStream(prop));
        if (p.containsKey("GPG_PATH"))
            gpg = p.getProperty("GPG_PATH");
        return (String)p.getProperty("pom.version");


    }

    public static final String VERSION = "\\$version";

    public static String replaceAll(String source, String token, String replacement){
        return source.replaceAll(token, replacement);
    }
}
