TravisCI: [![Build Status](https://travis-ci.org/jimbethancourt/dtangler.svg?branch=master)](https://travis-ci.org/jimbethancourt/dtangler) 

SonarCloud: [![Quality Gate](https://sonarcloud.io/api/badges/gate?key=org.hjug.dtangler%3Adtangler)](https://sonarcloud.io/dashboard?id=org.hjug.dtangler%3Adtangler)

[![CodeFactor](https://www.codefactor.io/repository/github/jimbethancourt/dtangler/badge)](https://www.codefactor.io/repository/github/jimbethancourt/dtangler)

[![Known Vulnerabilities](https://snyk.io/test/github/jimbethancourt/dtangler/badge.svg?targetFile=pom.xml)](https://snyk.io/test/github/jimbethancourt/dtangler?targetFile=pom.xml)

[![](http://codescene.io/projects/2168/status.svg)Get more details at **codescene.io**.](http://codescene.io/projects/2168/jobs/latest-successful/results)

# Why dtangler?
I want you and your team to succeed and spend time on what really matters.

Researchers have found that classes involved in one or more dependency cycles are more prone to bugs and changes than those not involved in changes - see [doi:10.1007/s11219-013-9200-8](https://link.springer.com/article/10.1007%2Fs11219-013-9200-8).  By avoiding dependency cycles, you can avoid rework and bugs in your codebase.

# Executable Jar Download
Download the released self-executable jar [from Maven Central](https://oss.sonatype.org/content/repositories/releases/org/hjug/dtangler/swingui/dtangler-swingui/2.1.0/dtangler-swingui-2.1.0-jar-with-dependencies.jar).  You will need Java 8 or newer to run it.

# Maven Plugin
You can use the [dsm-maven-plugin](https://github.com/sevntu-checkstyle/dsm-maven-plugin) as part of your build:
```
     <project>
            ...
            <reporting>
                <plugins>
                    <plugin>
                        <groupId>com.github.sevntu-checkstyle</groupId>
                        <artifactId>dsm-maven-plugin</artifactId>
                        <version>2.2.0version>
                        <configuration>
                            <obfuscatePackageNames>true</obfuscatePackageNames>
                        </configuration>
                    </plugin>
                    <!--  other reporting plugins  -->
                </plugins>
            </reporting>
            ...
        </project>
```

# dtangler is a suite of tools to analyze dependencies. 

dtangler's primary features currently are:
* DSM GUI (Dependency Structure Matrix)
* DSM text-based output
* Command line dependency analyzer (primary targeted for use in automated builds)
* Support for reading dependency info from a text file
* Built in support for Java dependency analysis (jar/class folder, package, and class level)
* The user can write dependency rules and dtangler checks if the code follows the rules.
