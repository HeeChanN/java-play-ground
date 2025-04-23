# 실습 : 빌드툴을 사용하자

<br>

진행한 실습 환경은 윈도우 환경입니다. 따라서 경로 설정의 경우 실습 환경에 맞게 변경해야 합니다.

<br>

## 1. 빌드 툴 없이 실행 가능한 JAR 만들기

먼저 실습에 필요한 jar 파일을 다운로드 받습니다.

[https://repo1.maven.org/maven2/com/google/code/gson/gson/2.10.1/gson-2.10.1.jar]

다운로드 후 `gson-2.10.1.jar` 파일을 `libs\` 폴더에 넣습니다.

### 컴파일 및 실행해보기

```markdown
out 폴더, libs 폴더 만들기

javac -d out -cp "libs\*" src\main\java\org\example\*.java

java -cp "out;libs/*" org.example.Main
```

<br>

### 실행 가능한 JAR 파일 만들기

먼저 manifest.txt 파일을 만든다. 파일을 만들 때 맨 마지막에 빈 line을 하나 추가해서 생성


`manifest.txt`
```markdown
Main-Class: com.example.Main
Class-Path: libs/gson-2.10.1.jar

```

<br>

jar 만들고 실행하기
```markdown
jar cfm gson-app.jar manifest.txt -C out .

java -jar gson-app.jar
```

<br>

jar 안의 manifest 확인하기
```markdown
jar xf gson-app.jar META-INF/MANIFEST.MF
```

<br>

불편한 점

- gson-app.jar파일로 실행시키기 위해서는 out/폴더에 있는 .class파일들과 libs/ 폴더에 있는

`gson-2.10.1.jar` 를 항상 동일 디렉토리에 유지해야 하는 단점

<br>

### Fat JAR 만들기

외부 라이브러리의 클래스들과 내 클래스들을 병합해서 실행 가능한 단일 jar 파일 하나로 만드는 과정

tmp 폴더 생성후 아래 명령어 시도

```markdown
1. out/의 모든 .class 파일 tmp로 복사
xcopy /E /I out tmp 

2. tmp 폴더로 이동해 gson jar 풀기
cd tmp
jar xf ..\libs\gson-2.10.1.jar

3. Fat jar 생성하기
cd ..
jar cfe gson-fat.jar org.example.Main -C tmp .


4. 실행해보기
java -jar gson-fat.jar
```

<br>

어디서든지 실행할 수 있는 단일 jar 생성 -> But, 과정은 정말 복잡

이를 간단하게 만들기 위해서 가장 먼저 생각해볼 수 있는 방법은 많은 명령어를 포함하는 스크립트를 만드는 것

하지만, 스크립트를 만들어도 해결할 수 없는 것은 외부 jar를 직접 다운로드해야하고 버전 업그레이드 시에도 의존성 충돌이나 실수로 누락할 가능성이 매우 높다는 문제점 존제

**이런 빌드 과정을 간편하게 사용할 수 있도록 만들어 준 것 -> 빌드 툴**

위 실습 과정을 통해 빌드 툴을 사용하는 가장 큰 이유는 다음과 같은 것들이 있다고 생각합니다.

- 매번 스크립트를 작성하지 않아도 된다.
- 의존성을 간편하게 관리하고 자동으로 다운로드할 수 있다.
- CI/CD와 컴파일, 테스트같은 것들이 한줄의 명령어로 해결 가능하다 (./gradlew build)

---

<br>

## Gradle로 실행 가능한 jar 만들기 (feat. shadowJar)

<br>

Gradle 플러그인 추가

```gradle
plugins {
    id 'java'
    id 'application' //추가
    id("com.github.johnrengelman.shadow") version "8.1.1" //추가
}

group = 'org.example'
version = '1.0-SNAPSHOT'

application { //추가
    mainClassName = 'org.example.Main'
}

repositories {
    mavenCentral()
}


dependencies {
    testImplementation platform('org.junit:junit-bom:5.11.4')
    testImplementation platform('org.assertj:assertj-bom:3.27.3')

    testImplementation('org.junit.jupiter:junit-jupiter')
    testImplementation('org.assertj:assertj-core')
    implementation("com.google.code.gson:gson:2.10.1")
}

test {
    useJUnitPlatform()
}

tasks.named('shadowJar') {//추가
    archiveBaseName.set('demo')
    mergeServiceFiles()
}
```

해당 플러그인을 통해 다음 이점들을 얻을 수 있다.

- 내 클래스 + 모든 의존 jar를 하나로 병합하여 `build/libs/demo-1.0-SNAPSHOT-all.jar` 생성
- 라이브러리 클래스 이름 공간을 변경해 **충돌 방지 지원**
- 사용하지 않는 클래스 제거로 JAR 크기 축소
- mergeServiceFiles()를 통해 인터페이스까지 같은 service명이 존재해도 둘다 jar에 병합될 수 있도록 도와줌


이후 다음 명령어를 통해 Fat-jar를 생성하고, 해당 jar파일만으로 프로그램을 실행시킬 수 있다. 

```markdown
./gradlew clean shadowJar 
java -jar .\build\libs\demo-1.0-SNAPSHOT-all.jar
```