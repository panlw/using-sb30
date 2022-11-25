# Using Spring Boot V2.7 on GraalVM

- https://github.com/graalvm/graalvm-ce-builds/releases/tag/vm-22.3.0
- https://www.graalvm.org/22.3/docs/getting-started/macos/
- https://docs.spring.io/spring-boot/docs/3.0.0-RC2/reference/html/native-image.html

## FAQ for build errors

### Using Spring Libs Repository for Experimental Artifacts

Add [spring-libs](https://repo.spring.io/libs-release/) into [kinco-public](https://mvn.kinco.top/repository/kinco-public/)


### https://github.com/netty/netty/issues/11020

- BUG: Unable to load `io.netty.resolver.dns.macos.MacOSDnsServerAddressStreamProvider`, fallback to system defaults. This may result in incorrect DNS resolutions on MacOS. Check whether you have a dependency on 'io.netty:netty-resolver-dns-native-macos'
- FIX: add classifier `osx-aarch_64` to dependency `netty-resolver-dns-native-macos`
    ```xml
        <properties>
            <!-- ... -->
            <spring-native.classifier>osx-aarch_64</spring-native.classifier>
        </properties>
        <dependencies>
            <!-- ... -->
            <dependency>
                <groupId>io.netty</groupId>
                <artifactId>netty-resolver-dns-native-macos</artifactId>
                <classifier>${spring-native.classifier}</classifier>
                <version>${netty.version}</version>
            </dependency>
        </dependencies>
    ```


### https://github.com/netty/netty/issues/11369

- BUG: `io.netty.handler.ssl.BouncyCastleAlpnSslUtils` initialize error at build time.
- FIX: src/main/resources/META-INF/native-image/io.netty/netty-handler/native-image.properties
    ```properties
    Args = --initialize-at-run-time=io.netty.handler.ssl.BouncyCastleAlpnSslUtils
    ```
- REF: https://www.graalvm.org/22.0/reference-manual/native-image/BuildConfiguration/#embedding-a-configuration-file


### https://github.com/spring-projects/spring-boot/issues/26721

- BUG: Execution default-cli of goal org.springframework.boot:spring-boot-maven-plugin:2.7.5:build-image failed: Original source '...-exec.jar.original' is required for building an image
- FIX: Ignore it :), waiting for Spring Boot V3 GA
