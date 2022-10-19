<br/>
<div align="center">
  <a href="https://github.com/ankitwasankar/mftool-java">
    <img src="https://raw.githubusercontent.com/ankitwasankar/mftool-java/master/src/main/resources/icons/mf-tool-java-new.jpg" alt="mftool-java">
  </a>
</div>
<br />

&nbsp;[![Maven Central](https://img.shields.io/maven-central/v/com.webencyclop.core/mftool-java.svg?label=Maven%20Central)](https://search.maven.org/artifact/com.webencyclop.core/mftool-java)
[![Build Status](https://travis-ci.com/ankitwasankar/mftool-java.svg?branch=master)](https://travis-ci.com/github/ankitwasankar/mftool-java)
[![MIT Licence](https://camo.githubusercontent.com/8298ac0a88a52618cd97ba4cba6f34f63dd224a22031f283b0fec41a892c82cf/68747470733a2f2f696d672e736869656c64732e696f2f707970692f6c2f73656c656e69756d2d776972652e737667)](https://github.com/ankitwasankar/mftool-java/blob/master/license.md)
<hr/>

#Introduction

mftool-java is a library for getting Mutual Funds data in India in Java applications. 
It can be used in various types of projects which requires getting live quotes for a given scheme.

###### Note: This library requires internet connection to fetch Mutual Fund data.

# Features
- Fetch list of all the mutual fund schemes with their scheme-codes.
- Match keyword with names of mutual fund list. (useful in auto-suggestion in searches).
- Fetch historic NAV for the fund.
- Fetch current NAV.
- Fetch NAV for specific date.
- Fetch Fund Details for fund.
- Returns data in Java Objects.

# Documentation
###### Add dependency from Maven Central https://search.maven.org/artifact/com.webencyclop.core/mftool-java

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

#### How to use in Java code:
```
// Return the List of mutual funds which matches keyword "Axis"
MFTool mfTool = new MFTool();
List<SchemeNameCodePair> list = mfTool.matchingScheme("Axis");

// Retuns List of all the mutual fund list in India
MFTool tool = new MFTool();
List<SchemeNameCodePair> list = tool.allSchemes();

// Retuns the Mutual Fund details - Axis fund: 120503
MFTool tool = new MFTool();
SchemeDetails details = tool.schemeDetails("120503");

// Returns hisoric NAV data - Axis fund: 120503
MFTool tool = new MFTool();
List<Data> list = tool.historicNavForScheme("120503");

// Returns current NAV for mutual fund
MFTool tool = new MFTool();
BigDecimal nav = tool.getCurrentNav("120503");

// Returns NAV for specific date
MFTool tool = new MFTool();
BigDecimal nav = tool.getNavFor("120503", LocalDate.parse("2021-07-13"));
```

