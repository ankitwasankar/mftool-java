<h1 align="center">
  <a href="https://github.com/ankitwasankar/mftool-java">
    <img src="https://raw.githubusercontent.com/ankitwasankar/mftool-java/master/src/main/resources/icons/mf-tool-java-new.jpg" alt="mftool-java">
  </a>
</h1>
<p align="center">
<a href="https://search.maven.org/artifact/com.webencyclop.core/mftool-java"><img src="https://img.shields.io/maven-central/v/com.webencyclop.core/mftool-java.svg?label=Maven%20Central"/></a> 
<a href="https://travis-ci.com/github/ankitwasankar/mftool-java"><img src="https://travis-ci.com/ankitwasankar/mftool-java.svg?branch=master" /></a>
<a href="https://github.com/ankitwasankar/mftool-java/blob/master/license.md"><img src="https://camo.githubusercontent.com/8298ac0a88a52618cd97ba4cba6f34f63dd224a22031f283b0fec41a892c82cf/68747470733a2f2f696d672e736869656c64732e696f2f707970692f6c2f73656c656e69756d2d776972652e737667" /></a>
&nbsp;<a href="https://www.linkedin.com/in/ankitwasankar/"><img height="20" src="https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white" /></a>
</p>
<p align="center">
  This repository contains the <strong>MF TOOL - JAVA</strong> source code.
  MF TOOL - JAVA is a java library developed to ease the process of working with Indian Mutual Funds. It's a powerful, actively maintained and easy to use java library.
</p>
<hr/>

# Introduction
This <b>mf-tool java</b> library provides simple apis/functions/methods to work with Indian Mutual Funds. You can,

- Fetch list of all the mutual fund schemes.
- Fetch list of matching mutual fund schemes based on provided keywords.
- Fetch historic or current NAV for the fund.
- Fetch Fund Details for fund and fund house.
- You can integrate this with any Java projects.

# Installation
##### Maven
```
<dependency>
  <groupId>com.webencyclop.core</groupId>
  <artifactId>mftool-java</artifactId>
  <version>1.0.4</version>
</dependency>
```
##### Graddle
```
implementation 'com.webencyclop.core:mftool-java:1.0.4'
```
For other dependency management tool, please visit
https://search.maven.org/artifact/com.webencyclop.core/mftool-java


# Usage
Sample code on how to use the library. <br/>
There are many methods available apart from below sample code, please scroll down to check the documentation.
```
MFTool tool = new MFTool();
tool.matchingScheme("Axis");   //-- get list of all schemes with Axis in it's name
tool.getCurrentNav("120503");  //-- get current nav
```
# Documentation
Multiple methods provide way to work with mutual funds and related data, here we can see each of the methods in details.
### 1. How to initialize MFTool object
```
MFTool tool = new MFTool();
```
This will create the object for you, but it's recommended that you should create this object as <b>singleton</b> object.
The object uses caching mechanism, which under-the-hood caches the values of historic nav and other static information to improve the performance. 
<br/>If you are using the Spring project, you can create the bean in ``@Configuration`` configuration class.
```
@Configuration
public class MFToolConfig{
    @Bean
    public MFTool initializeMfTool() {
        MFTool tool = new MFTool();
        return tool;
    }
}
```
You can use MFTool in other services using ``@Inject`` or ``@autowired`` annotation.
```
@Service
public class MyService {
    
    @Autowired
    private MFTool tool;

    public void getCurrentNav(String scheme) {
        BigDecimal nav = tool.getCurrentNav(scheme);
    }
}
```

### 2. How to fetch list of all Mutual Fund Schemes
```
@Service
public class MyService {
    
    @Autowired
    private MFTool tool;

    public List<SchemeNameCodePair> fetchListOfAllMutualFundSchemes() {
        List<SchemeNameCodePair> list = tool.allSchemes();
    }
}
```

### 3. How to fetch list of all Schemes matching keyword
```
@Service
public class MyService {
    
    @Autowired
    private MFTool tool;

    public List<SchemeNameCodePair> getCurrentNav(String schemeCode) {
        List<SchemeNameCodePair> list = tool.matchingScheme("Axis"); 
        // This will fetch MF schemes which has "Axis" in it's name.
    }
}
```

### 4. Current NAV for the mutual fund scheme
Here eg., schemeCode for Axis Long Term Equity Fund - Direct Plan - Growth Option is 120503.<br/>
When we fetch list of mutual funds, we get the scheme-name, and it's corresponding schemeCode.
<b>Scheme Code uniquely identifies the mutual fund scheme.</b>
```
@Service
public class MyService {
    
    @Autowired
    private MFTool tool;

    public List<SchemeNameCodePair> fetchListSchemes(String schemeCode) {
        BigDecimal nav = tool.getCurrentNav(schemeCode);
    }
}
```

### 5. NAV on specific date for the scheme
Here, LocalDate is used to define the date,
``LocalDate date = LocalDate.parse("2021-07-13");``
```
@Service
public class MyService {
    
    @Autowired
    private MFTool tool;

    public List<SchemeNameCodePair> getNavOnDate(String schemeCode, LocalDate date) {
        BigDecimal nav = tool.getNavFor("120503", date);
    }
}
```

### 6. List of historic NAV for the scheme
This method provide list of all the NAVs for the given scheme.
```
@Service
public class MyService {
    
    @Autowired
    private MFTool tool;

    public List<SchemeNameCodePair> getNavOnDate(String schemeCode) {
        List<Data> list = tool.historicNavForScheme(schemeCode);
    }
}
```


# Issue?
This repository is maintained actively, so if you face any issue please raise the issue and we will try to fix it as soon as possible.
https://github.com/ankitwasankar/mftool-java/issues

#### Liked our work ?
Put a star to our repository. :-)

 

